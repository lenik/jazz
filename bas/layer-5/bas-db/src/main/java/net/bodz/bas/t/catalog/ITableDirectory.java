package net.bodz.bas.t.catalog;

import net.bodz.bas.err.DuplicatedKeyException;

public interface ITableDirectory {

    /**
     * Check if the given name is valid with-in this directory.
     *
     * @return <code>false</code> if the name is outside of this directory.
     */
    boolean isValidName(QualifiedTableName tableName);

    /**
     * Check if all table names in this directory are inside the given schema namespace.
     */
    boolean isValidNameOf(String catalogName);

    default void checkValidName(QualifiedTableName tableName) {
        if (!isValidName(tableName))
            throw new IllegalArgumentException("Invalid name: " + tableName);
    }

    default TableList findTables(QualifiedTableName pattern) {
        return findTables(pattern, false);
    }

    TableList findTables(QualifiedTableName pattern, boolean ignoreCase);

    default ITableMetadata getTable(QualifiedTableName pattern) {
        return getTable(pattern, false);
    }

    default ITableMetadata getTable(QualifiedTableName pattern, boolean ignoreCase) {
        TableList tableList = findTables(pattern, ignoreCase);
        if (tableList.size() > 1)
            throw new DuplicatedKeyException("More than single table matched: " + pattern);
        if (tableList.isEmpty())
            return null;
        else
            return tableList.get(0);
    }

}
