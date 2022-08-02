package net.bodz.bas.t.table;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ISchema
        extends
            ITableMap {

    String K_CATALOG_NAME = "catalogName";
    String K_SCHEMA_NAME = "schemaName";
    String K_QUALIFIED_NAME = "qName";

    // ICatalog getParent();

    @Override
    ISchemaMetadata getMetadata();

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        ITableMap.super.writeObject(out);
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        ITableMap.super.writeObject(out);
    }

}
