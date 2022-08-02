package net.bodz.bas.t.table;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface IRowSet
        extends
            Iterable<IRow>,
            IJsonForm,
            IXmlForm {

    String K_METADATA = "metadata";
    String K_ROWS = "rows";

    ITableMap getParent();

    IRowSetMetadata getMetadata();

    int getRowCount();

    IRow getRow(int index);

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        IRowSetMetadata metadata = getMetadata();
        boolean mergeMetadata = metadata != null && metadata.getParent() != null;

        if (mergeMetadata) {
            // out.entry(K_NAME, ?);
        } else {
            metadata = getMetadata();
            out.key(K_METADATA);
            metadata.writeObjectBoxed(out);
        }
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
        boolean mergeMetadata = metadata != null && metadata.getParent() != null;

        if (!mergeMetadata) {
            metadata = getMetadata();
            out.beginElement(K_METADATA);
            metadata.writeObject(out);
            out.endElement();
        }

        out.beginElement(K_ROWS);
        if (mergeMetadata) {
            // out.attribute(K_NAME, ?);
        }
        for (IRow row : this)
            row.writeObjectBoxed(out);
        out.endElement();
    }

}
