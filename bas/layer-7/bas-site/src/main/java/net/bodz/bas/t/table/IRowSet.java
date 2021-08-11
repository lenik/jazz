package net.bodz.bas.t.table;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.IXmlSerializable;

public interface IRowSet
        extends
            Iterable<IRow>,
            IJsonSerializable,
            IXmlSerializable {

    IRowSetMetadata getMetadata();

    int getRowCount();

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        IRowSetMetadata metadata = getMetadata();
        out.key("metadata");
        metadata.writeObjectBoxed(out);
        out.key("rows");
        out.array();
        for (IRow row : this)
            row.writeObjectBoxed(out);
        out.endArray();
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        IRowSetMetadata metadata = getMetadata();
        out.beginElement("metadata");
        metadata.writeObject(out);
        out.endElement();

        out.beginElement("rows");
        for (IRow row : this)
            row.writeObjectBoxed(out);
        out.endElement();
    }

}
