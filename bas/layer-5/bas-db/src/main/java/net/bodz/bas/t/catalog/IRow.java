package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface IRow
        extends
            Iterable<Object>,
            IJsonForm,
            IXmlForm {

    IRowSet getRowSet();

    default ITable getTable() {
        return (ITable) getRowSet();
    }

    int getRowIndex();

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

    Object get(int index);

    default Object get(String name) {
        IColumnMetadata column = getColumn(name);
        if (column == null)
            return false;
        int index = column.getPositionOpt();
        return get(index);
    }

    boolean isSet(int index);

    default boolean isSet(String name) {
        IColumnMetadata column = getColumn(name);
        if (column == null)
            return false;
        int index = column.getPositionOpt();
        return isSet(index);
    }

    default List<Object> getPrimaryKeyValues() {
        IColumnMetadata[] keyColumns = getTable().getMetadata().getPrimaryKeyColumns();
        List<Object> values = new ArrayList<>(keyColumns.length);
        for (IColumnMetadata keyColumn : keyColumns) {
            Object value = get(keyColumn.position());
            values.add(value);
        }
        return values;
    }

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        IRowSetMetadata metadata = getRowSet().getMetadata();
        int cc = metadata.getColumnCount();
        for (int i = 0; i < cc; i++) {
            IColumnMetadata column = getColumn(i);
            Object cell = i < cc ? get(i) : null;
            column.writeColumnInJson(out, cell);
        }
    }

    @Override
    default void writeObjectBoxed(IJsonOut out)
            throws IOException, FormatException {
        out.array();
        writeObject(out);
        out.endArray();
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        IRowSetMetadata metadata = getRowSet().getMetadata();
        int cc = metadata.getColumnCount();
        for (int i = 0; i < cc; i++) {
            IColumnMetadata column = getColumn(i);
            Object cell = get(i);
            column.writeColumnInXml(out, cell);
        }
    }

    @Override
    default void writeObjectBoxed(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.beginElement("row");
        out.attribute("index", getRowIndex());
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
            int pos = column.position();
            Object val = get(pos);

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
