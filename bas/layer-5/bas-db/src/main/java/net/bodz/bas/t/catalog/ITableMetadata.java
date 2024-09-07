package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.potato.element.IType;

public interface ITableMetadata
        extends
            IRowSetMetadata,
            IJavaType,
            IJDBCMetaDataSupport {

    String K_TABLE_TYPE = "tableType";
    String K_DESCRIPTION = "description";

    String K_JAVA_TYPE = "javaType";
    String K_BASE_TYPE = "baseType";
    String K_EXCLUDED = "excluded";

    String K_PRIMARY_KEY = "primaryKey";
    String K_FOREIGN_KEYS = "foreignKeys";
    String K_CROSS_REF = "reference";

    TableOid getId();

    TableType getTableType();

    default Phrase nam() {
        return Phrase.dual(getName(), getJavaType().name);
    }

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

    String getDescription();

    default String getBaseTypeName() {
        return null;
    }

    TableKey getPrimaryKey();

    default IColumnMetadata[] getPrimaryKeyColumns() {
        TableKey key = getPrimaryKey();
        if (key == null)
            return new IColumnMetadata[0];
        IColumnMetadata[] keyColumns = key.resolve(this);
        // Arrays.sort(keyColumns, OrdinalComparator.INSTANCE);
        return keyColumns;
    }

    Map<String, CrossReference> getForeignKeys();

    CrossReference getForeignKey(String constraintName);

    CrossReference getForeignKeyFromColumn(String columnName);

    default Set<TableOid> getParentTableNames() {
        Set<TableOid> parents = new LinkedHashSet<>();
        for (CrossReference ref : getForeignKeys().values()) {
            TableOid parent = ref.getParentKey().getId();
            parents.add(parent);
        }
        return parents;
    }

    default void wireUp() {
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        getId().jsonOut(out, opts);

        out.entry(K_TABLE_TYPE, getTableType());

        out.entryNotNull(K_JAVA_TYPE, getJavaType().getFullName());
        out.entryNotNull(K_BASE_TYPE, getBaseTypeName());
        out.entryTrue(K_EXCLUDED, isExcluded());

        out.entryNotNull(K_DESCRIPTION, getDescription());

        IRowSetMetadata.super.jsonOut(out, opts);

        TableKey primaryKey = getPrimaryKey();
        if (primaryKey != null) {
            out.key(K_PRIMARY_KEY);
            primaryKey.jsonOut(out, opts);
        }

        Map<String, CrossReference> foreignKeys = getForeignKeys();
        if (! foreignKeys.isEmpty()) {
            out.key(K_FOREIGN_KEYS);
            out.array();
            for (CrossReference ref : foreignKeys.values())
                ref.jsonOutValue(out, opts);
            out.endArray();
        }

    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        getId().writeObject(out);

        out.attribute(K_TABLE_TYPE, getTableType());

        out.attributeNotNull(K_JAVA_TYPE, getJavaType().getFullName());
        out.attributeNotNull(K_BASE_TYPE, getBaseTypeName());
        out.attributeTrue(K_EXCLUDED, isExcluded());

        out.attributeNotNull(K_DESCRIPTION, getDescription());

        IRowSetMetadata.super.writeObject(out);

        TableKey primaryKey = getPrimaryKey();
        if (primaryKey != null) {
            out.beginElement(K_PRIMARY_KEY);
            primaryKey.writeObject(out);
            out.endElement();
        }

        Map<String, CrossReference> foreignKeys = getForeignKeys();
        if (! foreignKeys.isEmpty()) {
            out.beginElement(K_FOREIGN_KEYS);
            for (CrossReference ref : foreignKeys.values()) {
                out.beginElement(K_CROSS_REF);
                ref.writeObject(out);
                out.endElement();
            }
            out.endElement();
        }
    }

    void accept(ICatalogVisitor visitor);

    // Java binding

    boolean isExcluded();

    default Class<?> getJavaClass() {
        return null;
    }

    default IType getPotatoType() {
        return null;
    }

    default Class<?> getBaseClass() {
        return null;
    }

    default IType getBaseType() {
        return null;
    }

    default <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        return null;
    }

}
