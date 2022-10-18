package net.bodz.bas.t.catalog;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlOutput;

public interface ITableViewMetadata
        extends
            IRowSetMetadata,
            IJavaName,
            IJDBCMetaDataSupport {

    String K_JAVA_NAME = "javaName";
    String K_TABLE_TYPE = "tableType";
    String K_DESCRIPTION = "description";

    String K_JAVA_TYPE = "javaType";

    TableOid getId();

    TableType getTableType();

    default Phrase nam() {
        return Phrase.dual(getName(), getJavaName());
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

    TableKey getPrimaryKey();

    default IColumnMetadata[] getPrimaryKeyColumns() {
        TableKey key = getPrimaryKey();
        if (key == null)
            return new IColumnMetadata[0];
        IColumnMetadata[] keyColumns = key.resolve(this);
        // Arrays.sort(keyColumns, OrdinalComparator.INSTANCE);
        return keyColumns;
    }

    default String getJavaType() {
        return null;
    }

    default void wireUp() {
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        getId().jsonOut(out, opts);

        out.entry(K_TABLE_TYPE, getTableType());

        out.entryNotNull(K_JAVA_NAME, getJavaName());
        out.entryNotNull(K_JAVA_TYPE, getJavaType());
        out.entryNotNull(K_DESCRIPTION, getDescription());

        IRowSetMetadata.super.jsonOut(out, opts);
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        getId().writeObject(out);

        out.attribute(K_TABLE_TYPE, getTableType());

        out.attributeNotNull(K_JAVA_NAME, getJavaName());
        out.attributeNotNull(K_JAVA_TYPE, getJavaType());
        out.attributeNotNull(K_DESCRIPTION, getDescription());

        IRowSetMetadata.super.writeObject(out);
    }

    void accept(ICatalogVisitor visitor);

}
