package net.bodz.bas.t.catalog;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public enum JdbcType {

    Bit(Types.BIT, Boolean.class),

    TinyInt(Types.TINYINT, Short.class),
    SmallInt(Types.SMALLINT, Short.class),
    Integer(Types.INTEGER, Integer.class),
    BigInt(Types.BIGINT, Long.class),

    Float(Types.FLOAT, Double.class),
    Real(Types.REAL, Float.class),
    Double(Types.DOUBLE, Double.class),

    Numeric(Types.NUMERIC, BigDecimal.class),
    Decimal(Types.DECIMAL, BigDecimal.class),

    Char(Types.CHAR, String.class),
    VarChar(Types.VARCHAR, String.class),
    LongVarChar(Types.LONGVARCHAR, String.class),

    Date(Types.DATE, Date.class),
    Time(Types.TIME, Time.class),
    Timestamp(Types.TIMESTAMP, Timestamp.class),

    Binary(Types.BINARY, byte[].class),
    VarBinary(Types.VARBINARY, byte[].class),
    LongVarBinary(Types.LONGVARBINARY, byte[].class),

    Null(Types.NULL, Object.class),
    Other(Types.OTHER, Object.class),
    JavaObject(Types.JAVA_OBJECT, Object.class),
    Distinct(Types.DISTINCT, Object.class),

    Struct(Types.STRUCT, Struct.class),
    Array(Types.ARRAY, java.sql.Array.class),

    Blob(Types.BLOB, Blob.class),
    Clob(Types.CLOB, Clob.class),

    Ref(Types.REF, Ref.class),
    DataLink(Types.DATALINK, Object.class),

    Boolean(Types.BOOLEAN, Boolean.class),
    RowId(Types.ROWID, RowId.class),

    NChar(Types.NCHAR, String.class),
    NVarChar(Types.NVARCHAR, String.class),
    LongNVarChar(Types.LONGNVARCHAR, String.class),

    NClob(Types.NCLOB, Clob.class),
    SQLXML(Types.SQLXML, SQLXML.class),

    RefCursor(Types.REF_CURSOR, Object.class),
    TimeWithTimeZone(Types.TIME_WITH_TIMEZONE, Time.class),
    TimestampWithTimeZone(Types.TIMESTAMP_WITH_TIMEZONE, Timestamp.class),

    ;

    static final Logger logger = LoggerFactory.getLogger(JdbcType.class);

    public final int sqlType;
    final Class<?> defaultType;

    JdbcType(int sqlType, Class<?> defaultType) {
        this.sqlType = sqlType;
        this.defaultType = defaultType;
    }

    static boolean aggressive = false;

    // 4 294 967 296
    static final int MAXLEN_FOR_INT = aggressive ? 10 : 9;

    // 18 446 744 073 709 551 616
    static final int MAXLEN_FOR_LONG = aggressive ? 20 : 19;

    public Class<?> getPreferredType(String sqlTypeName, boolean nullable, boolean signed, int precision, int scale) {
        switch (this) {
        case Numeric:
        case Decimal:
            if (scale == 0) {
                // if (precision < 5) // 65 536
                // return nullable ? Short.class : short.class;
                if (precision <= MAXLEN_FOR_INT)
                    return nullable ? Integer.class : int.class;
                if (precision <= MAXLEN_FOR_LONG)
                    return nullable ? Long.class : long.class;
                return BigInteger.class;
            }
            return BigDecimal.class;

        case Bit:
            if (signed)
                return nullable ? Byte.class : byte.class;
            else
                return nullable ? Boolean.class : boolean.class;

        case TinyInt:
            if (signed)
                return nullable ? Byte.class : byte.class;
            else
                return nullable ? Short.class : short.class;

        case SmallInt:
            if (signed)
                return nullable ? Short.class : short.class;
            else
                return nullable ? Integer.class : int.class;

        case Integer:
            if (signed)
                return nullable ? Integer.class : int.class;
            else
                return nullable ? Long.class : long.class;

        case BigInt:
            if (signed)
                return nullable ? Long.class : long.class;
            else
                return BigInteger.class;

        case Real:
            return nullable ? Float.class : float.class;

        case Float:
        case Double:
            return nullable ? Double.class : double.class;

        case Array:
            return getPreferredArrayType(sqlTypeName, nullable, signed, precision, scale);

        case Distinct:
            // mapping of underlying type
            logger.error("unsupport distinct type.");
            return Object.class;

        default:
            return defaultType;
        }
    }

    public static Class<?> getPreferredType(IColumnMetadata column) {
        JdbcType jdbcType = column.getJdbcType();
        String sqlTypeName = column.getSqlTypeName();
        boolean nullable = column.isNullable();
        boolean signed = column.isSigned();
        int precision = column.getPrecision();
        int scale = column.getScale();
        return jdbcType.getPreferredType(sqlTypeName, nullable, signed, precision, scale);
    }

    static Map<String, String> postgresqlMap = new HashMap<>();
    static {
        postgresqlMap.put("bpchar", "char");
        postgresqlMap.put("int2", "smallint");
        postgresqlMap.put("int4", "integer");
        postgresqlMap.put("int8", "bigint");
        postgresqlMap.put("float4", "real");
        postgresqlMap.put("float8", "float");
        postgresqlMap.put("bpchar", "char");
    }

    public Class<?> getPreferredArrayType(String arraySqlTypeName, boolean nullable, boolean signed, int precision,
            int scale) {
        String sqlTypeName = arraySqlTypeName;
        if (sqlTypeName.startsWith("_"))
            sqlTypeName = sqlTypeName.substring(1);

        String std = postgresqlMap.get(sqlTypeName);
        if (std != null)
            sqlTypeName = std;

        Class<?> componentType = null;

        JdbcType sqlType = forSQLTypeName(sqlTypeName, VarChar);
        if (sqlType != null)
            componentType = sqlType.getPreferredType(sqlTypeName, nullable, signed, precision, scale);

        if (componentType == null)
            return defaultType;

        Class<?> arrayType = java.lang.reflect.Array.newInstance(componentType, 0).getClass();
        return arrayType;
    }

    private static Map<Integer, JdbcType> sqlTypeMap = new HashMap<>();
    private static Map<String, JdbcType> sqlTypeNameMap = new HashMap<>();
    static {
        for (JdbcType type : values()) {
            sqlTypeMap.put(type.sqlType, type);
            sqlTypeNameMap.put(type.name().toLowerCase(), type);
        }
    }

    public static JdbcType forSQLType(int sqlType, JdbcType fallback) {
        JdbcType type = sqlTypeMap.get(sqlType);
        return type != null ? type : fallback;
    }

    public static JdbcType forSQLTypeName(String sqlTypeName, JdbcType fallback) {
        if (sqlTypeName == null)
            return fallback;
        String lowerCased = sqlTypeName.toLowerCase();
        JdbcType type = sqlTypeNameMap.get(lowerCased);
        return type != null ? type : fallback;
    }

}
