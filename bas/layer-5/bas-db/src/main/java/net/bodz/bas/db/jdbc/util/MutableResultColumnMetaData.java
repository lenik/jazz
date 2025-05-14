package net.bodz.bas.db.jdbc.util;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.catalog.NullableType;

public class MutableResultColumnMetaData
        implements IResultColumnMetaData {

    String catalogName;
    boolean autoIncrement;
    boolean caseSensitive;
    boolean searchable;
    boolean currency;
    NullableType nullableType = NullableType.UNKNOWN;
    boolean signed;
    int columnDisplaySize;
    String columnLabel;
    String columnName;
    String schemaName;
    int precision;
    int scale;
    String tableName;
    int columnType;
    String columnTypeName;
    boolean readOnly;
    boolean writable;
    boolean definitelyWritable;
    String columnClassName;

    @Override
    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    @Override
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    @Override
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    @Override
    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    @Override
    public String getColumnClassName() {
        return columnClassName;
    }

    public void setColumnClassName(String columnClassName) {
        this.columnClassName = columnClassName;
    }

    @Override
    public int getColumnDisplaySize() {
        return columnDisplaySize;
    }

    public void setColumnDisplaySize(int columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    @Override
    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    @Override
    public boolean isCurrency() {
        return currency;
    }

    public void setCurrency(boolean currency) {
        this.currency = currency;
    }

    @Override
    public boolean isDefinitelyWritable() {
        return definitelyWritable;
    }

    public void setDefinitelyWritable(boolean definitelyWritable) {
        this.definitelyWritable = definitelyWritable;
    }

    @NotNull
    @Override
    public NullableType getNullableType() {
        return nullableType;
    }

    public void setNullableType(@NotNull NullableType nullableType) {
        this.nullableType = nullableType;
    }

    @Override
    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    @Override
    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    @Override
    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    public MutableResultColumnMetaData readObject(ResultSetMetaData metaData, int column)
            throws SQLException {
        catalogName = metaData.getCatalogName(column);
        autoIncrement = metaData.isAutoIncrement(column);
        caseSensitive = metaData.isCaseSensitive(column);
        searchable = metaData.isSearchable(column);
        currency = metaData.isCurrency(column);
        nullableType = NullableType.ofIntValue(metaData.isNullable(column));
        signed = metaData.isSigned(column);
        columnDisplaySize = metaData.getColumnDisplaySize(column);
        columnLabel = metaData.getColumnLabel(column);
        columnName = metaData.getColumnName(column);
        schemaName = metaData.getSchemaName(column);
        precision = metaData.getPrecision(column);
        scale = metaData.getScale(column);
        tableName = metaData.getTableName(column);
        columnType = metaData.getColumnType(column);
        columnTypeName = metaData.getColumnTypeName(column);
        readOnly = metaData.isReadOnly(column);
        writable = metaData.isWritable(column);
        definitelyWritable = metaData.isDefinitelyWritable(column);
        columnClassName = metaData.getColumnClassName(column);
        return this;
    }

}
