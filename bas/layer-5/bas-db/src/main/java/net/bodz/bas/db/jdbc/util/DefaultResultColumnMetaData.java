package net.bodz.bas.db.jdbc.util;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.catalog.NullableType;

public class DefaultResultColumnMetaData
        implements IResultColumnMetaDataThrows {

    ResultSetMetaData metaData;
    int column;

    public DefaultResultColumnMetaData(@NotNull ResultSetMetaData metaData, int column) {
        this.metaData = metaData;
        this.column = column;
    }

    @Override
    public String getCatalogName()
            throws SQLException {
        return metaData.getCatalogName(column);
    }

    @Override
    public boolean isAutoIncrement()
            throws SQLException {
        return metaData.isAutoIncrement(column);
    }

    @Override
    public boolean isCaseSensitive()
            throws SQLException {
        return metaData.isCaseSensitive(column);
    }

    @Override
    public boolean isSearchable()
            throws SQLException {
        return metaData.isSearchable(column);
    }

    @Override
    public boolean isCurrency()
            throws SQLException {
        return metaData.isCurrency(column);
    }

    @Override
    public NullableType getNullableType()
            throws SQLException {
        return NullableType.ofIntValue(metaData.isNullable(column));
    }

    @Override
    public boolean isSigned()
            throws SQLException {
        return metaData.isSigned(column);
    }

    @Override
    public int getColumnDisplaySize()
            throws SQLException {
        return metaData.getColumnDisplaySize(column);
    }

    @Override
    public String getColumnLabel()
            throws SQLException {
        return metaData.getColumnLabel(column);
    }

    @Override
    public String getColumnName()
            throws SQLException {
        return metaData.getColumnName(column);
    }

    @Override
    public String getSchemaName()
            throws SQLException {
        return metaData.getSchemaName(column);
    }

    @Override
    public int getPrecision()
            throws SQLException {
        return metaData.getPrecision(column);
    }

    @Override
    public int getScale()
            throws SQLException {
        return metaData.getScale(column);
    }

    @Override
    public String getTableName()
            throws SQLException {
        return metaData.getTableName(column);
    }

    @Override
    public int getColumnType()
            throws SQLException {
        return metaData.getColumnType(column);
    }

    @Override
    public String getColumnTypeName()
            throws SQLException {
        return metaData.getColumnTypeName(column);
    }

    @Override
    public boolean isReadOnly()
            throws SQLException {
        return metaData.isReadOnly(column);
    }

    @Override
    public boolean isWritable()
            throws SQLException {
        return metaData.isWritable(column);
    }

    @Override
    public boolean isDefinitelyWritable()
            throws SQLException {
        return metaData.isDefinitelyWritable(column);
    }

    @Override
    public String getColumnClassName()
            throws SQLException {
        return metaData.getColumnClassName(column);
    }

}
