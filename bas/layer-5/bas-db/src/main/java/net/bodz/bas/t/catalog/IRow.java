package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface IRow
        extends
            IJsonForm,
            IXmlForm {

    IRowSet getRowSet();

    default ITable getTable() {
        return (ITable) getRowSet();
    }

//    int getRowIndex();

    /**
     * @return -1 if unknown.
     */
    default int getColumnCount() {
        IRowSet rowSet = getRowSet();
        if (rowSet != null) {
            IRowSetMetadata metadata = getRowSet().getMetadata();
            if (metadata != null)
                return metadata.getColumnCount();
        }
        return -1;
    }

    IColumnMetadata getColumn(int index);

    /**
     * Get column metadata by name.
     *
     * @return non-<code>null</code>.
     * @throws NoSuchKeyException
     *             if column with <code>name</code> isn't existed.
     */
    IColumnMetadata getColumn(String name);

    Class<?> getColumnType(int index);

    Class<?> getColumnType(String columnName);

    Iterable<? extends ICell> flatten();

    /**
     * actual allocated cells.
     */
    List<? extends ICell> getCells();

    int getCellCount();

    ICell getCell(int columnIndex);

    default ICell getCell(String columnName) {
        int index = getColumnIndex(columnName);
        return getCell(index);
    }

    Object getCellData(int columnIndex);

    default Object getCellData(String columnName) {
        int index = getColumnIndex(columnName);
        return getCellData(index);
    }

    /**
     * @throws NoSuchKeyException
     */
    default int getColumnIndex(String name) {
        return getColumnIndex(name, false);
    }

    /**
     * @throws NoSuchKeyException
     */
    default int getColumnIndex(String name, boolean optional) {
        ITable table = getTable();
        ITableMetadata parent = table == null ? null : table.getMetadata();
        if (parent == null)
            throw new IllegalUsageException("No table metadata.");
        int index = parent.indexOfColumn(name);
        if (index == -1)
            if (! optional)
                throw new NoSuchKeyException(name);
        return index;
    }

    boolean isSet(int index);

    default boolean isSet(String name) {
        int index = getColumnIndex(name, true);
        if (index == -1)
            return false;
        return isSet(index);
    }

    default List<Object> getPrimaryKeyValues() {
        IColumnMetadata[] keyColumns = getTable().getMetadata().getPrimaryKeyColumns();
        List<Object> values = new ArrayList<>(keyColumns.length);
        for (IColumnMetadata keyColumn : keyColumns) {
            int index = getColumnIndex(keyColumn.getName());
            Object value = getCellData(index);
            values.add(value);
        }
        return values;
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.array();
        IRowSetMetadata metadata = getRowSet().getMetadata();
        int cc = metadata.getColumnCount();
        for (int i = 0; i < cc; i++) {
            IColumnMetadata column = getColumn(i);
            Object cell = i < cc ? getCellData(i) : null;
            column.writeColumnInJson(out, cell);
        }
        out.endArray();
    }

    @Override
    default boolean wantObjectContext() {
        return false;
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        IRowSetMetadata metadata = getRowSet().getMetadata();
        int cc = metadata.getColumnCount();
        for (int i = 0; i < cc; i++) {
            IColumnMetadata column = getColumn(i);
            Object cell = getCellData(i);
            column.writeColumnInXml(out, cell);
        }
    }

    @Override
    default void writeObjectBoxed(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.beginElement("row");
        // out.attribute("index", getRowIndex());
        writeObject(out);
        out.endElement();
    }

    default Map<String, Object> toMap() {
        return toMap(null, null);
    }

    default Map<String, Object> toMap(//
            Function<String, String> rename, //
            Function<Integer, String> positionName) {
        Map<String, Object> map = new HashMap<>();
        exportTo(map, rename, positionName);
        return map;
    }

    /**
     * If the renamed name and the position name are different, both names will be used.
     */
    default void exportTo(Map<String, Object> map, //
            Function<String, String> rename, //
            Function<Integer, String> positionName) {
        IRowSet rowSet = getRowSet();
        IRowSetMetadata metadata = rowSet.getMetadata();
        for (IColumnMetadata column : metadata.getColumns()) {
            int pos = getColumnIndex(column.getName());
            Object val = getCellData(pos);

            String name = column.getName();
            if (rename != null)
                name = rename.apply(name);
            map.put(name, val);

            if (positionName != null) {
                String posName = positionName.apply(pos);
                if (posName != null)
                    map.put(posName, val);
            }
        }
    }

}
