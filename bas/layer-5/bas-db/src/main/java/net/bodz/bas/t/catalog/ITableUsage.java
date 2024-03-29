package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ITableUsage
        extends
            IJsonForm,
            IXmlForm {

    String K_COLUMNS = "columns";
    String K_COLUMN = "columns";

    TableOid getTableId();

    List<String> getColumns();

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        TableOid oid = getTableId();
        if (oid != null)
            oid.jsonOut(out, opts);

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
        TableOid oid = getTableId();
        if (oid != null)
            oid.writeObject(out);

        List<String> columns = getColumns();
        if (columns != null) {
            out.beginElement(K_COLUMNS);
            for (String col : columns)
                out.element(K_COLUMN, col);
            out.endElement();
        }
    }

}
