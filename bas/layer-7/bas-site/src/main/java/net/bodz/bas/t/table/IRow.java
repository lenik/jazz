package net.bodz.bas.t.table;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.IXmlSerializable;

public interface IRow
        extends
            Iterable<Object>,
            IJsonSerializable,
            IXmlSerializable {

    IRowSetMetadata getRowSetMetadata();

    int getRowIndex();

    IColumnMetadata getMetadata(int index);

    /**
     * Get column metadata by name.
     *
     * @return non-<code>null</code>.
     * @throws NoSuchKeyException
     *             if column with <code>name</code> isn't existed.
     */
    IColumnMetadata getMetadata(String name);

    Class<?> getType(int index);

    default Class<?> getType(String name) {
        return getType(getMetadata(name).getIndex());
    }

    Object get(int index);

    default Object get(String name) {
        return get(getMetadata(name).getIndex());
    }

    boolean isSet(int index);

    default boolean isSet(String name) {
        return isSet(getMetadata(name).getIndex());
    }

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        IRowSetMetadata metadata = getRowSetMetadata();
        int cc = metadata.getColumnCount();
        for (int i = 0; i < cc; i++) {
            IColumnMetadata column = getMetadata(i);
            Object cell = i < cc ? get(i) : null;
            column.writeJson(out, cell);
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
        IRowSetMetadata metadata = getRowSetMetadata();
        int cc = metadata.getColumnCount();
        for (int i = 0; i < cc; i++) {
            IColumnMetadata column = getMetadata(i);
            Object cell = get(i);
            column.writeXml(out, cell);
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
            Function<Integer, String> renameIndexes) {
        Map<String, Object> map = new HashMap<>();
        exportTo(map, rename, renameIndexes);
        return map;
    }

    default void exportTo(Map<String, Object> map, //
            Function<String, String> rename, //
            Function<Integer, String> renameIndexes) {
        for (IColumnMetadata column : getRowSetMetadata().getColumns()) {
            int index = column.getIndex();
            Object val = get(index);

            String name = column.getName();
            if (rename != null)
                name = rename.apply(name);
            map.put(name, val);

            if (renameIndexes != null) {
                String indexName = renameIndexes.apply(index);
                if (indexName != null)
                    map.put(indexName, val);
            }
        }
    }

}
