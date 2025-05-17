package net.bodz.bas.t.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.PropertyReadException;

public interface IMutableRow
        extends IRow {

    @Override
    List<? extends IMutableCell> getCells();

//    void setCells(@NotNull List<IMutableCell> cells);

    void setCellData(int index, Object o);

    default void setCellData(String name, Object o) {
        int index = getColumnIndex(name);
        setCellData(index, o);
    }

    @Override
    IMutableCell getCell(int columnIndex);

    @Override
    default IMutableCell getCell(String columnName) {
        int index = getColumnIndex(columnName);
        return getCell(index);
    }

    default void setCell(String columnName, IMutableCell cell) {
        int index = getColumnIndex(columnName);
        setCell(index, cell);
    }

    IMutableCell createCell(int columnIndex);

    default IMutableCell addNewCell() {
        IRowSetMetadata metadata = getRowSet().getMetadata();
        IMutableCell cell;
        if (metadata != null) {
            int columnIndex = getCellCount();
            cell = createCell(columnIndex);
        } else {
            cell = new MutableCell(this);
        }
        addCell(cell);
        return cell;
    }

    default IMutableCell addNewCell(int columnIndex) {
        MutableCell cell = new MutableCell(this, columnIndex);
        setCell(columnIndex, cell);
        return cell;
    }

    void addCell(IMutableCell cell);

    void setCell(int columnIndex, IMutableCell cell);

    void removeCell(IMutableCell cell);

    void removeCell(int columnIndex);

    default void readObject(ResultSet rs)
            throws SQLException {
        IRowSetMetadata metadata = getRowSet().getMetadata();
        int cc = metadata.getColumnCount();
        for (int i = 0; i < cc; i++) {
            Object cell = rs.getObject(i + 1);
            this.setCellData(i, cell);
        }
    }

    // BEAN FORM

    default void readFromBean(@NotNull Object bean)
            throws PropertyReadException {
        IRowSet rowSet = getRowSet();
        if (rowSet == null)
            throw new IllegalUsageException("w/o rowSet");

        IRowSetMetadata metadata = rowSet.getMetadata();
        if (metadata == null)
            throw new IllegalUsageException("w/o metadata");

        List<? extends IColumnMetadata> columns = metadata.getColumns();
        int n = columns.size();
        for (int i = 0; i < n; i++) {
            IColumnMetadata column = columns.get(i);
            IProperty property = column.getProperty();
            Object value = null;
            try {
                value = property.read(bean);
            } catch (PropertyReadException e) {
                throw new PropertyReadException("error read from property " + property + ": " + e.getMessage(), e);
            }
            setCellData(i, value);
        }
    }

    // JSON FORM

    @Override
    default void jsonIn(@NotNull JsonVariant in, JsonFormOptions opts)
            throws ParseException {
        List<? extends IColumnMetadata> columns = Collections.emptyList();

        IRowSet rowSet = getRowSet();
        if (rowSet != null) {
            IRowSetMetadata metadata = rowSet.getMetadata();
            if (metadata != null)
                columns = metadata.getColumns();
        }

        if (in.isArray()) {
            JsonArray jarray = in.getArray();
            assert jarray != null;
            jsonInArray(jarray, columns, opts);
        } else {
            assert in.isObject();
            JsonObject jobject = in.getObject();
            assert jobject != null;

            Map<String, IColumnMetadata> columnMap = new LinkedHashMap<>();
            for (IColumnMetadata column : columns)
                columnMap.put(column.getName(), column);

            jsonInObject(jobject, columnMap, opts);
        }
    }

    @Override
    default void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        jsonIn(JsonVariant.of(o), opts);
    }

    default void jsonInArray(@NotNull JsonArray array, List<? extends IColumnMetadata> columns, JsonFormOptions opts)
            throws ParseException {
        int arrayLen = array.length();
        int columnCount = columns.size();
        int max = Math.max(arrayLen, columnCount);

        for (int i = 0; i < max; i++) {
            Object jsonValue = null;
            if (i < arrayLen)
                jsonValue = array.get(i);

            Object cellData = jsonValue;

            if (i < columnCount) {
                IColumnMetadata column = columns.get(i);
                if (column != null)
                    cellData = column.readColumnJsonValue(jsonValue);
            }

            IMutableCell cell = addNewCell();
            cell.setData(cellData);
        }
    }

    default void jsonInObject(@NotNull JsonObject jo, Map<String, ? extends IColumnMetadata> columnMap, JsonFormOptions opts)
            throws ParseException {
        for (String columnName : columnMap.keySet()) {
            IColumnMetadata column = columnMap.get(columnName);

            Object jsonValue = jo.get(columnName);

            Object cellData = jsonValue;

            if (column != null)
                cellData = column.readColumnJsonValue(jsonValue);

            IMutableCell cell = addNewCell();
            cell.setData(cellData);
        }
    }

}
