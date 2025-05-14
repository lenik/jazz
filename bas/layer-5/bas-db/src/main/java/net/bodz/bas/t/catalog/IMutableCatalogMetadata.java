package net.bodz.bas.t.catalog;

import net.bodz.bas.db.sql.dialect.ISqlDialect;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;

public interface IMutableCatalogMetadata
        extends ICatalogMetadata,
                IMutableJavaType {

    void setDialect(ISqlDialect dialect);

    /**
     * @throws DuplicatedKeyException When schema with same name was existed.
     */
    void addSchema(@NotNull ISchemaMetadata schema);

    boolean removeSchema(@NotNull ISchemaMetadata schema);

    boolean removeSchema(@Nullable String schemaName);

    void addTable(@NotNull ITableMetadata table);

    boolean removeTable(@NotNull ITableMetadata table);

    boolean removeTable(@NotNull TableOid table);

}
