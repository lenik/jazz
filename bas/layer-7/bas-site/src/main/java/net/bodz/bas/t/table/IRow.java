package net.bodz.bas.t.table;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
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

    Class<?> getType(int index);

    Object get(int index);

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

}
