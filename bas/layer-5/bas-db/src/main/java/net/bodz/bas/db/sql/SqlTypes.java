package net.bodz.bas.db.sql;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

public class SqlTypes {

    public static final DataType ARRAY = new DataType(java.sql.Array.class, Types.ARRAY, "array", true);
    public static final DataType BIGINT = new DataType(Long.class, Types.BIGINT, "bigint", true);
    public static final DataType BINARY = new DataType(byte[].class, Types.BINARY, "binary", true);
    public static final DataType BIT = new DataType(Boolean.class, Types.BIT, "bit", true);
    public static final DataType BLOB = new DataType(java.sql.Blob.class, Types.BLOB, "blob", true);
    public static final DataType BOOLEAN = new DataType(Boolean.class, Types.BOOLEAN, "boolean", true);
    public static final DataType CHAR = new DataType(String.class, Types.CHAR, "char", true);
    public static final DataType CLOB = new DataType(java.sql.Clob.class, Types.CLOB, "clob", true);
    public static final DataType DATALINK = new DataType(Object.class, Types.DATALINK, "datalink");
    public static final DataType DATE = new DataType(LocalDate.class, Types.DATE, "date", true);
    public static final DataType DECIMAL = new DataType(BigDecimal.class, Types.DECIMAL, "decimal", true);
    public static final DataType DISTINCT = new DataType(Object.class, Types.DISTINCT, "distinct", true);
    public static final DataType DOUBLE = new DataType(Double.class, Types.DOUBLE, "double", true);
    public static final DataType FLOAT = new DataType(Double.class, Types.FLOAT, "float", true);
    public static final DataType INTEGER = new DataType(Integer.class, Types.INTEGER, "integer", true);
    public static final DataType JAVA_OBJECT = new DataType(Object.class, Types.JAVA_OBJECT, "java_object", true);
    public static final DataType LONGNVARCHAR = new DataType(String.class, Types.LONGNVARCHAR, "longnvarchar", true);
    public static final DataType LONGVARBINARY = new DataType(byte[].class, Types.LONGVARBINARY, "longvarbinary", true);
    public static final DataType LONGVARCHAR = new DataType(String.class, Types.LONGVARCHAR, "longvarchar", true);
    public static final DataType NCHAR = new DataType(String.class, Types.NCHAR, "nchar", true);
    public static final DataType NCLOB = new DataType(java.sql.NClob.class, Types.NCLOB, "nclob", true);
    public static final DataType NULL = new DataType(Object.class, Types.NULL, "null", true);
    public static final DataType NUMERIC = new DataType(BigDecimal.class, Types.NUMERIC, "numeric", true);
    public static final DataType NVARCHAR = new DataType(String.class, Types.NVARCHAR, "nvarchar", true);
    public static final DataType OTHER = new DataType(Object.class, Types.OTHER, "other", true);
    public static final DataType REAL = new DataType(Float.class, Types.REAL, "real", true);
    public static final DataType REF_CURSOR = new DataType(java.sql.Ref.class, Types.REF_CURSOR, "ref_cursor", true);
    public static final DataType REF = new DataType(java.sql.Ref.class, Types.REF, "ref", true);
    public static final DataType ROWID = new DataType(java.sql.RowId.class, Types.ROWID, "rowid", true);
    public static final DataType SMALLINT = new DataType(Short.class, Types.SMALLINT, "smallint", true);
    public static final DataType SQLXML = new DataType(java.sql.SQLXML.class, Types.SQLXML, "sqlxml", true);
    public static final DataType STRUCT = new DataType(java.sql.Struct.class, Types.STRUCT, "struct", true);
    public static final DataType TIME = new DataType(LocalTime.class, Types.TIME, "time", true);
    public static final DataType TIMESTAMP = new DataType(LocalDateTime.class, Types.TIMESTAMP, "timestamp", true);
    public static final DataType TIMESTAMP_WITH_TIMEZONE = new DataType(OffsetDateTime.class, Types.TIMESTAMP_WITH_TIMEZONE, "timestamp with timezone", true);
    public static final DataType TIME_WITH_TIMEZONE = new DataType(OffsetTime.class, Types.TIME_WITH_TIMEZONE, "time with timezone", true);
    public static final DataType TINYINT = new DataType(Short.class, Types.TINYINT, "tinyint", true);
    public static final DataType VARBINARY = new DataType(byte[].class, Types.VARBINARY, "varbinary", true);
    public static final DataType VARCHAR = new DataType(String.class, Types.VARCHAR, "varchar", true);

}
