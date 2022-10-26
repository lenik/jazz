package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.Collection;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface IViewMetadata
        extends
            ITableMetadata {

    String K_TABLE_UAGES = "tableUsages";
    String K_TABLE_UAGE = "tableUsage";

    Collection<? extends ITableUsage> getTableUsages();

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        ITableMetadata.super.jsonOut(out, opts);

        Collection<? extends ITableUsage> usages = getTableUsages();
        if (usages != null) {
            out.key(K_TABLE_UAGES);
            out.array();
            for (ITableUsage usage : usages) {
                out.object();
                usage.jsonOut(out, opts);
                out.endObject();
            }
            out.endArray();
        }
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        ITableMetadata.super.writeObject(out);

        Collection<? extends ITableUsage> usages = getTableUsages();
        if (usages != null) {
            out.beginElement(K_TABLE_UAGES);
            for (ITableUsage usage : usages) {
                out.beginElement(K_TABLE_UAGE);
                usage.writeObject(out);
                out.endElement();
            }
            out.endElement();
        }
    }

}
