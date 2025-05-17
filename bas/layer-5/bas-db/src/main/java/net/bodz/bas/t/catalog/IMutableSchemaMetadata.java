package net.bodz.bas.t.catalog;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.meta.decl.NotNull;

public interface IMutableSchemaMetadata
        extends ISchemaMetadata,
                IMutableJavaQName {

    void setParent(ICatalogMetadata parent);

    /**
     * @throws DuplicatedKeyException When table with same name was existed.
     */
    void addTable(@NotNull ITableMetadata table);

    boolean removeTable(@NotNull ITableMetadata table);

    boolean removeTable(@NotNull String tableName);

}
