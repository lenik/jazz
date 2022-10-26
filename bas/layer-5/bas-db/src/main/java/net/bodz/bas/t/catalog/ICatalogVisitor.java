package net.bodz.bas.t.catalog;

public interface ICatalogVisitor {

    default boolean beginCatalog(ICatalogMetadata catalog) {
        return true;
    }

    default void endCatalog(ICatalogMetadata catalog) {
    }

    default boolean beginSchema(ISchemaMetadata schema) {
        return true;
    }

    default void endSchema(ISchemaMetadata schema) {
    }

    default boolean beginTables(ISchemaMetadata schema) {
        return true;
    }

    default void endTables(ISchemaMetadata schema) {
    }

    default boolean beginViews(ISchemaMetadata schema) {
        return true;
    }

    default void endViews(ISchemaMetadata schema) {
    }

    default boolean beginTableOrView(ITableMetadata table) {
        return true;
    }

    default void endTableOrView(ITableMetadata table) {
    }

    default boolean beginColumns(ITableMetadata table) {
        return true;
    }

    default void endColumns(ITableMetadata table) {
    }

    default void column(IColumnMetadata column) {
    }

    default void primaryKey(ITableMetadata table, TableKey key) {
    }

    default void foreignKey(ITableMetadata table, CrossReference crossRef) {
    }

    default boolean beginRowSet(IRowSetMetadata rowSet) {
        return true;
    }

    default void endRowSet(IRowSetMetadata rowSet) {
    }

    default void row(IRow row) {
    }

}
