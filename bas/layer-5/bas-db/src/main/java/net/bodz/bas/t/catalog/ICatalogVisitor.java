package net.bodz.bas.t.catalog;

public interface ICatalogVisitor {

    default void beginCatalog(ICatalogMetadata catalog) {
    }

    default void endCatalog(ICatalogMetadata catalog) {
    }

    default void beginSchema(ISchemaMetadata schema) {
    }

    default void endSchema(ISchemaMetadata schema) {
    }

    default void beginTable(ITableMetadata table) {
    }

    default void endTable(ITableMetadata table) {
    }

    default void beginColumns(ITableMetadata table) {
    }

    default void endColumns(ITableMetadata table) {
    }

    default void column(IColumnMetadata column) {
    }

    default void primaryKey(ITableMetadata table, TableKey key) {
    }

    default void foreignKey(ITableMetadata table, CrossReference crossRef) {
    }

    default void beginRowSet(IRowSetMetadata rowSet) {
    }

    default void endRowSet(IRowSetMetadata rowSet) {
    }

    default void row(IRow row) {
    }

}
