package net.bodz.bas.t.catalog;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLXML;
import java.sql.Struct;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.bodz.bas.c.object.Unknown;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;

public enum SqlTypeEnum {

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

    DATE(Types.DATE, LocalDate.class),
    TIME(Types.TIME, LocalTime.class),
    TIMESTAMP(Types.TIMESTAMP, LocalDateTime.class),

    BINARY(Types.BINARY, byte[].class),
    VARBINARY(Types.VARBINARY, byte[].class),
    LONGVARBINARY(Types.LONGVARBINARY, byte[].class),

    NULL(Types.NULL, Unknown.class),
    OTHER(Types.OTHER, Unknown.class),

    JAVA_OBJECT(Types.JAVA_OBJECT, Unknown.class),
    DISTINCT(Types.DISTINCT, Unknown.class),

    STRUCT(Types.STRUCT, Struct.class),
    ARRAY(Types.ARRAY, java.sql.Array.class),

    BLOB(Types.BLOB, Blob.class),
    CLOB(Types.CLOB, Clob.class),

    REF(Types.REF, Ref.class),
    DATALINK(Types.DATALINK, Unknown.class),

    BOOLEAN(Types.BOOLEAN, Boolean.class),
    ROWID(Types.ROWID, RowId.class),

    NCHAR(Types.NCHAR, String.class),
    NVARCHAR(Types.NVARCHAR, String.class),
    LONGNVARCHAR(Types.LONGNVARCHAR, String.class),

    NCLOB(Types.NCLOB, Clob.class),
    SQLXML(Types.SQLXML, SQLXML.class),

