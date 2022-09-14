package net.bodz.bas.t.catalog;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class SQLTypes {

    static final Logger logger = LoggerFactory.getLogger(SQLTypes.class);

    static boolean aggressive = false;

    // 4 294 967 296
    static final int MAXLEN_FOR_INT = aggressive ? 10 : 9;

    // 18 446 744 073 709 551 616
    static final int MAXLEN_FOR_LONG = aggressive ? 20 : 19;

    public static Class<?> toJavaType(IColumnMetadata column) {
        int sqlType = column.getSqlType();
        String sqlTypeName = column.getSqlTypeName();
        boolean nullable = column.isNullable();
        int precision = column.getPrecision();
        int scale = column.getScale();
        return toJavaType(sqlType, sqlTypeName, precision, scale, nullable);
    }

    public static Class<?> toJavaType(int sqlType, String sqlTypeName, int precision, int scale, boolean nullable) {
        switch (sqlType) {
        case Types.CHAR:
        case Types.VARCHAR:
        case Types.LONGVARCHAR:
        case Types.NCHAR:
        case Types.NVARCHAR:
        case Types.LONGNVARCHAR:
            return String.class;

        case Types.NUMERIC:
        case Types.DECIMAL:
            if (scale == 0) {
                if (precision < 5) // 65 536
                    return nullable ? Short.class : short.class;
                if (precision <= MAXLEN_FOR_INT)
                    return nullable ? Integer.class : int.class;
                if (precision <= MAXLEN_FOR_LONG)
                    return nullable ? Long.class : long.class;
                return BigInteger.class;
            } else {
                return BigDecimal.class;
            }

        case Types.BIT:
            return nullable ? Boolean.class : boolean.class;

        case Types.TINYINT:
            return nullable ? Byte.class : byte.class;

        case Types.SMALLINT:
            return nullable ? Short.class : short.class;

        case Types.INTEGER:
            return nullable ? Integer.class : int.class;

        case Types.BIGINT:
            return nullable ? Long.class : long.class;

        case Types.REAL:
            return nullable ? Float.class : float.class;

        case Types.FLOAT:
        case Types.DOUBLE:
            return nullable ? Double.class : double.class;

        case Types.BINARY:
        case Types.VARBINARY:
        case Types.LONGVARBINARY:
            return byte[].class;

        case Types.DATE:
            return Date.class;
        case Types.TIME:
            return Time.class;
        case Types.TIMESTAMP:
            return Timestamp.class;

        case Types.CLOB:
        case Types.NCLOB:
            return Clob.class;

        case Types.BLOB:
            return Blob.class;

        case Types.ARRAY:
            switch (sqlTypeName) {
            case "_bpchar":
            case "_varchar":
            case "_text":
                return String[].class;

            case "_date":
                return Date[].class;

            case "_time":
                return Time[].class;

            case "_timestamp":
                return Timestamp.class;

            case "bit":
            case "_int2":
            case "_int4":
                return int[].class;

            case "_int8":
                return long[].class;

            case "_float4":
                return float[].class;

            case "_float8":
                return double[].class;

            case "_money":
                return Currency[].class;

            default:
            }
            return Object[].class;

        case Types.DISTINCT:
            // mapping of underlying type
            logger.error("unsupport distinct type.");
            return Object.class;

        case Types.REF:
            return Ref.class;

        case Types.JAVA_OBJECT:
            return Object.class;

        default:
            return Object.class;
        }
    }

    static Map<Integer, String> intToNameMap = new HashMap<>();
    static Map<String, Integer> nameToIntMap = new HashMap<>();

    static {
        try {
            for (Field field : Types.class.getDeclaredFields()) {
                // if (!field.isAccessible()) continue;
                int modifiers = field.getModifiers();
                if (!Modifier.isPublic(modifiers))
                    continue;
                if (!Modifier.isStatic(modifiers))
                    continue;
                if (field.getType() != int.class)
                    continue;
                String name = field.getName();
                Integer value = (Integer) field.get(null);
                intToNameMap.put(value, name);
                nameToIntMap.put(name, value);
            }
        } catch (Exception e) {
            throw new UnexpectedException(e);
        }
    }

    public static String getTypeName(int type) {
        String name = intToNameMap.get(type);
        return name;
    }

    public static Integer getTypeInt(String name) {
        return nameToIntMap.get(name);
    }

    public static int getTypeInt(String name, int defaultValue) {
        Integer val = nameToIntMap.get(name);
        if (val != null)
            return val.intValue();
        else
            return defaultValue;
    }

}
