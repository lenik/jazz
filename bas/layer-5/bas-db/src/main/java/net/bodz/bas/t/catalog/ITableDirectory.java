package net.bodz.bas.t.catalog;

import java.sql.Connection;

import net.bodz.bas.err.DuplicatedKeyException;

public interface ITableDirectory {

    /**
     * Check if the given name is valid with-in this directory.
     *
     * @return <code>false</code> if the name is outside of this directory.
     */
    boolean isValidTableId(TableId id);

    /**
     * Check if all table names in this directory are inside the given schema namespace.
     */
    boolean isValidIdOf(String catalogName);

    default void checkTableId(TableId id) {
        if (!isValidTableId(id))
            throw new IllegalArgumentException("Invalid table id: " + id);
    }

    default TableList findTables(TableId pattern) {
        return findTables(pattern, false);
    }

    TableList findTables(TableId pattern, boolean ignoreCase);

    default ITableMetadata getTable(TableId pattern) {
        return getTable(pattern, false);
    }

    default ITableMetadata getTable(TableId pattern, boolean ignoreCase) {
        TableList tableList = findTables(pattern, ignoreCase);
        if (tableList.size() > 1)
            throw new DuplicatedKeyException("More than single table matched: " + pattern);
        if (tableList.isEmpty())
            return null;
        else
            return tableList.get(0);
    }

    default ITableMetadata autoLoadTableFromJDBC(TableId id, Connection autoLoadConnection) {
        return autoLoadTableFromJDBC(id, false, autoLoadConnection);
    }

    ITableMetadata autoLoadTableFromJDBC(TableId id, boolean ignoreCase, Connection autoLoadConnection);

}
