package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.db.jdbc.util.IResultColumnMetaData;
import net.bodz.bas.db.sql.DataType;
import net.bodz.bas.db.sql.dialect.ISqlDialect;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.t.order.IOrdinal;
import net.bodz.bas.t.tuple.QualifiedName;

public interface IColumnMetadata
        extends IJavaQName,
                IOrdinal,
                IResultColumnMetaData,
                IJsonForm,
                IXmlForm {

    String K_ORDINAL_POSITION = "ordinal";
    String K_NAME = "name";
    String K_Q_NAME = "javaName";
    String K_LABEL = "label";
    String K_DESCRIPTION = "description";
    String K_TYPE = "type";
    String K_SQL_TYPE = "SQLType";
    String K_SQL_TYPE_NAME = "SQLTypeName";
    String K_SQL_CLASS_NAME = "SQLClassName";
    String K_PRIMARY_KEY = "primaryKey";
    String K_AUTO_INCREMENT = "autoIncrement";
    String K_CASE_SENSITIVE = "caseSensitive";
    String K_SEARCHABLE = "searchable";
    String K_CURRENCY = "currency";
    String K_UNIQUE = "unique";
    String K_NULLABLE = "nullable";
    String K_SIGNED = "signed";
    String K_READ_ONLY = "readOnly";
    String K_WRITABLE = "writable";
    String K_DEFINITELY_WRITABLE = "definitelyWritable";
    String K_COLUMN_DISPLAY_SIZE = "columnDisplaySize";
    String K_PRECISION = "precision";
    String K_SCALE = "scale";
    String K_DEFAULT_VALUE = "defaultValue";

    String K_EXCLUDED = "excluded";
    String K_VERBOSE_LEVEL = "verboseLevel";
    String K_JOIN_LEVEL = "joinLevel";

    IRowSetMetadata getParent();

    ITableMetadata getTable();

    default ISchemaMetadata getSchema() {
        ITableMetadata table = getTable();
        return table == null ? null : table.getParent();
    }

    default ICatalogMetadata getCatalog() {
        ISchemaMetadata schema = getSchema();
        return schema == null ? null : schema.getParent();
    }

    default ISqlDialect getDialect() {
        ICatalogMetadata catalog = getCatalog();
        return catalog == null ? null : catalog.getDialect();
    }

    default ISqlDialect getDialect(ISqlDialect fallback) {
        ISqlDialect dialect = getDialect();
        return dialect == null ? fallback : dialect;
    }

//    default int position() {
//        int pos = getPositionOpt();
//        if (pos == -1)
//            throw new NoSuchKeyException(getName());
//        return pos;
//    }
//
//    default int getPositionOpt() {
//        IRowSetMetadata parent = getParent();
//        if (parent == null)
//            throw new IllegalStateException("Parent wasn't set.");
//        return parent.indexOfColumn(getName());
//    }

    ColumnOid getId();

    int getColumnIndex();

    String getName();

    boolean isJavaNameComplete();

    default boolean isPropertyOfComposite() {
        QualifiedName qProperty = getJavaQName();
        if (qProperty == null)
            return false;
        return qProperty.packageName != null;
    }

    String getLabel();

    String getDescription();

    Class<?> getJavaClass();

    default Class<?> getActualClass() {
        IProperty property = getProperty();
        if (property != null) {
            Class<?> propertyClass = property.getPropertyClass();
            if (propertyClass != null)
                return propertyClass;
        }

        Class<?> javaClass = getJavaClass();
        if (javaClass != null)
            return javaClass;

        return getSqlClass();
    }

    DataType getDataType();

    int getSqlType();

    default SqlTypeEnum getSqlTypeEnum() {
        return SqlTypeEnum.forSQLType(getSqlType(), SqlTypeEnum.OTHER);
    }

    String getSqlTypeName();

    String getSqlClassName();

    Class<?> getSqlClass();

    boolean isPrimaryKey();

    boolean isForeignKey();

    IColumnMetadata getParentColumn();

    boolean isUnique();

    @Override
    default String getCatalogName() {
        ICatalogMetadata catalog = getCatalog();
        return catalog == null ? null : catalog.getName();
    }

    @Override
    default String getColumnLabel() {
        return getLabel();
    }

    @Override
    default String getColumnName() {
        return getName();
    }

    @Override
    default String getSchemaName() {
        ISchemaMetadata schema = getSchema();
        return schema == null ? null : schema.getName();
    }

    @Override
    default String getTableName() {
        ITableMetadata table = getTable();
        return table == null ? null : table.getName();
    }

    @Override
    default int getColumnType() {
        return getSqlType();
    }

    @Override
    default String getColumnTypeName() {
        return getSqlTypeName();
    }

    @Override
    default String getColumnClassName() {
        return getSqlClassName();
    }

    @Override
    boolean isSigned();

    @Override
    boolean isAutoIncrement();

    @Override
    boolean isCaseSensitive();

    @Override
    boolean isSearchable();

    @Override
    boolean isCurrency();

    @Override
    boolean isReadOnly();

    @Override
    boolean isWritable();

    @Override
    boolean isDefinitelyWritable();

    @Override
    int getColumnDisplaySize();

    @Override
    int getPrecision();

    @Override
    int getScale();

    String getPrecisionExpr();

    String getScaleExpr();

    default boolean hasDefaultValue() {
        return getDefaultValue() != null;
    }

    String getDefaultValue();

    int getVerboseLevel();

    int getJoinLevel();

    Object parseColumnValue(String s)
            throws ParseException;

    Object readColumnJsonValue(Object jsonValue)
            throws ParseException;

    void writeColumnInJson(IJsonOut out, Object value)
            throws IOException, FormatException;

    Object readColumnXmlValue(IElement enclosing)
            throws ParseException, LoaderException;

    void writeColumnInXml(IXmlOutput out, Object value)
            throws XMLStreamException, FormatException;

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry(K_ORDINAL_POSITION, getOrdinal());
        out.entry(K_NAME, getName());

        out.entryNotNull(K_Q_NAME, getJavaQName());
        out.entryNotNull(K_LABEL, getLabel());
        out.entryNotNull(K_DESCRIPTION, getDescription());

        out.entry(K_TYPE, getJavaClass().getName());
        out.entry(K_SQL_TYPE, getSqlType());
        out.entryNotNull(K_SQL_TYPE_NAME, getSqlTypeName());
        out.entryNotNull(K_SQL_CLASS_NAME, getSqlClassName());
        out.entryTrue(K_PRIMARY_KEY, isPrimaryKey());
        out.entryTrue(K_AUTO_INCREMENT, isAutoIncrement());
        out.entryTrue(K_CASE_SENSITIVE, isCaseSensitive());
        out.entryTrue(K_SEARCHABLE, isSearchable());
        out.entryTrue(K_CURRENCY, isCurrency());
        out.entryTrue(K_UNIQUE, isUnique());
        out.entryNotNull(K_NULLABLE, getNullableType());
        out.entryNotNull(K_SIGNED, isSigned());
        out.entryTrue(K_READ_ONLY, isReadOnly());
        out.entryTrue(K_WRITABLE, isWritable());
        out.entryTrue(K_DEFINITELY_WRITABLE, isDefinitelyWritable());
        out.entry(K_COLUMN_DISPLAY_SIZE, getColumnDisplaySize());
        out.entry(K_PRECISION, getPrecision());
        out.entry(K_SCALE, getScale());

        out.entryNotNull(K_DEFAULT_VALUE, getDefaultValue());

        out.entryTrue(K_EXCLUDED, isExcluded());
        out.entryNot0(K_VERBOSE_LEVEL, getVerboseLevel());
        out.entryNot0(K_JOIN_LEVEL, getJoinLevel());
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.attribute(K_ORDINAL_POSITION, getOrdinal());
        out.attribute(K_NAME, getName());

        out.attributeNotNull(K_Q_NAME, getJavaQName());
        out.attributeNotNull(K_LABEL, getLabel());
        out.attributeNotNull(K_DESCRIPTION, getDescription());

        out.attribute(K_TYPE, getJavaClass().getName());
        out.attribute(K_SQL_TYPE, getSqlType());
        out.attribute(K_SQL_TYPE_NAME, getSqlTypeName());
        out.attribute(K_SQL_CLASS_NAME, getSqlClassName());

        out.attributeIf(K_PRIMARY_KEY, isPrimaryKey());
        out.attributeNotNull(K_AUTO_INCREMENT, isAutoIncrement());
        out.attributeIf(K_CASE_SENSITIVE, isCaseSensitive());
        out.attributeIf(K_SEARCHABLE, isSearchable());
        out.attributeIf(K_CURRENCY, isCurrency());
        out.attributeIf(K_UNIQUE, isUnique());
        out.attributeNotNull(K_NULLABLE, getNullableType());
        out.attributeNotNull(K_SIGNED, isSigned());
        out.attributeIf(K_READ_ONLY, isReadOnly());
        out.attributeIf(K_WRITABLE, isWritable());
        out.attributeIf(K_DEFINITELY_WRITABLE, isDefinitelyWritable());
        out.attribute(K_COLUMN_DISPLAY_SIZE, getColumnDisplaySize());
        out.attribute(K_PRECISION, getPrecision());
        out.attribute(K_SCALE, getScale());

        out.attribute(K_DEFAULT_VALUE, getDefaultValue());

        out.attributeTrue(K_EXCLUDED, isExcluded());
        out.attributeNot0(K_VERBOSE_LEVEL, getVerboseLevel());
        out.attributeNot0(K_JOIN_LEVEL, getJoinLevel());
    }

    default void accept(ICatalogVisitor visitor) {
        visitor.column(this);
    }

    // Java binding

    boolean isExcluded();

    IProperty getProperty();

    <A extends Annotation> A getAnnotation(Class<A> annotationType);

}
