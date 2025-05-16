package net.bodz.bas.db.sql;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

import static net.bodz.bas.db.sql.DataType.newType;

public interface IStdDataTypes {

    DataType ARRAY = newType(java.sql.Array.class, Types.ARRAY, "array")//
            .refiner(Refiners.R_ARRAY).build();
    DataType BIGINT = newType(Long.class, Types.BIGINT, "bigint")//
            .refiner(column -> {
                if (column.isSigned())
                    return column.isNullable() ? Long.class : long.class;
                else
                    return BigInteger.class;
            }).build();
    DataType BINARY = newType(byte[].class, Types.BINARY, "binary").build();
    DataType BIT = newType(Boolean.class, Types.BIT, "bit")//
            .refiner(column -> {
                if (column.getPrecision() > 1)
                    return byte[].class;
                if (column.isSigned()) // 0, 1, -1
                    return column.isNullable() ? Byte.class : byte.class;
                else // 0, 1
                    return column.isNullable() ? Boolean.class : boolean.class;
            }).build();
    // java.sql.Blob
    DataType BLOB = newType(byte[].class, Types.BLOB, "blob").build();
    DataType BOOLEAN = newType(Boolean.class, Types.BOOLEAN, "boolean").build();
    DataType CHAR = newType(String.class, Types.CHAR, "char")//
            .refiner(Refiners.R_CHAR).build();
    DataType CLOB = newType(String.class, Types.CLOB, "clob").build(); // java.sql.Clob
    DataType DATALINK = newType(Object.class, Types.DATALINK, "datalink").build();
    DataType DATE = newType(LocalDate.class, Types.DATE, "date").build();
    DataType DECIMAL = newType(BigDecimal.class, Types.DECIMAL, "decimal")//
            .refiner(Refiners.R_NUMERIC).build();
    DataType DISTINCT = newType(Object.class, Types.DISTINCT, "distinct").build();
    DataType DOUBLE = newType(Double.class, Types.DOUBLE, "double")//
            .refiner(Refiners.R_DOUBLE).build();
    DataType FLOAT = newType(Double.class, Types.FLOAT, "float")//
            .refiner(Refiners.R_DOUBLE).build();
    DataType INTEGER = newType(Integer.class, Types.INTEGER, "integer")//
            .refiner(column -> {
                if (column.isSigned())
                    return column.isNullable() ? Integer.class : int.class;
                else
                    return column.isNullable() ? Long.class : long.class;
            }).build();
    DataType JAVA_OBJECT = newType(Object.class, Types.JAVA_OBJECT, "java_object").build();
    DataType LONGNVARCHAR = newType(String.class, Types.LONGNVARCHAR, "longnvarchar").build();
    DataType LONGVARBINARY = newType(byte[].class, Types.LONGVARBINARY, "longvarbinary").build();
    DataType LONGVARCHAR = newType(String.class, Types.LONGVARCHAR, "longvarchar").build();
    DataType NCHAR = newType(String.class, Types.NCHAR, "nchar")//
            .refiner(Refiners.R_CHAR).build();
    DataType NCLOB = newType(String.class, Types.NCLOB, "nclob").build(); // java.sql.NClob
    DataType NULL = newType(Object.class, Types.NULL, "null").build();
    DataType NUMERIC = newType(BigDecimal.class, Types.NUMERIC, "numeric")//
            .refiner(Refiners.R_NUMERIC).build();
    DataType NVARCHAR = newType(String.class, Types.NVARCHAR, "nvarchar").build();
    DataType OTHER = newType(Object.class, Types.OTHER, "other").build();
    DataType REAL = newType(Float.class, Types.REAL, "real")//
            .refiner(Refiners.R_FLOAT).build();
    DataType REF_CURSOR = newType(java.sql.Ref.class, Types.REF_CURSOR, "ref_cursor").build();
    DataType REF = newType(java.sql.Ref.class, Types.REF, "ref").build();
    DataType ROWID = newType(java.sql.RowId.class, Types.ROWID, "rowid").build();
    DataType SMALLINT = newType(Short.class, Types.SMALLINT, "smallint")//
            .refiner(column -> {
                if (column.isSigned())
                    return column.isNullable() ? Short.class : short.class;
                else
                    return column.isNullable() ? Integer.class : int.class;
            }).build();
    DataType SQLXML = newType(java.sql.SQLXML.class, Types.SQLXML, "sqlxml").build();
    DataType STRUCT = newType(java.sql.Struct.class, Types.STRUCT, "struct").build();
    DataType TIME = newType(LocalTime.class, Types.TIME, "time").build();
    DataType TIMESTAMP = newType(LocalDateTime.class, Types.TIMESTAMP, "timestamp").build();
    DataType TIMESTAMP_WITH_TIMEZONE = newType(OffsetDateTime.class, Types.TIMESTAMP_WITH_TIMEZONE, "timestamp with timezone").build();
    DataType TIME_WITH_TIMEZONE = newType(OffsetTime.class, Types.TIME_WITH_TIMEZONE, "time with timezone").build();
    DataType TINYINT = newType(Short.class, Types.TINYINT, "tinyint")//
            .refiner(column -> {
                if (column.isSigned())
                    return column.isNullable() ? Byte.class : byte.class;
                else
                    return column.isNullable() ? Short.class : short.class;
            }).build();
    DataType VARBINARY = newType(byte[].class, Types.VARBINARY, "varbinary").build();
    DataType VARCHAR = newType(String.class, Types.VARCHAR, "varchar").build();

}

class Refiners {

    static final IRefiner R_NUMERIC = column -> {
        // 65 536
        final int MAXLEN_FOR_SHORT = 5;

        // 4 294 967 296
        final int MAXLEN_FOR_INT = 10;

        // 18 446 744 073 709 551 616
        final int MAXLEN_FOR_LONG = 20;

        boolean nullable = column.isNullable();
        int precision = column.getPrecision();
        if (column.getScale() == 0) {
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

    static final IRefiner R_CHAR = column -> {
        if (column.getPrecision() > 1)
            return String.class;
        return column.isNullable() ? Character.class : char.class;
    };

    static final IRefiner R_FLOAT = column -> {
        return column.isNullable() ? Float.class : float.class;
    };

    static final IRefiner R_DOUBLE = column -> {
        return column.isNullable() ? Double.class : double.class;
    };

    static final IRefiner R_ARRAY = column -> {
//        String sqlTypeName = column.getColumnTypeName();
        return java.sql.Array.class;
    };

}