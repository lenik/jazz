package net.bodz.bas.t.table;

import java.io.IOException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.IXmlSerializable;

public interface IRowSetMetadata
        extends
            Iterable<IColumnMetadata>,
            IJsonSerializable,
            IXmlSerializable {

    String K_COLUMNS = "columns";
    String K_COLUMN = "column";

    boolean isSparse();

    List<IColumnMetadata> getColumns();

    int getColumnCount();

    default IColumnMetadata getColumn(String name) {
        return getColumn(name, false);
    }

    default IColumnMetadata getColumn(String name, boolean ignoreCase) {
        int pos = indexOfColumn(name, ignoreCase);
        if (pos == -1)
            return null;
        else
            return getColumn(pos);
    }

    default int indexOfColumn(String name, boolean ignoreCase) {
        if (name == null)
            throw new NullPointerException("name");
        int n = getColumnCount();
        for (int i = 0; i < n; i++) {
            IColumnMetadata column = getColumn(i);
            if (ignoreCase) {
                if (column.getName().equalsIgnoreCase(name))
                    return i;
            } else {
                if (column.getName().equals(name))
                    return i;
            }
        }
        return -1;
    }

    default int indexOfColumn(String name) {
        return indexOfColumn(name, false);
    }

    default int indexOfColumnIgnoreCase(String name) {
        return indexOfColumn(name, true);
    }

    IColumnMetadata getColumn(int index);

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        out.key(K_COLUMNS);
        out.array();
        int cc = getColumnCount();
        for (int i = 0; i < cc; i++) {
            IColumnMetadata column = getColumn(i);
            out.object();
            column.writeObject(out);
            out.endObject();
        }
        out.endArray();
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.beginElement(K_COLUMNS);
        int cc = getColumnCount();
        for (int i = 0; i < cc; i++) {
            IColumnMetadata column = getColumn(i);
            out.beginElement(K_COLUMN);
            column.writeObject(out);
            out.endElement();
        }
        out.endElement();
    }

}
