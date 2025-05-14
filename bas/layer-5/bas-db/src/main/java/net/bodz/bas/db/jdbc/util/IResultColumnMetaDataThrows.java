package net.bodz.bas.db.jdbc.util;

import java.sql.SQLException;

import net.bodz.bas.t.catalog.NullableType;

/**
 * @see java.sql.ResultSetMetaData
 */
public interface IResultColumnMetaDataThrows {

    boolean isAutoIncrement()
            throws SQLException;

    boolean isCaseSensitive()
            throws SQLException;

    boolean isSearchable()
            throws SQLException;

    boolean isCurrency()
            throws SQLException;

    NullableType getNullableType()
            throws SQLException;

    default boolean isNullable()
            throws SQLException {
        return getNullableType() == NullableType.NULLABLE;
    }

    default boolean isNoNulls()
            throws SQLException {
        return getNullableType() == NullableType.NO_NULLS;
    }

    boolean isSigned()
            throws SQLException;

    int getColumnDisplaySize()
            throws SQLException;

    String getColumnLabel()
            throws SQLException;

    String getColumnName()
            throws SQLException;

    String getSchemaName()
            throws SQLException;

    int getPrecision()
            throws SQLException;

    int getScale()
            throws SQLException;

    String getTableName()
            throws SQLException;

    String getCatalogName()
            throws SQLException;

    int getColumnType()
            throws SQLException;

    String getColumnTypeName()
            throws SQLException;

    boolean isReadOnly()
            throws SQLException;

    boolean isWritable()
            throws SQLException;

    boolean isDefinitelyWritable()
            throws SQLException;

    String getColumnClassName()
            throws SQLException;

}
