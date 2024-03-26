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

    String K_VIEW_COLUMNS = "viewColumns";
    String K_VIEW_COLUMN = "column";
    String K_FROM_COLUMNS = "fromColumns";
    String K_FROM_COLUMN = "column";

    List<String> getViewColumns();

    TableOid getFromTableId();

    List<String> getFromColumns();

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        List<String> viewColumns = getViewColumns();
        if (viewColumns != null) {
            out.key(K_VIEW_COLUMNS);
            out.array();
            for (String col : viewColumns)
                out.value(col);
            out.endArray();
        }

        TableOid oid = getFromTableId();
        if (oid != null)
            oid.jsonOut(out, opts);

        List<String> fromColumns = getFromColumns();
        if (fromColumns != null) {
            out.key(K_FROM_COLUMNS);
            out.array();
            for (String col : fromColumns)
                out.value(col);
            out.endArray();
        }
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        List<String> viewColumns = getFromColumns();
        if (viewColumns != null) {
            out.beginElement(K_VIEW_COLUMNS);
            for (String col : viewColumns)
                out.element(K_VIEW_COLUMN, col);
            out.endElement();
        }

        TableOid oid = getFromTableId();
        if (oid != null)
            oid.writeObject(out);

        List<String> fromColumns = getFromColumns();
        if (fromColumns != null) {
            out.beginElement(K_FROM_COLUMNS);
            for (String col : fromColumns)
                out.element(K_FROM_COLUMN, col);
            out.endElement();
        }
    }

}
