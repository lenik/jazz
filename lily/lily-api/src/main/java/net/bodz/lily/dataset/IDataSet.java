package net.bodz.lily.dataset;

import java.io.IOException;
import java.util.Set;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.catalog.DefaultTableMetadata;
import net.bodz.bas.t.catalog.IRowSet;
import net.bodz.bas.t.catalog.IRowSetMetadata;
import net.bodz.bas.t.catalog.ITable;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public interface IDataSet
        extends IJsonForm {

    IEntityTypeInfo getTypeInfo(@NotNull String name);

    default ITableMetadata getMetadata(@NotNull String name) {
        IEntityTypeInfo typeInfo = getTypeInfo(name);
        if (typeInfo == null)
            return null;
        return getMetadata(typeInfo.getEntityClass());
    }

    default ITableMetadata getMetadata(@NotNull Class<?> type) {
        DefaultTableMetadata metadata = new DefaultTableMetadata();
        metadata.parseClass(type);
        return metadata;
    }

    Set<String> keySet();

    default IRowSet getData(@NotNull String name) {
        return getDataTable(name);
    }

    ITable getDataTable(@NotNull String name);

    String getDefaultName(ITable table);

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        for (String name : keySet()) {
            out.key(name);

            IRowSet data = getData(name);
            IRowSetMetadata metadata = data.getMetadata();
            if (metadata == null)
                data.jsonOutArrays(out, opts);
            else
                data.jsonOutObjects(out, opts);
        }
    }

}
