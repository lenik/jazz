package net.bodz.lily.dataset;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.catalog.IMutableRowSet;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.bas.t.catalog.MutableTable;
import net.bodz.bas.t.tuple.QualifiedName;

public interface IMutableDataSet
        extends IDataSet {

    @Override
    default IMutableRowSet getData(@NotNull String name) {
        return getDataTable(name);
    }

    @Override
    MutableTable getDataTable(@NotNull String name);

    default void addData(@NotNull String name, @NotNull IMutableRowSet data) {
        if (data instanceof MutableTable)
            addDataTable(name, (MutableTable) data);
        else
            throw new IllegalArgumentException("unsupported data type: " + data.getClass());
    }

    void addDataTable(@NotNull String name, @NotNull MutableTable dataTable);

    default void addDataTable(@NotNull MutableTable dataTable) {
        String name = dataTable.getName();
        ITableMetadata metadata = dataTable.getMetadata();
        if (metadata != null) {
            QualifiedName javaType = metadata.getJavaType();
            if (javaType != null)
                name = javaType.name;
        }

        addDataTable(name, dataTable);
    }

    default MutableTable addNewDataTable(@NotNull String name) {
        ITableMetadata metadata = getMetadata(name);
        if (metadata == null)
            throw new IllegalArgumentException("no metadata for name " + name);
        MutableTable dataTable = new MutableTable(metadata);
        addDataTable(name, dataTable);
        return dataTable;
    }

    void removeData(@NotNull String name);

    @Override
    default void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        for (String name : o.keySet()) {
            JsonArray array = o.getJsonArray(name);
            MutableTable table = addNewDataTable(name);
            table.jsonInObjects(array, opts);
        }
    }

}
