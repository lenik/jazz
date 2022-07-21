package net.bodz.bas.t.table;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class SqlTypes {

    static final Logger logger = LoggerFactory.getLogger(SqlTypes.class);

    static boolean aggressive = true;

    // 4 294 967 296
    static final int MAXLEN_FOR_INT = aggressive ? 10 : 9;

    // 18 446 744 073 709 551 616
    static final int MAXLEN_FOR_LONG = aggressive ? 20 : 19;

    public static Class<?> toJavaType(IColumnMetadata column) {
        int sqlType = column.getSqlType();
        boolean nullable = column.isNullable();
        int precision = column.getPrecision();
        int scale = column.getScale();
        return toJavaType(sqlType, precision, scale, nullable);
    }

    public static Class<?> toJavaType(int sqlType, int precision, int scale, boolean nullable) {
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
            return Array.class;

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

}
