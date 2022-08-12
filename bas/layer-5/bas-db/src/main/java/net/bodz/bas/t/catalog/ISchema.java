package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ISchema
        extends
            Iterable<ITable>,
            IJsonForm,
            IXmlForm {

    String K_METADATA = "metadata";
    String K_TABLES = "tables";
    String K_TABLE = "table";

    ISchemaMetadata getMetadata();

    ICatalog getParent();

    SchemaId getId();

    default String getName() {
        return getId().getSchemaName();
    }

    default boolean isAttached() {
        if (getParent() == null)
            return false;
        ISchemaMetadata metadata = getMetadata();
        if (metadata == null)
            return false;
        if (metadata.getParent() == null)
            return false;
        return true;
    }

    Map<String, ITable> getTables();

    default int getTableCount() {
        return getTables().size();
    }

    default ITable getTable(String name) {
        return getTables().get(name);
    }

    default ITable getTable(String name, boolean ignoreCase) {
        if (ignoreCase)
            name = getCanonicalName(name);
        return getTable(name);
    }

    default String getCanonicalName(String name) {
        return getMetadata().getCanonicalName(name);
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        ISchemaMetadata metadata = getMetadata();
        out.key(K_METADATA);
        metadata.jsonOut(out, opts, true);

        out.key(K_TABLES);
        out.object();
        for (String key : getTables().keySet()) {
            out.key(key);
            ITable table = getTable(key);
            if (table == null) {
                out.value(null);
                continue;
            }
            out.object();
            table.jsonOut(out, opts);
            out.endObject();
        }
        out.endObject();
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        ISchemaMetadata metadata = getMetadata();
        out.beginElement(K_METADATA);
        metadata.writeObject(out);
        out.endElement();

        out.beginElement(K_TABLES);
        for (String key : getTables().keySet()) {
            ITable table = getTable(key);
            if (table == null)
                continue;
            out.beginElement(K_TABLE);
            table.writeObject(out);
            out.endElement();
        }
        out.endElement();
    }

}
