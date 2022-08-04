package net.bodz.bas.t.catalog;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ITableViewMetadata
        extends
            IRowSetMetadata,
            IJDBCMetaDataSupport {

    String K_TABLE_TYPE = "tableType";

    TableId getId();

    TableType getTableType();

    default String getName() {
        return getId().getTableName();
    }

    default String getCompactName() {
        return getCompactName(false);
    }

    default String getCompactName(boolean ignoreCase) {
        ISchemaMetadata schema = getParent();
        if (schema == null)
            return getId().getFullName();
        else
            return getId().getCompactName(schema.getId());
    }

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        getId().writeObject(out);
        out.entry(K_TABLE_TYPE, getTableType());
        IRowSetMetadata.super.writeObject(out);
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        getId().writeObject(out);
        out.attribute(K_TABLE_TYPE, getTableType());
        IRowSetMetadata.super.writeObject(out);
    }

    void accept(ICatalogVisitor visitor);

}