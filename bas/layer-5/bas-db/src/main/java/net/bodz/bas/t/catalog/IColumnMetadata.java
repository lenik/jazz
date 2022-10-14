package net.bodz.bas.t.catalog;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.t.order.IOrdinal;

public interface IColumnMetadata
        extends
            IJavaName,
            IOrdinal,
            IJsonForm,
            IXmlForm {

    public static final String K_ORDINAL_POSITION = "ordinal";
    public static final String K_NAME = "name";
    public static final String K_JAVA_NAME = "javaName";
    public static final String K_LABEL = "label";
    public static final String K_DESCRIPTION = "description";
    public static final String K_TYPE = "type";
    public static final String K_SQL_TYPE = "SQLType";
    public static final String K_SQL_TYPE_NAME = "SQLTypeName";
    public static final String K_PRIMARY_KEY = "primaryKey";
    public static final String K_AUTO_INCREMENT = "autoIncrement";
    public static final String K_CASE_SENSITIVE = "caseSensitive";
    public static final String K_SEARCHABLE = "searchable";
    public static final String K_CURRENCY = "currency";
    public static final String K_UNIQUE = "unique";
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

    String getName();

    default Phrase nam() {
        return Phrase.foo_bar(getName());
    }

    String getLabel();

    String getDescription();

    Class<?> getType();

    JdbcType getJdbcType();

    String getSqlTypeName();

    boolean isPrimaryKey();

    Boolean getAutoIncrement();

    default boolean isAutoIncrement(boolean defaultValue) {
        Boolean b = getAutoIncrement();
        return b != null ? b : defaultValue;
    }

    boolean isCaseSensitive();

    boolean isSearchable();

    boolean isCurrency();

    boolean isUnique();

    Boolean getNullable();

    default boolean isNullable(boolean defaultValue) {
        Boolean b = getNullable();
        return b != null ? b : defaultValue;
    }

    Boolean getSigned();

    default boolean isSigned(boolean defaultValue) {
        Boolean b = getSigned();
        return b != null ? b : defaultValue;
    }

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
        out.entry(K_ORDINAL_POSITION, getOrdinal());
        out.entry(K_NAME, getName());

        out.entryNotNull(K_JAVA_NAME, getJavaName());
        out.entryNotNull(K_LABEL, getLabel());
        out.entryNotNull(K_DESCRIPTION, getDescription());

        out.entry(K_TYPE, getType().getName());
        out.entry(K_SQL_TYPE, getJdbcType().name());
        out.entryNotNull(K_SQL_TYPE_NAME, getSqlTypeName());
        out.entryTrue(K_PRIMARY_KEY, isPrimaryKey());
        out.entryNotNull(K_AUTO_INCREMENT, getAutoIncrement());
        out.entryTrue(K_CASE_SENSITIVE, isCaseSensitive());
        out.entryTrue(K_SEARCHABLE, isSearchable());
        out.entryTrue(K_CURRENCY, isCurrency());
        out.entryTrue(K_UNIQUE, isUnique());
        out.entryNotNull(K_NULLABLE, getNullable());
        out.entryNotNull(K_SIGNED, getSigned());
        out.entryTrue(K_READ_ONLY, isReadOnly());
        out.entryTrue(K_WRITABLE, isWritable());
        out.entryTrue(K_DEFINITELY_WRITABLE, isDefinitelyWritable());
        out.entry(K_COLUMN_DISPLAY_SIZE, getColumnDisplaySize());
        out.entry(K_PRECISION, getPrecision());
        out.entry(K_SCALE, getScale());

        out.entryNotNull(K_DEFAULT_VALUE, getDefaultValue());
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.attribute(K_ORDINAL_POSITION, getOrdinal());
        out.attribute(K_NAME, getName());

        out.attributeNotNull(K_JAVA_NAME, getJavaName());
        out.attributeNotNull(K_LABEL, getLabel());
        out.attributeNotNull(K_DESCRIPTION, getDescription());

        out.attribute(K_TYPE, getType().getName());
        out.attribute(K_SQL_TYPE, getJdbcType().name());
        out.attribute(K_SQL_TYPE_NAME, getSqlTypeName());

        out.attributeIf(K_PRIMARY_KEY, isPrimaryKey());
        out.attributeNotNull(K_AUTO_INCREMENT, getAutoIncrement());
        out.attributeIf(K_CASE_SENSITIVE, isCaseSensitive());
        out.attributeIf(K_SEARCHABLE, isSearchable());
        out.attributeIf(K_CURRENCY, isCurrency());
        out.attributeIf(K_UNIQUE, isUnique());
        out.attributeNotNull(K_NULLABLE, getNullable());
        out.attributeNotNull(K_SIGNED, getSigned());
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
