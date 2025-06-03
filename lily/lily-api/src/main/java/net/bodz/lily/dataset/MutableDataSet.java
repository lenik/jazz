package net.bodz.lily.dataset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.potato.element.PropertyWriteException;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.catalog.ITable;
import net.bodz.bas.t.catalog.MutableTable;
import net.bodz.bas.t.map.ListMap;
import net.bodz.lily.entity.type.EntityTypes;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public class MutableDataSet
        implements IMutableDataSet,
                   IJsonForm {

    Map<String, MutableTable> map;
    Function<String, IEntityTypeInfo> typeMap;
    Function<ITable, String> defaultNaming;

    public MutableDataSet(@NotNull Map<String, MutableTable> map, @NotNull Function<String, IEntityTypeInfo> typeMap) {
        this.map = map;
        this.typeMap = typeMap;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        Map<String, MutableTable> map;
        Function<String, IEntityTypeInfo> typeMap;
        Function<ITable, String> defaultNaming;

        public Builder order(SortOrder order) {
            map = order.newMap();
            return this;
        }

        public Builder map(Map<String, MutableTable> map) {
            this.map = map;
            return this;
        }

        public Builder typeMap(Function<String, IEntityTypeInfo> typeMap, Function<ITable, String> defaultNaming) {
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

        public MutableDataSet build() {
            if (map == null)
                map = new HashMap<>();
            if (typeMap == null)
                typeMap = EntityTypes.qTypeMap;
            if (defaultNaming == null)
                defaultNaming = EntityTypes.qTypeNaming;
            return new MutableDataSet(map, typeMap);
        }

    }

    @Override
    public IEntityTypeInfo getTypeInfo(@NotNull String name) {
        return typeMap.apply(name);
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public MutableTable getDataTable(@NotNull String name) {
        return map.get(name);
    }

    @Override
    public void addDataTable(@NotNull String name, @NotNull MutableTable dataTable) {
        if (map.containsKey(name))
            throw new DuplicatedKeyException(name);
        map.putIfAbsent(name, dataTable);
    }

    @Override
    public String getDefaultName(ITable table) {
        return defaultNaming.apply(table);
    }

    @Override
    public void removeData(@NotNull String name) {
        map.remove(name);
    }

    public ListMap<String, Object> toNamedBeanMap()
            throws PropertyWriteException {
        return toBeanMap((name, typeInfo) -> name);
    }

    public ListMap<Class<?>, Object> toTypedBeanMap()
            throws PropertyWriteException {
        return toBeanMap((name, typeInfo) -> typeInfo.getEntityClass());
    }

    public <K> ListMap<K, Object> toBeanMap(BiFunction<String, IEntityTypeInfo, K> keyFn)
            throws PropertyWriteException {
        ListMap<K, Object> beansMap = new ListMap<>();
        for (String name : keySet()) {
            IEntityTypeInfo typeInfo = getTypeInfo(name);

            K key = keyFn.apply(name, typeInfo);

            @SuppressWarnings("unchecked")
            Class<Object> entityClass = (Class<Object>) typeInfo.getEntityClass();

            MutableTable table = getDataTable(name);
            List<Object> beans = table.toBeans(entityClass, typeInfo::newInstance);
            beansMap.put(key, beans);
        }
        return beansMap;
    }

}
