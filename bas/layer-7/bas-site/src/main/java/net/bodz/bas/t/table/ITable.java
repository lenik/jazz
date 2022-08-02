package net.bodz.bas.t.table;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ITable
        extends
            IRowSet {

    String K_CATALOG_NAME = "catalogName";
    String K_SCHEMA_NAME = "schemaName";
    String K_TABLE_NAME = "tableName";
    String K_QUALIFIED_NAME = "qName";

    ISchema getParent();

    @Override
    ITableMetadata getMetadata();

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        ITableMetadata metadata = getMetadata();
        boolean mergeMetadata = metadata != null && metadata.getParent() != null;

        if (mergeMetadata) {
            // out.entry(K_QUALIFIED_NAME, metadata.getQualifiedName());
            out.entry(K_CATALOG_NAME, metadata.getCatalogName());
            out.entry(K_SCHEMA_NAME, metadata.getSchemaName());
            out.entry(K_TABLE_NAME, metadata.getName());
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
        ITableMetadata metadata = getMetadata();
        boolean mergeMetadata = metadata != null && metadata.getParent() != null;

        if (!mergeMetadata) {
            metadata = getMetadata();
            out.beginElement(K_METADATA);
            metadata.writeObject(out);
            out.endElement();
        }

        out.beginElement(K_ROWS);
        if (mergeMetadata) {
            // out.attribute(K_QUALIFIED_NAME, metadata.getQualifiedName());
            out.attribute(K_CATALOG_NAME, metadata.getCatalogName());
            out.attribute(K_SCHEMA_NAME, metadata.getSchemaName());
            out.attribute(K_TABLE_NAME, metadata.getName());
        }
        for (IRow row : this)
            row.writeObjectBoxed(out);
        out.endElement();
    }

}
