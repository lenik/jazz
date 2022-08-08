package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ITableUsage
        extends
            IJsonForm,
            IXmlForm {

    String K_COLUMNS = "columns";
    String K_COLUMN = "columns";

    TableId getTableId();

    List<String> getColumns();

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        TableId id = getTableId();
        if (id != null)
            id.writeObject(out);

        List<String> columns = getColumns();
        if (columns != null) {
            out.key(K_COLUMNS);
            out.array();
            for (String col : columns)
                out.value(col);
            out.endArray();
        }
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        TableId id = getTableId();
        if (id != null)
            id.writeObject(out);

        List<String> columns = getColumns();
        if (columns != null) {
            out.beginElement(K_COLUMNS);
            for (String col : columns)
                out.element(K_COLUMN, col);
            out.endElement();
        }
    }

}
