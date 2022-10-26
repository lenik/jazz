package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.util.List;

import net.bodz.bas.err.DuplicatedKeyException;

public interface ITableDirectory {

    /**
     * Check if the given name is valid with-in this directory.
     *
     * @return <code>false</code> if the name is outside of this directory.
     */
    boolean isValidTableId(TableOid oid);

    /**
     * Check if all table names in this directory are inside the given schema namespace.
     */
    boolean isValidIdOf(String catalogName);

    default void checkTableId(TableOid oid) {
        if (!isValidTableId(oid))
            throw new IllegalArgumentException("Invalid table id: " + oid);
    }

    default List<ITableMetadata> findTables(TableOid pattern) {
        return findTables(pattern, false);
    }

    List<ITableMetadata> findTables(TableOid pattern, boolean ignoreCase);

    default ITableMetadata getTable(TableOid pattern) {
        return getTable(pattern, false);
    }

    default ITableMetadata getTable(TableOid pattern, boolean ignoreCase) {
        List<ITableMetadata> tableList = findTables(pattern, ignoreCase);
        if (tableList.size() > 1)
            throw new DuplicatedKeyException("More than single table matched: " + pattern);
        if (tableList.isEmpty())
            return null;
        else
            return tableList.get(0);
    }

    default ITableMetadata autoLoadTableFromJDBC(TableOid oid, Connection autoLoadConnection) {
        LoadFromJDBCOptions options = new LoadFromJDBCOptions();
        return autoLoadTableFromJDBC(oid, autoLoadConnection, options);
    }

    ITableMetadata autoLoadTableFromJDBC(TableOid oid, Connection autoLoadConnection, LoadFromJDBCOptions options);

}
