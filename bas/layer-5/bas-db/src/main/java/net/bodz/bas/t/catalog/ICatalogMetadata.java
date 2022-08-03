package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ICatalogMetadata
        extends
            Iterable<ISchemaMetadata>,
            IJsonForm,
            IXmlForm,
            IJDBCMetaDataSupport {

    String K_NAME = "name";
    String K_SCHEMAS = "schemas";
    String K_SCHEMA = "schema";

    String getName();

    String getCanonicalName(String name);

    /**
     * key: canonical name
     */
    Map<String, ? extends ISchemaMetadata> getSchemas();

    int getSchemaCount();

    ISchemaMetadata getSchema(String name);

    default ISchemaMetadata findSchema(String name, boolean ignoreCase) {
        if (ignoreCase)
            name = getCanonicalName(name);
        return getSchema(name);
    }

    default List<ITableMetadata> findTables(QualifiedTableName qName) {
        if (!NamePattern.matches(getName(), qName.getCatalogName()))
            return Collections.emptyList();
        List<ITableMetadata> list = new ArrayList<>();
        for (ISchemaMetadata schema : this) {
            if (schema.getQName().contains(qName)) {
                ITableMetadata table = schema.findTable(qName.tableName, false);
                if (table != null)
                    list.add(table);
            }
        }
        return list;
    }

    default ITableMetadata getTable(QualifiedTableName qName) {
        List<ITableMetadata> list = findTables(qName);
        if (list.size() > 1)
            throw new DuplicatedKeyException("More than single table matched: " + qName);
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        out.entry(K_NAME, getName());

        out.key(K_SCHEMAS);
        out.object();
        for (String key : getSchemas().keySet()) {
            out.key(key);
            ISchemaMetadata schema = getSchema(key);
            if (schema == null) {
                out.value(null);
                continue;
            }
            out.object();
            schema.writeObject(out);
            out.endObject();
        }
        out.endObject();
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.attribute(K_NAME, getName());

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
