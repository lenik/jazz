package net.bodz.bas.t.catalog;

import net.bodz.bas.err.DuplicatedKeyException;

public interface IMutableSchemaMetadata
        extends
            ISchemaMetadata {

    void setParent(ICatalogMetadata parent);

    /**
     * @throws DuplicatedKeyException
     *             When table with same name was existed.
     */
    void addTable(ITableMetadata table);

    boolean removeTable(ITableMetadata table);

    boolean removeTable(String tableName);

}
