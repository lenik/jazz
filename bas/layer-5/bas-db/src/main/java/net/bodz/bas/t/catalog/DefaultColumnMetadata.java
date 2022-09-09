package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;

public class DefaultColumnMetadata
        implements
            IColumnMetadata,
            IMutableJavaName {

    IRowSetMetadata parent;

    String name;
    String javaName;

    String label;
    String description; // comment..

    Class<?> type = Integer.class;
    int sqlType = Types.INTEGER;
    boolean jsonType;
    boolean xmlType;

    boolean primaryKey;
    boolean unique;

    boolean autoIncrement;
    boolean caseSensitive;
    boolean searchable;
    boolean currency;
    int nullable = ResultSetMetaData.columnNullableUnknown;
    boolean signed;
    boolean readOnly;
    boolean writable;
    boolean definitelyWritable;

    int columnDisplaySize;
    int precision;
    int scale;

    String defaultValue;

    public DefaultColumnMetadata(IRowSetMetadata parent) {
        if (parent == null)
            throw new NullPointerException("parent");
        this.parent = parent;
    }

    @Override
    public IRowSetMetadata getParent() {
        return parent;
    }

    public void setParent(IRowSetMetadata parent) {
        if (parent == null)
            throw new NullPointerException("parent");
        this.parent = parent;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getJavaName() {
        return javaName;
    }

    @Override
    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
        jsonType = IJsonForm.class.isAssignableFrom(type);
        xmlType = IXmlForm.class.isAssignableFrom(type);
    }

    @Override
    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public void setTypeByName(String typeName)
            throws ParseException {
        Class<?> clazz;
        try {
            clazz = Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new ParseException(e.getMessage(), e);
        }
        setType(clazz);
    }

    @Override
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    @Override
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    @Override
    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    @Override
    public boolean isCurrency() {
        return currency;
    }

    public void setCurrency(boolean currency) {
        this.currency = currency;
    }

    @Override
    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    @Override
    public int getNullableStatus() {
        return nullable;
    }

    public void setNullableStatus(int nullable) {
        this.nullable = nullable;
    }

    @Override
    public boolean isNullable() {
        return nullable == ResultSetMetaData.columnNullable;
    }

    @Override
    public boolean isNullable(boolean fallback) {
        if (nullable == ResultSetMetaData.columnNullableUnknown)
            return fallback;
        else
            return nullable == ResultSetMetaData.columnNullable;
    }

    @Override
    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    @Override
    public boolean isDefinitelyWritable() {
        return definitelyWritable;
    }

    public void setDefinitelyWritable(boolean definitelyWritable) {
        this.definitelyWritable = definitelyWritable;
    }

    @Override
    public int getColumnDisplaySize() {
        return columnDisplaySize;
    }

    public void setColumnDisplaySize(int columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    @Override
    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    @Override
    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Object parseColumnValue(String s)
            throws ParseException {
        IParser<?> parser = Typers.getTyper(type, IParser.class);
        return parser.parse(s);
    }

    @Override
    public Object readColumnJsonValue(Object jsonBox)
            throws ParseException {
        if (jsonType) {
            IJsonForm obj;
            try {
                obj = (IJsonForm) type.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new ParseException("Failed to instantiate " + type, e);
            }
            obj.readObjectBoxed(jsonBox, Convention.JSON_STYLE);
            return obj;
        }

        IParser<?> parser = Typers.getTyper(type, IParser.class);
        if (parser != null) {
            String text = jsonBox.toString();
            Object value = parser.parse(text);
            return value;
        }

        throw new UnsupportedOperationException(//
                "Don't know how to convert json " + jsonBox + " to " + type);
    }

    @Override
    public void writeColumnInJson(IJsonOut out, Object value)
            throws IOException, FormatException {
        if (value == null) {
            out.value(null);
            return;
        }

        if (!type.isInstance(value))
            throw new IllegalArgumentException("Not an instance of " + type);

        if (jsonType) {
            IJsonForm obj = (IJsonForm) value;
            obj.jsonOut(out, Convention.JSON_STYLE, true);
            return;
        }

        JsonFn.writeObject(out, value, null);
    }

    String getPreferredTagName() {
        String tagName = name; // type.getSimpleName();
        return tagName;
    }

    @Override
    public Object readColumnXmlValue(IElement enclosing)
            throws ParseException, LoaderException {
        if (xmlType) {
            IXmlForm obj;
            try {
                obj = (IXmlForm) type.newInstance();
            } catch (Exception e) {
                throw new ParseException("Failed to instantiate " + type, e);
            }
            obj.readObjectBoxed(enclosing);
            return obj;
        }

        String text = enclosing.getTextContent();
        IParser<?> parser = Typers.getTyper(type, IParser.class);
        if (parser != null) {
            Object value = parser.parse(text);
            return value;
        }

        throw new UnsupportedOperationException(//
                "Don't know how to convert xml text content [" + text + "] to " + type);
    }

    @Override
    public void writeColumnInXml(IXmlOutput out, Object value)
            throws XMLStreamException, FormatException {
        if (value != null) {
            if (!type.isInstance(value))
                throw new IllegalArgumentException("Not an instance of " + type);

            if (xmlType) {
                IXmlForm obj = (IXmlForm) value;
                obj.writeObjectBoxed(out);
                return;
            }
        }

        String tagName = getPreferredTagName();
        out.beginElement(tagName);
        String text = String.valueOf(value);
        out.writeCharacters(text);
        out.endElement();
    }

    public void readObject(ResultSetMetaData jdbcMetadata, int columnIndex)
            throws SQLException {
        String name = jdbcMetadata.getColumnName(columnIndex);
        String label = jdbcMetadata.getColumnLabel(columnIndex);

        String typeName = jdbcMetadata.getColumnClassName(columnIndex);
        // int width = jdbcMetadata.getColumnDisplaySize(i);
        int sqlType = jdbcMetadata.getColumnType(columnIndex);

        // this.setIndex(columnIndex - 1);
        this.setName(name);
        if (!Nullables.equals(name, label))
            this.setLabel(label);

        try {
            this.setTypeByName(typeName);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        this.setSqlType(sqlType);

        autoIncrement = jdbcMetadata.isAutoIncrement(columnIndex);
        caseSensitive = jdbcMetadata.isCaseSensitive(columnIndex);
        searchable = jdbcMetadata.isSearchable(columnIndex);
        currency = jdbcMetadata.isCurrency(columnIndex);
        nullable = jdbcMetadata.isNullable(columnIndex);
        signed = jdbcMetadata.isSigned(columnIndex);
        readOnly = jdbcMetadata.isReadOnly(columnIndex);
        writable = jdbcMetadata.isWritable(columnIndex);
        definitelyWritable = jdbcMetadata.isDefinitelyWritable(columnIndex);

        precision = jdbcMetadata.getPrecision(columnIndex);
        scale = jdbcMetadata.getScale(columnIndex);
        columnDisplaySize = jdbcMetadata.getColumnDisplaySize(columnIndex);
    }

    public void readObject(ResultSet rs)
            throws SQLException {
        name = rs.getString("COLUMN_NAME");
        label = name;

        sqlType = rs.getInt("DATA_TYPE");

        // String typeName = rs.getString("TYPE_NAME");
        precision = rs.getInt("COLUMN_SIZE");
        // int bufferLength = rs.getInt("BUFFER_LENGTH");
        scale = rs.getInt("DECIMAL_DIGITS");
        // int numPrecRadix = rs.getInt("NUM_PREC_RADIX");
        nullable = rs.getInt("NULLABLE");
        // String isNullable = rs.getString("IS_NULLABLE"); // YES NO
        description = rs.getString("REMARKS");
        defaultValue = rs.getString("COLUMN_DEF");
        // int sqlDataType = rs.getInt("SQL_DATA_TYPE");
        // Integer sqlDatetimeSub = (Integer) rs.getObject("SQL_DATETIME_SUB");
        // int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");
        // int ordinalPosition = rs.getInt("ORDINAL_POSITION");
        // String scopeCatalog = rs.getString("SCOPE_CATLOG");
        // String scopeSchema = rs.getString("SCOPE_SCHEMA");
        // String scopeTable = rs.getString("SCOPE_TABLE");
        // Object sourceDataType = rs.getObject("SOURCE_DATA_TYPE");
        String isAutoIncrement = rs.getString("IS_AUTOINCREMENT");
        this.autoIncrement = "YES".equals(isAutoIncrement);

        type = SQLTypes.toJavaType(this);
    }

    /** ⇱ Implementation Of {@link IJsonForm}. */
    /* _____________________________ */static section.iface __JSON__;

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
//        index = o.getInt("index", -1);
        name = o.getString(K_NAME);
        javaName = o.getString(K_JAVA_NAME);

        label = o.getString(K_LABEL);
        description = o.getString(K_DESCRIPTION);

        String typeName = o.getString(K_TYPE);
        setTypeByName(typeName);

        sqlType = SQLTypes.getTypeInt(o.getString(K_SQL_TYPE), Types.INTEGER);

        autoIncrement = o.getBoolean(K_AUTO_INCREMENT, false);
        caseSensitive = o.getBoolean(K_CASE_SENSITIVE, false);
        searchable = o.getBoolean(K_SEARCHABLE, false);
        currency = o.getBoolean(K_CURRENCY, false);
        nullable = o.getInt(K_NULLABLE, ResultSetMetaData.columnNullableUnknown);
        signed = o.getBoolean(K_SIGNED, false);
        readOnly = o.getBoolean(K_READ_ONLY, false);
        writable = o.getBoolean(K_WRITABLE, false);
        definitelyWritable = o.getBoolean(K_DEFINITELY_WRITABLE, false);

        precision = o.getInt(K_PRECISION, 0);
        scale = o.getInt(K_SCALE, 0);
        columnDisplaySize = o.getInt(K_COLUMN_DISPLAY_SIZE, 0);

        defaultValue = o.getString(K_DEFAULT_VALUE);
    }

    /** ⇱ Implementation Of {@link IXmlForm}. */
    /* _____________________________ */static section.iface __XML__;

    @Override
    public void readObject(IElement o)
            throws ParseException, LoaderException {
//        index = o.a("index").getInt(-1);
        name = o.a(K_NAME).getString();
        javaName = o.a(K_JAVA_NAME).getString();
        label = o.a(K_LABEL).getString();
        description = o.a(K_DESCRIPTION).getString();

        String typeName = o.a(K_TYPE).getString();
        setTypeByName(typeName);
        sqlType = SQLTypes.getTypeInt(o.a(K_SQL_TYPE).getString(), Types.INTEGER);

        autoIncrement = o.a(K_AUTO_INCREMENT).getBoolean(false);
        caseSensitive = o.a(K_CASE_SENSITIVE).getBoolean(false);
        searchable = o.a(K_SEARCHABLE).getBoolean(false);
        currency = o.a(K_CURRENCY).getBoolean(false);
        nullable = o.a(K_NULLABLE).getInt(ResultSetMetaData.columnNullableUnknown);
        signed = o.a(K_SIGNED).getBoolean(false);
        readOnly = o.a(K_READ_ONLY).getBoolean(false);
        writable = o.a(K_WRITABLE).getBoolean(false);
        definitelyWritable = o.a(K_DEFINITELY_WRITABLE).getBoolean(false);

        precision = o.a(K_PRECISION).getInt(0);
        scale = o.a(K_SCALE).getInt(0);
        columnDisplaySize = o.a(K_COLUMN_DISPLAY_SIZE).getInt(0);

        defaultValue = o.a(K_DEFAULT_VALUE).getString();
    }

    @Override
    public String toString() {
        return name;
    }

}
