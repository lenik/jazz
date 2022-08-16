package net.bodz.bas.t.catalog;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ITable
        extends
            IRowSet,
            IJsonForm,
            IXmlForm {

    @Override
    ITableMetadata getMetadata();

    ISchema getParent();

    TableOid getId();

    default String getName() {
        return getId().getTableName();
    }

    default boolean isAttached() {
        if (getParent() == null)
            return false;
        ITableMetadata metadata = getMetadata();
        if (metadata == null)
            return false;
        if (metadata.getParent() == null)
            return false;
        return true;
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        ITableMetadata metadata = getMetadata();
        if (isAttached()) {
            getId().jsonOut(out, opts);
        } else {
            out.key(K_METADATA);
            metadata.jsonOut(out, opts, true);
        }
        out.key(K_ROWS);
        out.array();
        for (IRow row : this)
            row.jsonOut(out, opts, true);
        out.endArray();
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        ITableMetadata metadata = getMetadata();
        if (isAttached()) {
            getId().writeObject(out);
        } else {
            out.beginElement(K_METADATA);
            metadata.writeObject(out);
            out.endElement();
        }

        out.beginElement(K_ROWS);
        for (IRow row : this)
            row.writeObjectBoxed(out);
        out.endElement();
    }

}
