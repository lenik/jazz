package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.sql.ResultSetMetaData;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;

public interface IColumnMetadata
        extends
            IJavaName,
            IJsonForm,
            IXmlForm {

    public static final String K_POSITION = "position";
    public static final String K_NAME = "name";
    public static final String K_JAVA_NAME = "javaName";
    public static final String K_LABEL = "label";
    public static final String K_DESCRIPTION = "description";
    public static final String K_TYPE = "type";
    public static final String K_SQL_TYPE = "SQLType";
    public static final String K_PRIMARY_KEY = "primaryKey";
    public static final String K_AUTO_INCREMENT = "autoIncrement";
    public static final String K_CASE_SENSITIVE = "caseSensitive";
    public static final String K_SEARCHABLE = "searchable";
    public static final String K_CURRENCY = "currency";
    public static final String K_UNIQUE = "unique";
    public static final String K_NULLABLE_STATUS = "nullableStatus";
    public static final String K_NULLABLE = "nullable";
    public static final String K_SIGNED = "signed";
    public static final String K_READ_ONLY = "readOnly";
    public static final String K_WRITABLE = "writable";
    public static final String K_DEFINITELY_WRITABLE = "definitelyWritable";
    public static final String K_COLUMN_DISPLAY_SIZE = "columnDisplaySize";
    public static final String K_PRECISION = "precision";
    public static final String K_SCALE = "scale";
    public static final String K_DEFAULT_VALUE = "defaultValue";

    IRowSetMetadata getParent();

    default int position() {
        int pos = getPositionOpt();
        if (pos == -1)
            throw new NoSuchKeyException(getName());
        return pos;
    }

    default int getPositionOpt() {
        IRowSetMetadata parent = getParent();
        if (parent == null)
            throw new IllegalStateException("Parent wasn't set.");
        return parent.indexOfColumn(getName());
    }

    String getName();

    String getLabel();

    String getDescription();

    Class<?> getType();

    int getSqlType();

    boolean isPrimaryKey();

    boolean isAutoIncrement();

    boolean isCaseSensitive();

    boolean isSearchable();

    boolean isCurrency();

    boolean isUnique();

    /**
     * @see ResultSetMetaData#columnNoNulls
     * @see ResultSetMetaData#columnNullable
     * @see ResultSetMetaData#columnNullableUnknown
     */
    int getNullableStatus();

    boolean isNullable();

    boolean isNullable(boolean fallback);

    boolean isSigned();

    boolean isReadOnly();

    boolean isWritable();

    boolean isDefinitelyWritable();

    int getColumnDisplaySize();

    int getPrecision();

    int getScale();

    String getDefaultValue();

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
        if (getParent() != null)
            out.entry(K_POSITION, getPositionOpt());
        out.entry(K_NAME, getName());

        out.entryNotNull(K_JAVA_NAME, getJavaName());
        out.entryNotNull(K_LABEL, getLabel());
        out.entryNotNull(K_DESCRIPTION, getDescription());

        out.entry(K_TYPE, getType().getName());
        out.entry(K_SQL_TYPE, SQLTypes.getTypeName(getSqlType()));
        out.entry(K_PRIMARY_KEY, isPrimaryKey());
        out.entry(K_AUTO_INCREMENT, isAutoIncrement());
        out.entry(K_CASE_SENSITIVE, isCaseSensitive());
        out.entry(K_SEARCHABLE, isSearchable());
        out.entry(K_CURRENCY, isCurrency());
        out.entry(K_UNIQUE, isUnique());
        out.entry(K_NULLABLE_STATUS, getNullableStatus());
        out.entry(K_NULLABLE, isNullable());
        out.entry(K_SIGNED, isSigned());
        out.entry(K_READ_ONLY, isReadOnly());
        out.entry(K_WRITABLE, isWritable());
        out.entry(K_DEFINITELY_WRITABLE, isDefinitelyWritable());
        out.entry(K_COLUMN_DISPLAY_SIZE, getColumnDisplaySize());
        out.entry(K_PRECISION, getPrecision());
        out.entry(K_SCALE, getScale());

        out.entryNotNull(K_DEFAULT_VALUE, getDefaultValue());
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        if (getParent() != null)
            out.attribute(K_POSITION, getPositionOpt());

        out.attribute(K_NAME, getName());

        out.attributeNotNull(K_JAVA_NAME, getJavaName());
        out.attributeNotNull(K_LABEL, getLabel());
        out.attributeNotNull(K_DESCRIPTION, getDescription());

        out.attribute(K_TYPE, getType().getName());
        out.attribute(K_SQL_TYPE, SQLTypes.getTypeName(getSqlType()));
        out.attributeIf(K_PRIMARY_KEY, isPrimaryKey());
        out.attributeIf(K_AUTO_INCREMENT, isAutoIncrement());
        out.attributeIf(K_CASE_SENSITIVE, isCaseSensitive());
        out.attributeIf(K_SEARCHABLE, isSearchable());
        out.attributeIf(K_CURRENCY, isCurrency());
        out.attributeIf(K_UNIQUE, isUnique());
        out.attribute(K_NULLABLE_STATUS, getNullableStatus());
        out.attributeIf(K_NULLABLE, isNullable());
        out.attributeIf(K_SIGNED, isSigned());
        out.attributeIf(K_READ_ONLY, isReadOnly());
        out.attributeIf(K_WRITABLE, isWritable());
        out.attributeIf(K_DEFINITELY_WRITABLE, isDefinitelyWritable());
        out.attribute(K_COLUMN_DISPLAY_SIZE, getColumnDisplaySize());
        out.attribute(K_PRECISION, getPrecision());
        out.attribute(K_SCALE, getScale());

        out.attribute(K_DEFAULT_VALUE, getDefaultValue());
    }

    default void accept(ICatalogVisitor visitor) {
        visitor.column(this);
    }

}
