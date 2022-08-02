package net.bodz.bas.t.table;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ISchemaMetadata
        extends
            ITableMapMetadata {

    String K_NAME = "name";

    String getCatalogName();

    String getName();

    String getQualifiedName();

    String getNecessaryQualifiedName();

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        out.entry(K_NAME, getName());
        ITableMapMetadata.super.writeObject(out);
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.attribute(K_NAME, getName());
        ITableMapMetadata.super.writeObject(out);
    }

}
