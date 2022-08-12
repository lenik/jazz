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

public interface ICatalog
        extends
            Iterable<ISchema>,
            IJsonForm,
            IXmlForm {

    String K_METADATA = "metadata";
    String K_SCHEMAS = "schemas";
    String K_SCHEMA = "schema";

    ICatalogMetadata getMetadata();

    default String getName() {
        ICatalogMetadata metadata = getMetadata();
        return metadata == null ? null : metadata.getName();
    }

    default String getCanonicalName(String name) {
        return getMetadata().getCanonicalName(name);
    }

    Map<String, ISchema> getSchemas();

    default int getSchemaCount() {
        return getSchemas().size();
    }

    default ISchema getSchema(String name) {
        return getSchemas().get(name);
    }

    default ISchema getSchema(String name, boolean ignoreCase) {
        if (ignoreCase)
            name = getCanonicalName(name);
        return getSchema(name);
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        ICatalogMetadata metadata = getMetadata();
        out.key(K_METADATA);
        metadata.jsonOut(out, opts, true);

        out.key(K_SCHEMAS);
        out.object();
        for (String key : getSchemas().keySet()) {
            out.key(key);
            ISchema schema = getSchema(key);
            if (schema == null) {
                out.value(null);
                continue;
            }
            out.object();
            schema.jsonOut(out, opts);
            out.endObject();
        }
        out.endObject();
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        ICatalogMetadata metadata = getMetadata();
        out.beginElement(K_METADATA);
        metadata.writeObject(out);
        out.endElement();

        out.beginElement(K_SCHEMAS);
        for (String key : getSchemas().keySet()) {
            ISchema schema = getSchema(key);
            if (schema == null)
                continue;
            out.beginElement(K_SCHEMA);
            schema.writeObject(out);
            out.endElement();
        }
        out.endElement();
    }

}
