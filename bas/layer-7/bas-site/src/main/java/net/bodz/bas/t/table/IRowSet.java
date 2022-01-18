package net.bodz.bas.t.table;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.IXmlSerializable;

public interface IRowSet
        extends
            Iterable<IRow>,
            IJsonForm,
            IXmlSerializable {

    String K_METADATA = "metadata";
    String K_ROWS = "rows";

    IRowSetMetadata getMetadata();

    int getRowCount();

    IRow getRow(int index);

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        IRowSetMetadata metadata = getMetadata();
        out.key(K_METADATA);
        metadata.writeObjectBoxed(out);
        out.key(K_ROWS);
        out.array();
        for (IRow row : this)
            row.writeObjectBoxed(out);
        out.endArray();
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        IRowSetMetadata metadata = getMetadata();
        out.beginElement(K_METADATA);
        metadata.writeObject(out);
        out.endElement();

        out.beginElement(K_ROWS);
        for (IRow row : this)
            row.writeObjectBoxed(out);
        out.endElement();
    }

}