    REF_CURSOR(Types.REF_CURSOR, Unknown.class),
    TIME_WITH_TIMEZONE(Types.TIME_WITH_TIMEZONE, OffsetTime.class),
    TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE, OffsetDateTime.class),
    ;

    static final Logger logger = LoggerFactory.getLogger(SqlTypeEnum.class);

    public final int sqlType;
    final Class<?> defaultType;

    SqlTypeEnum(int sqlType, Class<?> defaultType) {
        this.sqlType = sqlType;
        this.defaultType = defaultType;
    }

    static final boolean aggressive = false;

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

            case CHAR:
            case NCHAR:
                if (precision == 1)
                    return nullable ? Character.class : char.class;
                break;

            case VARCHAR:
            case LONGVARCHAR:
            case NVARCHAR:
            case LONGNVARCHAR:
                break;

            case VARBINARY:
            case LONGVARBINARY:
                break;

            case TIMESTAMP:
                switch (sqlTypeName.toLowerCase()) {
                    case "timestamptz":
                        return OffsetDateTime.class;
                }
                return LocalDateTime.class;
            case TIMESTAMP_WITH_TIMEZONE:
                return OffsetDateTime.class;

            case ARRAY:
                return getPreferredArrayType(sqlTypeName, nullable, signed, precision, scale);

            case DISTINCT:
                return getPreferredDistinctType(sqlTypeName, nullable, signed, precision, scale);

            case OTHER:
                return getPreferredOtherType(sqlTypeName, nullable, signed, precision, scale);

            default:
        }
        return defaultType;
    }

    public static Class<?> getPreferredType(IColumnMetadata column) {
//        DataType dataType = column.getDataType();
//        if (dataType != null)
//            return dataType.getJavaType();

        String sqlTypeName = column.getSqlTypeName();
        if (sqlTypeName != null) {
            SqlTypeEnum jdbcType = SqlTypeEnum.forSQLTypeName(sqlTypeName);
            if (jdbcType != null)
                return jdbcType.defaultType;
        }

        int sqlType = column.getSqlType();
        SqlTypeEnum jdbcType = SqlTypeEnum.forSQLType(sqlType);
        if (jdbcType != null)
            return jdbcType.defaultType;

//        Boolean nullable = column.getNullable();
//        Boolean signed = column.getSigned();
//        int precision = column.getPrecision();
//        int scale = column.getScale();
//        Class<?> type = JdbcType.getPreferredType(sqlTypeName, nullable, signed, precision, scale);
        return Unknown.class;
    }

    static final Map<String, String> postgresqlMap = new HashMap<>();

    static {
        postgresqlMap.put("bpchar", "char");
        postgresqlMap.put("int2", "smallint");
        postgresqlMap.put("int4", "integer");
        postgresqlMap.put("int8", "bigint");
        postgresqlMap.put("float4", "real");
        postgresqlMap.put("float8", "float");
    }

    public Class<?> getPreferredArrayType(String arraySqlTypeName, Boolean nullable, Boolean signed, int precision, int scale) {
        String sqlTypeName = arraySqlTypeName;
        if (sqlTypeName.startsWith("_"))
            sqlTypeName = sqlTypeName.substring(1);

        String std = postgresqlMap.get(sqlTypeName);
        if (std != null)
            sqlTypeName = std;

        Class<?> componentType = null;

        SqlTypeEnum sqlType = forSQLTypeName(sqlTypeName);
        if (sqlType != null)
            componentType = sqlType.getPreferredType(sqlTypeName, nullable, signed, precision, scale);

        if (componentType == null)
            return defaultType;

        Class<?> arrayType = java.lang.reflect.Array.newInstance(componentType, 0).getClass();
        return arrayType;
    }

    public Class<?> getPreferredDistinctType(String sqlTypeName, Boolean nullable, Boolean signed, int precision, int scale) {
        String noquote = sqlTypeName.replaceAll("\"", "");
        switch (noquote) {
            case "information_schema.sql_identifier":
                return String.class;
            case "information_schema.yes_or_no":
                return Boolean.class;
            case "information_schema.character_data":
                return String.class;
        }
        logger.warn("unsupported distinct type: " + sqlTypeName);
        return String.class;
    }

    public Class<?> getPreferredOtherType(String sqlTypeName, Boolean nullable, Boolean signed, int precision, int scale) {
        switch (sqlTypeName) {
            case "json":
            case "jsonb":
                return JsonVariant.class;

            case "inet":
                return InetAddress.class;

            case "uuid":
                return UUID.class;

            default:
                return Unknown.class;
        }
    }

    private static final Map<Integer, SqlTypeEnum> sqlTypeMap = new HashMap<>();
    private static final Map<String, SqlTypeEnum> _sqlTypeNameMap = new HashMap<>();

    static SqlTypeEnum getNamed(@NotNull String name) {
        name = name.toLowerCase();
        return _sqlTypeNameMap.get(name);
    }

    static void putNamed(@NotNull String name, @NotNull SqlTypeEnum val) {
        name = name.toLowerCase();
        _sqlTypeNameMap.put(name, val);
    }

    static {
        for (SqlTypeEnum type : values()) {
            sqlTypeMap.put(type.sqlType, type);
            putNamed(type.name(), type);
        }
    }

    public static SqlTypeEnum forSQLType(int sqlType) {
        SqlTypeEnum type = sqlTypeMap.get(sqlType);
        return type;
    }

    @NotNull
    public static SqlTypeEnum forSQLType(int sqlType, @NotNull SqlTypeEnum fallback) {
        SqlTypeEnum type = sqlTypeMap.get(sqlType);
        return type != null ? type : fallback;
    }

    public static SqlTypeEnum forSQLTypeName(@NotNull String sqlTypeName) {
        SqlTypeEnum type = getNamed(sqlTypeName);
        return type;
    }

    @NotNull
    public static SqlTypeEnum forSQLTypeName(String sqlTypeName, @NotNull SqlTypeEnum fallback) {
        if (sqlTypeName == null)
            return fallback;
        SqlTypeEnum type = getNamed(sqlTypeName);
        return type != null ? type : fallback;
    }

    public static SqlTypeEnum forSQLTypeName(int sqlType, String sqlTypeName) {
        if (sqlTypeName != null) {
            SqlTypeEnum type = getNamed(sqlTypeName);
            if (type != null)
                return type;
        }

        SqlTypeEnum type = sqlTypeMap.get(sqlType);
        return type;
    }

    @NotNull
    public static SqlTypeEnum forSQLTypeName(int sqlType, String sqlTypeName, @NotNull SqlTypeEnum fallback) {
        if (sqlTypeName != null) {
            SqlTypeEnum type = getNamed(sqlTypeName);
            if (type != null)
                return type;
        }

        SqlTypeEnum type = sqlTypeMap.get(sqlType);
        if (type != null)
            return type;

        return fallback;
    }

}
