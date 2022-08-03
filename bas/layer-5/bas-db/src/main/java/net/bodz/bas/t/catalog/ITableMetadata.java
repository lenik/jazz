package net.bodz.bas.t.catalog;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ITableMetadata
        extends
            IRowSetMetadata {

    String K_PRIMARY_KEY = "primaryKey";
    String K_DEFAULT_NAME = "defaultName";

    QualifiedTableName getQName();

    QualifiedTableName getDefaultName();

    default String getName() {
        return getQName().getTableName();
    }

    default String getCompactName() {
        return getCompactName(false);
    }

    default String getCompactName(boolean ignoreCase) {
        return getQName().getCompactName(getDefaultName());
    }

    @Override
    ISchemaMetadata getParent();

    TableKey getPrimaryKey();

    default IColumnMetadata[] getPrimaryKeyColumns() {
        return getPrimaryKey().resolve(this);
    }

    @Override
    default void writeObject(IJsonOut out)
            throws IOException, FormatException {
        getQName().writeObject(out);

        IRowSetMetadata.super.writeObject(out);

        TableKey primaryKey = getPrimaryKey();
        if (primaryKey != null) {
            out.key(K_PRIMARY_KEY);
            primaryKey.writeObject(out);
        }
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        getQName().writeObject(out);

        IRowSetMetadata.super.writeObject(out);

        TableKey primaryKey = getPrimaryKey();
        if (primaryKey != null) {
            out.beginElement(K_PRIMARY_KEY);
            primaryKey.writeObject(out);
            out.endElement();
        }
    }

    default void accept(ICatalogVisitor visitor) {
        visitor.beginTable(this);

        visitor.beginRowSet(this);
        int n = getColumnCount();
        for (int i = 0; i < n; i++)
            getColumn(i).accept(visitor);
        visitor.endRowSet(this);

        visitor.endTable(this);
    }

}
