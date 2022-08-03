package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ITableMetadata
        extends
            IRowSetMetadata,
            IJDBCMetaDataSupport {

    String K_DEFAULT_NAME = "defaultName";
    String K_PRIMARY_KEY = "primaryKey";
    String K_FOREIGN_KEYS = "foreignKeys";
    String K_FOREIGN_KEY = "foreignKey";

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

    Map<String, CrossReference> getForeignKeys();

    CrossReference getForeignKey(String constraintName);

    default Set<QualifiedTableName> getParentTableNames() {
        Set<QualifiedTableName> parents = new LinkedHashSet<>();
        for (CrossReference ref : getForeignKeys().values()) {
            QualifiedTableName parent = ref.getParentKey().getQName();
            parents.add(parent);
        }
        return parents;
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

        Map<String, CrossReference> foreignKeys = getForeignKeys();
        if (!foreignKeys.isEmpty()) {
            out.key(K_FOREIGN_KEYS);
            out.array();
            for (CrossReference ref : foreignKeys.values())
                ref.writeObjectBoxed(out);
            out.endArray();
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

        Map<String, CrossReference> foreignKeys = getForeignKeys();
        if (!foreignKeys.isEmpty()) {
            out.beginElement(K_FOREIGN_KEYS);
            for (CrossReference ref : foreignKeys.values()) {
                out.beginElement(K_FOREIGN_KEY);
                ref.writeObject(out);
                out.endElement();
            }
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
