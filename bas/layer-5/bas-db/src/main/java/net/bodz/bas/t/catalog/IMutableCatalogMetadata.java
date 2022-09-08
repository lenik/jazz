package net.bodz.bas.t.catalog;

import net.bodz.bas.err.DuplicatedKeyException;

public interface IMutableCatalogMetadata
        extends
            ICatalogMetadata,
            IMutableJavaName {

    /**
     * @throws DuplicatedKeyException
     *             When schema with same name was existed.
     */
    void addSchema(ISchemaMetadata schema);

    boolean removeSchema(ISchemaMetadata schema);

    boolean removeSchema(String schemaName);

    void addTable(ITableMetadata table);

    boolean removeTable(ITableMetadata table);

    boolean removeTable(TableOid table);

}
