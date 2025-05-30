package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.xml.stream.XMLStreamException;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.db.sql.DataType;
import net.bodz.bas.db.sql.IStdDataTypes;
import net.bodz.bas.db.sql.dialect.ISqlDialect;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.t.tuple.QualifiedName;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class DefaultColumnMetadata
        implements IColumnMetadata,
                   IMutableJavaQName {

    IRowSetMetadata parent;
    TableOid tableOid;

    int columnIndex;
    int ordinalPosition;

    ColumnOid oid;
    String name;
    QualifiedName javaQName;
    boolean javaNameComplete;

    String label;
    String description; // comment..

    Class<?> javaClass;

    DataType dataType;

    int sqlType;
    String sqlTypeName;
    String sqlClassName;
    Class<?> sqlClass;

    boolean jsonType;
    boolean xmlType;

    boolean primaryKey;
    boolean unique;

    boolean autoIncrement;
    boolean caseSensitive;
    boolean searchable;
    boolean currency;
    NullableType nullableType;
    boolean signed;
    boolean readOnly;
    boolean writable;
    boolean definitelyWritable;

    int columnDisplaySize;
    int precision;
    int scale;
    String precisionExpr;
    String scaleExpr;

    String defaultValue;

    int verboseLevel; // not used
    int joinLevel; // not used

    IColumnMetadata parentColumn;

    private boolean excluded;
    private IProperty property;

    protected DefaultColumnMetadata(IRowSetMetadata parent) {
        if (parent == null)
            throw new NullPointerException("parent");
        this.parent = parent;
    }

    protected DefaultColumnMetadata(ITableMetadata parent) {
        if (parent == null)
            throw new NullPointerException("parent");
        this.parent = parent;
        this.tableOid = parent.getId();
    }

    @Override
    public ITableMetadata getTable() {
        if (tableOid != null || parent instanceof ITableMetadata)
            return (ITableMetadata) parent;
        return null;
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

    public void setParent(ITableMetadata parent) {
        if (parent == null)
            throw new NullPointerException("parent");
        this.parent = parent;
        this.tableOid = parent.getId();
    }

    @Override
    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    @Override
    public int getOrdinal() {
        return ordinalPosition;
    }

    public void setOrdinal(int ordinal) {
        this.ordinalPosition = ordinal;
    }

    @Override
    public ColumnOid getId() {
        if (oid == null) {
            if (tableOid != null)
                oid = new ColumnOid(tableOid, name);
        }
        return oid;
    }

    public void setId(ColumnOid id) {
        this.oid = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public QualifiedName getJavaQName() {
        return javaQName;
    }

    @Override
    public void setJavaQName(QualifiedName qName) {
        this.javaQName = qName;
    }

    @Override
    public boolean isJavaNameComplete() {
        return javaNameComplete;
    }

    public void setJavaNameComplete(boolean javaNameComplete) {
        this.javaNameComplete = javaNameComplete;
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
    public Class<?> getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(Class<?> javaClass) {
        this.javaClass = javaClass;
        jsonType = IJsonForm.class.isAssignableFrom(javaClass);
        xmlType = IXmlForm.class.isAssignableFrom(javaClass);
    }

    public String getJavaClassName() {
        if (javaClass == null)
            return null;
        else
            return javaClass.getName();
    }

    public void setJavaClassName(String className) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        setJavaClass(clazz);
    }

    @Override
    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(@NotNull DataType dataType) {
        this.dataType = dataType;
        this.sqlType = dataType.getSqlType();
        this.sqlTypeName = dataType.getSqlTypeName();
        this.sqlClassName = dataType.getSqlClassName();
    }

    public void updateDataType() {
        ISqlDialect dialect = getDialect();
        if (dialect == null)
            throw new IllegalStateException("Can't determine the sql dialect.");

        DataType type = dialect.getDefaultType(sqlType, sqlTypeName);
        sqlClass = type == null ? null : type.getSqlClass(this);

        if (javaClass == null)
            javaClass = sqlClass;
    }

    @Override
    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    @Override
    public String getSqlTypeName() {
        return sqlTypeName;
    }

    public void setSqlTypeName(String sqlTypeName) {
        this.sqlTypeName = sqlTypeName;
    }

    @Override
    public String getSqlClassName() {
        return sqlClassName;
    }

    public void setSqlClassName(String sqlClassName) {
        this.sqlClassName = sqlClassName;
        try {
            sqlClass = Class.forName(sqlClassName);
        } catch (ReflectiveOperationException e) {
            sqlClass = null;
        }
    }

    @Override
    public Class<?> getSqlClass() {
        return sqlClass;
    }

    public void setSqlClass(Class<?> sqlClass) {
        this.sqlClass = sqlClass;
        this.sqlClassName = sqlClass == null ? null : sqlClass.getName();
    }

    @Override
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public boolean isForeignKey() {
        if (parentColumn != null)
            return true;

        CrossReference crossRef = getCrossReference();
        if (crossRef != null)
            return crossRef.foreignKey.contains(name);

        return false;
    }

    @Override
    public IColumnMetadata getParentColumn() {
        if (parentColumn == null) {
            CrossReference crossRef = getCrossReference();
            if (crossRef != null)
                parentColumn = crossRef.findParentColumn(name);
        }
        return parentColumn;
    }

    public CrossReference getCrossReference() {
        IRowSetMetadata parent = getParent();
        if (parent instanceof ITableMetadata) {
            ITableMetadata table = (ITableMetadata) parent;
            CrossReference crossRef = table.getForeignKeyFromColumn(name);
            return crossRef;
        }
        return null;
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

    @NotNull
    @Override
    public NullableType getNullableType() {
        return nullableType;
    }

    public void setNullableType(NullableType nullableType) {
        this.nullableType = nullableType;
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
    public String getPrecisionExpr() {
        return precisionExpr;
    }

    public void setPrecisionExpr(String precisionExpr) {
        this.precisionExpr = precisionExpr;
    }

    @Override
    public String getScaleExpr() {
        return scaleExpr;
    }

    public void setScaleExpr(String scaleExpr) {
        this.scaleExpr = scaleExpr;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public int getVerboseLevel() {
        return verboseLevel;
    }

    public void setVerboseLevel(int verboseLevel) {
        this.verboseLevel = verboseLevel;
    }

    @Override
    public int getJoinLevel() {
        return joinLevel;
    }

    public void setJoinLevel(int joinLevel) {
        this.joinLevel = joinLevel;
    }

    @Override
    public String toString() {
        ColumnOid id = getId();
        if (id != null)
            return id.toString();
        return name;
    }

    /**
     * ⇱ Implementation Of {@link interface}.
     */
    /* _____________________________ */static section.iface __1__;

    @Override
    public Object parseColumnValue(String s)
            throws ParseException {
        IParser<?> parser = Typers.getTyper(javaClass, IParser.class);
        return parser.parse(s);
    }

    @Override
    public Object readColumnJsonValue(Object jsonValue)
            throws ParseException {
        if (jsonValue == null)
            return null;

        if (jsonType) {
            IJsonForm obj;
            try {
                obj = (IJsonForm) javaClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new ParseException("Failed to instantiate " + javaClass, e);
            }
            obj.jsonIn(JsonVariant.of(jsonValue), Convention.JSON_STYLE);
            return obj;
        }

        IParser<?> parser = Typers.getTyper(javaClass, IParser.class);
        if (parser != null) {
            String text = jsonValue.toString();
            Object value = parser.parse(text);
            return value;
        }

        throw new UnsupportedOperationException(//
                "Don't know how to convert json " + jsonValue + " to " + javaClass);
    }

    @Override
    public void writeColumnInJson(IJsonOut out, Object value)
            throws IOException, FormatException {
        if (value == null) {
            out.value(null);
            return;
        }

        Class<?> boxed = Primitives.box(javaClass);
        if (!boxed.isInstance(value))
            throw new IllegalArgumentException(String.format(//
                    "Not an instance of %s: \"%s\" (%s)", javaClass, value, value.getClass()));

        if (jsonType) {
            IJsonForm obj = (IJsonForm) value;
            obj.jsonOutValue(out, Convention.JSON_STYLE);
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
                obj = (IXmlForm) javaClass.getConstructor().newInstance();
            } catch (Exception e) {
                throw new ParseException("Failed to instantiate " + javaClass, e);
            }
            obj.readObjectBoxed(enclosing);
            return obj;
        }

        String text = enclosing.getTextContent();
        IParser<?> parser = Typers.getTyper(javaClass, IParser.class);
        if (parser != null) {
            Object value = parser.parse(text);
            return value;
        }

        throw new UnsupportedOperationException(//
                "Don't know how to convert xml text content [" + text + "] to " + javaClass);
    }

    @Override
    public void writeColumnInXml(IXmlOutput out, Object value)
            throws XMLStreamException, FormatException {
        if (value != null) {
            if (!javaClass.isInstance(value))
                throw new IllegalArgumentException("Not an instance of " + javaClass);

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

    /**
     * ⇱ Implementation Of {@link interface}.
     */
    /* _____________________________ */static section.iface __RS__;

    public void readObject(ResultSetMetaData jdbcMetadata, int columnIndex)
            throws SQLException {
        String name = jdbcMetadata.getColumnName(columnIndex);
        String label = jdbcMetadata.getColumnLabel(columnIndex);

        // this.setIndex(columnIndex - 1);
        this.setName(name);
        if (!Nullables.equals(name, label))
            this.setLabel(label);

        String sqlClassName = jdbcMetadata.getColumnClassName(columnIndex);
        this.setJavaClassName(sqlClassName);

        columnDisplaySize = jdbcMetadata.getColumnDisplaySize(columnIndex);
        sqlType = jdbcMetadata.getColumnType(columnIndex);
        sqlTypeName = jdbcMetadata.getColumnTypeName(columnIndex);

        autoIncrement = jdbcMetadata.isAutoIncrement(columnIndex);
        caseSensitive = jdbcMetadata.isCaseSensitive(columnIndex);
        searchable = jdbcMetadata.isSearchable(columnIndex);
        currency = jdbcMetadata.isCurrency(columnIndex);

        nullableType = NullableType.ofIntValue(jdbcMetadata.isNullable(columnIndex));

        signed = jdbcMetadata.isSigned(columnIndex);
        readOnly = jdbcMetadata.isReadOnly(columnIndex);
        writable = jdbcMetadata.isWritable(columnIndex);
        definitelyWritable = jdbcMetadata.isDefinitelyWritable(columnIndex);

        precision = jdbcMetadata.getPrecision(columnIndex);
        scale = jdbcMetadata.getScale(columnIndex);

        updateDataType();
    }

    public void readObject(ResultSet rs)
            throws SQLException {
        name = rs.getString("COLUMN_NAME");
        // label = name;

        sqlType = rs.getInt("DATA_TYPE");
        sqlTypeName = rs.getString("TYPE_NAME");

        precision = rs.getInt("COLUMN_SIZE");
        // int bufferLength = rs.getInt("BUFFER_LENGTH");
        scale = rs.getInt("DECIMAL_DIGITS");
        // int numPrecRadix = rs.getInt("NUM_PREC_RADIX");

        nullableType = NullableType.ofIntValue(rs.getInt("NULLABLE"));

        // String isNullable = rs.getString("IS_NULLABLE"); // YES NO
        description = rs.getString("REMARKS");
        if (description != null) {
            description = description.trim();
            if (description.isEmpty())
                description = null;
            else {
                int colon = description.indexOf(':');
                if (colon != -1) {
                    label = description.substring(0, colon).trim();
                    description = description.substring(colon + 1).trim();
                } else {
                    label = description;
                    // description = null;
                }
            }
        }

        defaultValue = rs.getString("COLUMN_DEF");
        // int sqlDataType = rs.getInt("SQL_DATA_TYPE");
        // Integer sqlDatetimeSub = (Integer) rs.getObject("SQL_DATETIME_SUB");
        // int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");
        ordinalPosition = rs.getInt("ORDINAL_POSITION");
        // String scopeCatalog = rs.getString("SCOPE_CATLOG");
        // String scopeSchema = rs.getString("SCOPE_SCHEMA");
        // String scopeTable = rs.getString("SCOPE_TABLE");
        // Object sourceDataType = rs.getObject("SOURCE_DATA_TYPE");
        String isAutoIncrement = rs.getString("IS_AUTOINCREMENT");
        this.autoIncrement = "YES".equals(isAutoIncrement);

        updateDataType();
    }

    /**
     * ⇱ Implementation Of {@link IJsonForm}.
     */
    /* _____________________________ */static section.iface __JSON__;

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
//        index = o.getInt("index", -1);
        name = o.getString(K_NAME);
        setJavaQName(o.getString(K_Q_NAME));

        label = o.getString(K_LABEL);
        description = o.getString(K_DESCRIPTION);

        String typeName = o.getString(K_TYPE);
        setJavaClassName(typeName);

        sqlType = o.getInt(K_SQL_TYPE);
        sqlTypeName = o.getString(K_SQL_TYPE_NAME);
        setSqlClassName(o.getString(K_SQL_CLASS_NAME));

        autoIncrement = o.getBoolean(K_AUTO_INCREMENT, false);
        caseSensitive = o.getBoolean(K_CASE_SENSITIVE, false);
        searchable = o.getBoolean(K_SEARCHABLE, false);
        currency = o.getBoolean(K_CURRENCY, false);
        nullableType = NullableType.ofIntValue(o.getInt(K_NULLABLE));
        signed = o.getBoolean(K_SIGNED);
        readOnly = o.getBoolean(K_READ_ONLY, false);
        writable = o.getBoolean(K_WRITABLE, false);
        definitelyWritable = o.getBoolean(K_DEFINITELY_WRITABLE, false);

        precision = o.getInt(K_PRECISION, 0);
        scale = o.getInt(K_SCALE, 0);
        columnDisplaySize = o.getInt(K_COLUMN_DISPLAY_SIZE, 0);

        defaultValue = o.getString(K_DEFAULT_VALUE);

        excluded = o.getBoolean(K_EXCLUDED, false);
        verboseLevel = o.getInt(K_VERBOSE_LEVEL, 0);
        joinLevel = o.getInt(K_JOIN_LEVEL, 0);
    }

    /**
     * ⇱ Implementation Of {@link IXmlForm}.
     */
    /* _____________________________ */static section.iface __XML__;

    @Override
    public void readObject(IElement o)
            throws ParseException, LoaderException {
//        index = o.a("index").getInt(-1);
        name = o.a(K_NAME).getString();
        setJavaQName(o.a(K_Q_NAME).getString());
        label = o.a(K_LABEL).getString();
        description = o.a(K_DESCRIPTION).getString();

        String typeName = o.a(K_TYPE).getString();
        setJavaClassName(typeName);
        sqlType = o.a(K_SQL_TYPE).getInt();
        sqlTypeName = o.a(K_SQL_TYPE_NAME).getString();
        setSqlClassName(o.a(K_SQL_CLASS_NAME).getString());

        autoIncrement = o.a(K_AUTO_INCREMENT).getBoolean(false);
        caseSensitive = o.a(K_CASE_SENSITIVE).getBoolean(false);
        searchable = o.a(K_SEARCHABLE).getBoolean(false);
        currency = o.a(K_CURRENCY).getBoolean(false);
        nullableType = NullableType.ofIntValue(o.a(K_NULLABLE).getInt(ResultSetMetaData.columnNullableUnknown));
        signed = o.a(K_SIGNED).getBoolean(null);
        readOnly = o.a(K_READ_ONLY).getBoolean(false);
        writable = o.a(K_WRITABLE).getBoolean(false);
        definitelyWritable = o.a(K_DEFINITELY_WRITABLE).getBoolean(false);

        precision = o.a(K_PRECISION).getInt(0);
        scale = o.a(K_SCALE).getInt(0);
        columnDisplaySize = o.a(K_COLUMN_DISPLAY_SIZE).getInt(0);

        defaultValue = o.a(K_DEFAULT_VALUE).getString();

        excluded = o.a(K_EXCLUDED).getBoolean(false);
        verboseLevel = o.a(K_VERBOSE_LEVEL).getInt(0);
        joinLevel = o.a(K_JOIN_LEVEL).getInt(0);
    }

    /**
     * ⇱ Java runtime binding
     */
    /* _____________________________ */static section.iface __JAVA_BIND__;

    @Override
    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    @Override
    public IProperty getProperty() {
        return property;
    }

    public void setProperty(IProperty property) {
        this.property = property;
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        IProperty property = getProperty();
        if (property == null)
            return null;
        else
            return property.getAnnotation(annotationType);
    }

    public void parseProperty(IProperty property) {
//        int columnIndex;
        Ordinal aOrdinal = property.getAnnotation(Ordinal.class);
        if (aOrdinal != null)
            ordinalPosition = aOrdinal.value();

        javaQName = QualifiedName.parse(property.getName());
        javaNameComplete = true;

        IElementDoc xjdoc = property.getXjdoc();
        if (xjdoc != null) {
            label = xjdoc.getString("label");
            description = xjdoc.getText().toString();
        }

        Class<?> javaClass = property.getPropertyClass();
        setJavaClass(javaClass);

        ISqlDialect dialect = getDialect();
        if (dialect != null) {
            DataType dataType = dialect.getDefaultType(javaClass);
            if (dataType == null)
                dataType = IStdDataTypes.OTHER;
            setDataType(dataType);
        }

        Column aColumn = property.getAnnotation(Column.class);
        primaryKey = property.isAnnotationPresent(Id.class);
        unique = aColumn.unique();

//        boolean autoIncrement
//        boolean caseSensitive;
//        boolean searchable;
//        boolean currency;
        nullableType = aColumn.nullable() ? NullableType.NULLABLE : NullableType.NO_NULLS;
        signed = true;
        readOnly = property.isReadable() && !property.isWritable();
        writable = property.isWritable();
        definitelyWritable = writable;

        if (javaClass == String.class)
            precision = aColumn.length();
        else {
            precision = aColumn.precision();
            scale = aColumn.scale();
        }
        columnDisplaySize = precision;

//        ManyToOne aManyToOne = property.getAnnotation(ManyToOne.class);
//        OneToOne aOneToOne = property.getAnnotation(OneToOne.class);
        JoinColumn aJoinColumn = property.getAnnotation(JoinColumn.class);
        JoinColumns aJoinColumns = property.getAnnotation(JoinColumns.class);
        if (aJoinColumn != null || aJoinColumns != null) {
            ICatalogMetadata catalog = getCatalog();
            if (catalog != null) {
                ITableMetadata parentTable = getCatalog().findTable(javaClass);
                if (parentTable != null) {
                    if (aJoinColumn == null)
                        aJoinColumn = aJoinColumns.value()[0];
                    parentColumn = parentTable.getColumn(aJoinColumn.name());
                }
            }
        }

        DetailLevel aDetailLevel = property.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null) {
            int detailLevel = aDetailLevel.value();
            excluded = detailLevel >= DetailLevel.HIDDEN;
        } else {
            excluded = false;
        }
        this.property = property;
    }

}
