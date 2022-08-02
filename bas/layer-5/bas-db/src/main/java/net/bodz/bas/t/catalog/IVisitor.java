package net.bodz.bas.t.catalog;

public interface IVisitor {

    void beginCatalog(ICatalogMetadata catalog);

    void endCatalog(ICatalogMetadata catalog);

    void beginSchema(ISchemaMetadata schema);

    void endSchema(ISchemaMetadata schema);

    void beginTable(ITableMetadata table);

    void endTable(ITableMetadata table);

    void beginRowSet(IRowSetMetadata rowSet);

    void endRowSet(IRowSetMetadata rowSet);

    void column(IColumnMetadata column);

    void row(IRow row);

}
