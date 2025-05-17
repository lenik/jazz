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
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.PropertyWriteException;
import net.bodz.bas.t.variant.IVariant;
import net.bodz.bas.t.variant.MutableVariant;

public interface IRow
        extends IJsonForm,
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
     * @throws NoSuchKeyException if column with <code>name</code> isn't existed.
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

    default IVariant getCellVar(int columnIndex) {
        Object data = getCellData(columnIndex);
        return MutableVariant.wrap(data);
    }

    default Object getCellVar(String columnName) {
        int index = getColumnIndex(columnName);
        return getCellVar(index);
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
            if (!optional)
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

    // MAP FORM

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

    // JSON FORM
    @Override
    default boolean wantObjectContext() {
        return false;
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        jsonOutArray(out, opts);
    }

    default void jsonOutArray(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        int cellCount = getCellCount();
        int columnCount = cellCount;

        IRowSetMetadata metadata = null;
        IRowSet rowSet = getRowSet();
        if (rowSet != null)
            metadata = rowSet.getMetadata();

        if (metadata != null)
            columnCount = metadata.getColumnCount();

        int maxCount = Math.max(columnCount, cellCount);

        out.array();
        for (int i = 0; i < maxCount; i++) {
            IColumnMetadata column = getColumn(i);
            Object cell = i < cellCount ? getCellData(i) : null;
            column.writeColumnInJson(out, cell);
        }
        out.endArray();
    }

    default void jsonOutObject(IJsonOut out, JsonFormOptions opts, @NotNull IRowSetMetadata metadata)
            throws IOException, FormatException {
        int columnCount = metadata.getColumnCount();
        int cellCount = getCellCount();
        int minCount = Math.min(columnCount, cellCount);


        out.object();
        for (int i = 0; i < minCount; i++) {
            String name = metadata.getColumn(i).getName();
            out.key(name);

            IColumnMetadata column = getColumn(i);
            Object cell = getCellData(i);
            column.writeColumnInJson(out, cell);
        }
        out.endObject();
    }

    // XML FORM

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

    // BEAN FORM

    default void writeToBean(Object bean)
            throws PropertyWriteException {
        IRowSet rowSet = getRowSet();
        if (rowSet == null)
            throw new IllegalUsageException("w/o rowSet");

        IRowSetMetadata metadata = rowSet.getMetadata();
        if (metadata == null)
            throw new IllegalUsageException("w/o metadata");

        IProperty[] propertyArray = metadata.toPropertyArray();

        writeToBean(bean, propertyArray);
    }

    default void writeToBean(Object bean, @NotNull IProperty[] propertyArray)
            throws PropertyWriteException {
        int n = propertyArray.length;
        for (int i = 0; i < n; i++) {
            IProperty property = propertyArray[i];
            if (!property.isWritable())
                continue;

            Object cellData = getCellData(i);
            try {
                property.write(bean, cellData);
            } catch (PropertyWriteException e) {
                throw new PropertyWriteException("error write to property " + property + ": " + e.getMessage(), e);
            }
        }
    }

}
