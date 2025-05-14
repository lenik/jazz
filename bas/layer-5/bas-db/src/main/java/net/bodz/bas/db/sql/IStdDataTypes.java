package net.bodz.bas.db.sql;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.function.Function;

import net.bodz.bas.db.jdbc.util.IResultColumnMetaData;

public interface IStdDataTypes {

    DataType ARRAY = newType(java.sql.Array.class, Types.ARRAY, "array", Refiners.R_ARRAY);
    DataType BIGINT = newType(Long.class, Types.BIGINT, "bigint", metaData -> {
        if (metaData.isSigned())
            return metaData.isNullable() ? Long.class : long.class;
        else
            return BigInteger.class;
    });
    DataType BINARY = newType(byte[].class, Types.BINARY, "binary");
    DataType BIT = newType(Boolean.class, Types.BIT, "bit", metaData -> {
        if (metaData.getPrecision() > 1)
            return byte[].class;
        if (metaData.isSigned()) // 0, 1, -1
            return metaData.isNullable() ? Byte.class : byte.class;
        else // 0, 1
            return metaData.isNullable() ? Boolean.class : boolean.class;
    });
    DataType BLOB = newType(java.sql.Blob.class, Types.BLOB, "blob");
    DataType BOOLEAN = newType(Boolean.class, Types.BOOLEAN, "boolean");
    DataType CHAR = newType(String.class, Types.CHAR, "char", Refiners.R_CHAR);
    DataType CLOB = newType(java.sql.Clob.class, Types.CLOB, "clob");
    DataType DATALINK = newType(Object.class, Types.DATALINK, "datalink");
    DataType DATE = newType(LocalDate.class, Types.DATE, "date");
    DataType DECIMAL = newType(BigDecimal.class, Types.DECIMAL, "decimal", Refiners.R_NUMERIC);
    DataType DISTINCT = newType(Object.class, Types.DISTINCT, "distinct");
    DataType DOUBLE = newType(Double.class, Types.DOUBLE, "double", Refiners.R_DOUBLE);
    DataType FLOAT = newType(Double.class, Types.FLOAT, "float", Refiners.R_DOUBLE);
    DataType INTEGER = newType(Integer.class, Types.INTEGER, "integer", metaData -> {
        if (metaData.isSigned())
            return metaData.isNullable() ? Integer.class : int.class;
        else
            return metaData.isNullable() ? Long.class : long.class;
    });
    DataType JAVA_OBJECT = newType(Object.class, Types.JAVA_OBJECT, "java_object");
    DataType LONGNVARCHAR = newType(String.class, Types.LONGNVARCHAR, "longnvarchar");
    DataType LONGVARBINARY = newType(byte[].class, Types.LONGVARBINARY, "longvarbinary");
    DataType LONGVARCHAR = newType(String.class, Types.LONGVARCHAR, "longvarchar");
    DataType NCHAR = newType(String.class, Types.NCHAR, "nchar", Refiners.R_CHAR);
    DataType NCLOB = newType(java.sql.NClob.class, Types.NCLOB, "nclob");
    DataType NULL = newType(Object.class, Types.NULL, "null");
    DataType NUMERIC = newType(BigDecimal.class, Types.NUMERIC, "numeric", Refiners.R_NUMERIC);
    DataType NVARCHAR = newType(String.class, Types.NVARCHAR, "nvarchar");
    DataType OTHER = newType(Object.class, Types.OTHER, "other");
    DataType REAL = newType(Float.class, Types.REAL, "real", Refiners.R_FLOAT);
    DataType REF_CURSOR = newType(java.sql.Ref.class, Types.REF_CURSOR, "ref_cursor");
    DataType REF = newType(java.sql.Ref.class, Types.REF, "ref");
    DataType ROWID = newType(java.sql.RowId.class, Types.ROWID, "rowid");
    DataType SMALLINT = newType(Short.class, Types.SMALLINT, "smallint", metaData -> {
        if (metaData.isSigned())
            return metaData.isNullable() ? Short.class : short.class;
        else
            return metaData.isNullable() ? Integer.class : int.class;
    });
    DataType SQLXML = newType(java.sql.SQLXML.class, Types.SQLXML, "sqlxml");
    DataType STRUCT = newType(java.sql.Struct.class, Types.STRUCT, "struct");
    DataType TIME = newType(LocalTime.class, Types.TIME, "time");
    DataType TIMESTAMP = newType(LocalDateTime.class, Types.TIMESTAMP, "timestamp");
    DataType TIMESTAMP_WITH_TIMEZONE = newType(OffsetDateTime.class, Types.TIMESTAMP_WITH_TIMEZONE, "timestamp with timezone");
    DataType TIME_WITH_TIMEZONE = newType(OffsetTime.class, Types.TIME_WITH_TIMEZONE, "time with timezone");
    DataType TINYINT = newType(Short.class, Types.TINYINT, "tinyint", metaData -> {
        if (metaData.isSigned())
            return metaData.isNullable() ? Byte.class : byte.class;
        else
            return metaData.isNullable() ? Short.class : short.class;
    });
    DataType VARBINARY = newType(byte[].class, Types.VARBINARY, "varbinary");
    DataType VARCHAR = newType(String.class, Types.VARCHAR, "varchar");

    static DataType newType(Class<?> type, int sqlType, String sqlTypeName) {
        return new DataType(type, sqlType, sqlTypeName, true);
    }

    static DataType newType(Class<?> type, int sqlType, String name, Function<IResultColumnMetaData, Class<?>> refiner) {
        return new DataType(type, sqlType, name, true, refiner);

    }

}

class Refiners {

    static final Function<IResultColumnMetaData, Class<?>> R_NUMERIC = metaData -> {
        // 65 536
        final int MAXLEN_FOR_SHORT = 5;

        // 4 294 967 296
        final int MAXLEN_FOR_INT = 10;

        // 18 446 744 073 709 551 616
        final int MAXLEN_FOR_LONG = 20;

        boolean nullable = metaData.isNullable();
        int precision = metaData.getPrecision();
        if (metaData.getScale() == 0) {
            if (precision < MAXLEN_FOR_SHORT)
                return nullable ? Short.class : short.class;
            if (precision <= MAXLEN_FOR_INT)
                return nullable ? Integer.class : int.class;
            if (precision <= MAXLEN_FOR_LONG)
                return nullable ? Long.class : long.class;
            return BigInteger.class;
        }
        return BigDecimal.class;
    };

    static final Function<IResultColumnMetaData, Class<?>> R_CHAR = metaData -> {
        if (metaData.getPrecision() > 1)
            return String.class;
        return metaData.isNullable() ? Character.class : char.class;
    };

    static final Function<IResultColumnMetaData, Class<?>> R_FLOAT = metaData -> {
        return metaData.isNullable() ? Float.class : float.class;
    };

    static final Function<IResultColumnMetaData, Class<?>> R_DOUBLE = metaData -> {
        return metaData.isNullable() ? Double.class : double.class;
    };

    static final Function<IResultColumnMetaData, Class<?>> R_ARRAY = metaData -> {
//        String sqlTypeName = metaData.getColumnTypeName();
        return java.sql.Array.class;
    };

}