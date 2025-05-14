package net.bodz.bas.db.jdbc.util;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.catalog.NullableType;

public interface IResultColumnMetaData
        extends IResultColumnMetaDataThrows {

    @Override
    boolean isAutoIncrement();

    @Override
    boolean isCaseSensitive();

    @Override
    boolean isSearchable();

    @Override
    boolean isCurrency();

    @NotNull
    @Override
    NullableType getNullableType();

    default boolean isNullable() {
        return getNullableType() == NullableType.NULLABLE;
    }

    default boolean isNoNulls() {
        return getNullableType() == NullableType.NO_NULLS;
    }

    @Override
    boolean isSigned();

    @Override
    int getColumnDisplaySize();

    @Override
    String getColumnLabel();

    @Override
    String getColumnName();

    @Override
    String getSchemaName();

    @Override
    int getPrecision();

    @Override
    int getScale();

    @Override
    String getTableName();

    @Override
    String getCatalogName();

    @Override
    int getColumnType();

    @Override
    String getColumnTypeName();

    @Override
    boolean isReadOnly();

    @Override
    boolean isWritable();

    @Override
    boolean isDefinitelyWritable();

    @Override
    String getColumnClassName();

}
