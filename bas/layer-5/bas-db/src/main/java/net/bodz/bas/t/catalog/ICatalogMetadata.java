package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.db.sql.dialect.ISqlDialect;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.t.tuple.QualifiedName;

public interface ICatalogMetadata
        extends Iterable<ISchemaMetadata>,
                IJavaType,
                ISchemaDirectory,
                ITableDirectory,
                ICrossRefAnalyzer,
                IJsonForm,
                IXmlForm,
                IJDBCMetaDataSupport {

    String K_NAME = "name";
    String K_JAVA_TYPE = "javaType";
    String K_SCHEMAS = "schemas";
    String K_SCHEMA = "schema";

    String getName();

    String getCanonicalName(String name);

    ISqlDialect getDialect();

    /**
     * key: canonical name
     */
    Map<String, ? extends ISchemaMetadata> getSchemas();

    int getSchemaCount();

    ISchemaMetadata getSchema(String name);

    default ISchemaMetadata getDefaultSchema() {
        return getSchema((String) null);
    }

    default ISchemaMetadata findSchema(String name, boolean ignoreCase) {
        if (ignoreCase)
            name = getCanonicalName(name);
        return getSchema(name);
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry(K_NAME, getName());

        QualifiedName javaType = getJavaType();
        if (javaType != null)
            out.entry(K_JAVA_TYPE, javaType.getFullName());

        out.key(K_SCHEMAS);
        out.object();
        for (String key : getSchemas().keySet()) {
            out.key(key);
            ISchemaMetadata schema = getSchema(key);
            if (schema == null) {
                out.value(null);
                continue;
            }
            schema.jsonOutValue(out, opts);
        }
        out.endObject();
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.attribute(K_NAME, getName());

        QualifiedName javaType = getJavaType();
        if (javaType != null)
            out.attribute(K_JAVA_TYPE, javaType.getFullName());

        out.beginElement(K_SCHEMAS);
        for (String key : getSchemas().keySet()) {
            ISchemaMetadata schema = getSchema(key);
            if (schema == null)
                continue;
            out.beginElement(K_SCHEMA);
            schema.writeObject(out);
            out.endElement();
        }
        out.endElement();
    }

    default void accept(ICatalogVisitor visitor) {
        visitor.beginCatalog(this);
        for (ISchemaMetadata schema : getSchemas().values())
            schema.accept(visitor);
        visitor.endCatalog(this);
    }

}
