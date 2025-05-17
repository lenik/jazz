package net.bodz.bas.t.catalog;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public interface IMutableRowSet
        extends IRowSet {

    @Override
    IMutableRow getRow(int index);

    void setRow(int rowIndex, IMutableRow row);

    IMutableRow addNewRow();

    void addRow(IMutableRow row);

    void addRow(int rowIndex, IMutableRow row);

    void removeRow(IMutableRow row);

    void removeRow(int rowIndex);

    void removeAll();

    @Override
    default void jsonIn(@NotNull JsonVariant in, JsonFormOptions opts)
            throws ParseException {
        if (!in.isArray())
            throw new ParseException("expect array: " + in.getType());

        JsonArray array = in.getArray();
        assert array != null;

        if (getMetadata() == null)
            jsonInArrays(array, opts);
        else
            jsonInObjects(array, opts);
    }

    @Override
    default void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        throw new IllegalUsageException();
    }

    default void jsonInArrays(JsonArray arrays, JsonFormOptions opts)
            throws ParseException {
        List<? extends IColumnMetadata> columns = Collections.emptyList();
        IRowSetMetadata metadata = getMetadata();
        if (metadata != null)
            columns = metadata.getColumns();

        int len = arrays.length();
        for (int i = 0; i < len; i++) {
            JsonArray in = arrays.getJsonArray(i);
            IMutableRow row = addNewRow();
            row.jsonInArray(in, columns, opts);
        }
    }

    default void jsonInObjects(JsonArray objects, JsonFormOptions opts)
            throws ParseException {
        IRowSetMetadata metadata = getMetadata();
        if (metadata == null)
            throw new IllegalUsageException("require metadata");

        Map<String, IColumnMetadata> columnMap = new LinkedHashMap<>();
        for (IColumnMetadata column : metadata.getColumns())
            columnMap.put(column.getName(), column);

        int len = objects.length();
        for (int i = 0; i < len; i++) {
            JsonObject in = objects.getJsonObject(i);
            IMutableRow row = addNewRow();
            row.jsonInObject(in, columnMap, opts);
        }
    }

}
