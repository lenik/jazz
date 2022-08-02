package net.bodz.bas.t.table;

import net.bodz.bas.err.DuplicatedKeyException;

public interface IMutableCatalogMetadata
        extends
            ICatalogMetadata {

    /**
     * @throws DuplicatedKeyException
     *             When schema with same name was existed.
     */
    void addSchema(ISchemaMetadata schema);

    boolean removeSchema(ISchemaMetadata schema);

    boolean removeSchema(String schemaName);

}
