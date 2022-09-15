package net.bodz.bas.t.catalog;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public enum JdbcType {

    BIT(Types.BIT, Boolean.class),

    TINYINT(Types.TINYINT, Short.class),
    SMALLINT(Types.SMALLINT, Short.class),
    INTEGER(Types.INTEGER, Integer.class),
    BIGINT(Types.BIGINT, Long.class),

    FLOAT(Types.FLOAT, Double.class),
    REAL(Types.REAL, Float.class),
    DOUBLE(Types.DOUBLE, Double.class),

    NUMERIC(Types.NUMERIC, BigDecimal.class),
    DECIMAL(Types.DECIMAL, BigDecimal.class),

    CHAR(Types.CHAR, String.class),
    VARCHAR(Types.VARCHAR, String.class),
    LONGVARCHAR(Types.LONGVARCHAR, String.class),

    DATE(Types.DATE, Date.class),
    TIME(Types.TIME, Time.class),
    TIMESTAMP(Types.TIMESTAMP, Timestamp.class),

    BINARY(Types.BINARY, byte[].class),
    VARBINARY(Types.VARBINARY, byte[].class),
    LONGVARBINARY(Types.LONGVARBINARY, byte[].class),

    NULL(Types.NULL, Object.class),
    OTHER(Types.OTHER, Object.class),
    JAVA_OBJECT(Types.JAVA_OBJECT, Object.class),
    DISTINCT(Types.DISTINCT, Object.class),

    STRUCT(Types.STRUCT, Struct.class),
    ARRAY(Types.ARRAY, java.sql.Array.class),

    BLOB(Types.BLOB, Blob.class),
    CLOB(Types.CLOB, Clob.class),

    REF(Types.REF, Ref.class),
    DATALINK(Types.DATALINK, Object.class),

    BOOLEAN(Types.BOOLEAN, Boolean.class),
    ROWID(Types.ROWID, RowId.class),

    NCHAR(Types.NCHAR, String.class),
    NVARCHAR(Types.NVARCHAR, String.class),
    LONGNVARCHAR(Types.LONGNVARCHAR, String.class),

    NCLOB(Types.NCLOB, Clob.class),
    SQLXML(Types.SQLXML, SQLXML.class),

    REF_CURSOR(Types.REF_CURSOR, Object.class),
    TIME_WITH_TIMEZONE(Types.TIME_WITH_TIMEZONE, Time.class),
    TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE, Timestamp.class),

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

    public Class<?> getPreferredType(String sqlTypeName, Boolean nullable, Boolean signed, int precision, int scale) {
        switch (this) {
        case NUMERIC:
        case DECIMAL:
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

        case BIT:
            if (signed == Boolean.FALSE)
                return nullable ? Byte.class : byte.class;
            else
                return nullable ? Boolean.class : boolean.class;

        case TINYINT:
            if (signed != Boolean.FALSE)
                return nullable ? Byte.class : byte.class;
            else
                return nullable ? Short.class : short.class;

        case SMALLINT:
            if (signed != Boolean.FALSE)
                return nullable ? Short.class : short.class;
            else
                return nullable ? Integer.class : int.class;

        case INTEGER:
            if (signed != Boolean.FALSE)
                return nullable ? Integer.class : int.class;
            else
                return nullable ? Long.class : long.class;

        case BIGINT:
            if (signed != Boolean.FALSE)
                return nullable ? Long.class : long.class;
            else
                return BigInteger.class;

        case REAL:
            return nullable ? Float.class : float.class;

        case FLOAT:
        case DOUBLE:
            return nullable ? Double.class : double.class;

        case ARRAY:
            return getPreferredArrayType(sqlTypeName, nullable, signed, precision, scale);

        case DISTINCT:
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
        Boolean nullable = column.getNullable();
        Boolean signed = column.getSigned();
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

    public Class<?> getPreferredArrayType(String arraySqlTypeName, Boolean nullable, Boolean signed, int precision,
            int scale) {
        String sqlTypeName = arraySqlTypeName;
        if (sqlTypeName.startsWith("_"))
            sqlTypeName = sqlTypeName.substring(1);

        String std = postgresqlMap.get(sqlTypeName);
        if (std != null)
            sqlTypeName = std;

        Class<?> componentType = null;

        JdbcType sqlType = forSQLTypeName(sqlTypeName, VARCHAR);
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
