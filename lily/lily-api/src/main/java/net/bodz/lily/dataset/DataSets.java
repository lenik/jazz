package net.bodz.lily.dataset;

import java.util.List;
import java.util.function.Function;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.catalog.DefaultCatalogMetadata;
import net.bodz.bas.t.catalog.ICatalogMetadata;
import net.bodz.bas.t.catalog.ITable;
import net.bodz.bas.t.catalog.MutableTable;
import net.bodz.lily.entity.type.EntityTypes;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public class DataSets
        implements IDataContextAware {

    DataContext dataContext;
    ICatalogMetadata catalog;

    Function<String, IEntityTypeInfo> typeMap;
    Function<ITable, String> defaultNaming;

    int limit;

    public DataSets(@NotNull DataContext dataContext, @NotNull ICatalogMetadata catalog, @NotNull Function<String, IEntityTypeInfo> typeMap) {
        this.dataContext = dataContext;
        this.catalog = catalog;
        this.typeMap = typeMap;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        DataContext dataContext;
        ICatalogMetadata catalog = null;
        Function<String, IEntityTypeInfo> typeMap;
        Function<ITable, String> defaultNaming;

        public Builder dataContext(@NotNull DataContext dataContext) {
            this.dataContext = dataContext;
            return this;
        }

        public Builder catalog(@NotNull ICatalogMetadata catalog) {
            this.catalog = catalog;
            return this;
        }

        public Builder typeMap(@NotNull Function<String, IEntityTypeInfo> typeMap) {
            this.typeMap = typeMap;
            return this;
        }

        public Builder typeMap(@NotNull Function<String, IEntityTypeInfo> typeMap, @NotNull Function<ITable, String> defaultNaming) {
            this.typeMap = typeMap;
            this.defaultNaming = defaultNaming;
            return this;
        }

        public Builder qTypeMap() {
            typeMap = EntityTypes.qTypeMap;
            defaultNaming = EntityTypes.qTypeNaming;
            return this;
        }

        public Builder simpleTypeMap() {
            typeMap = EntityTypes.simpleTypeMap;
            defaultNaming = EntityTypes.simpleTypeNaming;
            return this;
        }


        public DataSets build() {
            if (dataContext == null)
                dataContext = DataHub.getPreferredHub().getMain();

            if (catalog == null)
                catalog = new DefaultCatalogMetadata(dataContext);

            if (typeMap == null)
                typeMap = EntityTypes.qTypeMap;

            if (defaultNaming == null)
                defaultNaming = EntityTypes.qTypeNaming;

            return new DataSets(dataContext, catalog, typeMap);
        }
    }

    @NotNull
    @Override
    public DataContext getDataContext() {
        return dataContext;
    }

    @Override
    public void setDataContext(@NotNull DataContext dataContext) {
        this.dataContext = dataContext;
    }

    public DefaultDataSet newDataSet() {
        return DefaultDataSet.builder() //
                .typeMap(typeMap, defaultNaming) //
                .build();
    }

    public <T> MutableTable loadTable(Class<T> type) {
        return loadTable(type, (Integer) null);
    }

    public <T> MutableTable loadTable(Class<T> type, Integer limit) {
        SelectOptions options = new SelectOptions();
        if (limit != null)
            options.limit(limit);
        return loadTable(type, mapper -> mapper.all(options));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public MutableTable _loadTable(Class<?> type, Function<IEntityMapper<?>, List<?>> loader) {
        return loadTable((Class) type, (Function) loader);
    }

    public <T> MutableTable loadTable(Class<T> type, Function<IEntityMapper<T>, List<T>> loader) {
        IEntityTypeInfo typeInfo = EntityTypes.getTypeInfo(type);
        Class<?> mapperClass = typeInfo.getMapperClass();

        @SuppressWarnings("unchecked")
        IEntityMapper<T> mapper = (IEntityMapper<T>) dataContext.getMapper(mapperClass);

        if (mapper == null)
            throw new IllegalUsageException("No mapper for " + mapperClass);

        SelectOptions options = new SelectOptions();
        if (limit != 0)
            options.limit(limit);

        List<T> objectList = mapper.all(options);
        MutableTable table = MutableTable.fromObjects(type, objectList);
        return table;
    }

    public IMutableDataSet loadTables(Class<?>... types) {
        return loadTables((Integer) null, types);
    }

    public IMutableDataSet loadTables(Integer limit, Class<?>... types) {
        SelectOptions options = new SelectOptions();
        if (limit != null)
            options.limit(limit);
        return loadTables(mapper -> mapper.all(options), types);
    }

    public IMutableDataSet loadTables(Function<IEntityMapper<?>, List<?>> loader, Class<?>... types) {
        DefaultDataSet dataSet = DefaultDataSet.builder()//
                .typeMap(typeMap, defaultNaming)//
                .build();
        for (Class<?> type : types) {
            String name = type.getSimpleName();
            MutableTable table = _loadTable(type, loader);
            dataSet.addData(name, table);
        }
        return dataSet;
    }

}
