package net.bodz.bas.t.catalog;

import net.bodz.bas.io.ITreeOut;

public class TreeOutVisitor
        implements
            ICatalogVisitor {

    ITreeOut out;
    ICatalogVisitor impl;

    public TreeOutVisitor(ITreeOut out, ICatalogVisitor impl) {
        this.out = out;
        this.impl = impl;
    }

    @Override
    public void beginCatalog(ICatalogMetadata catalog) {
        impl.beginCatalog(catalog);
        out.enter();
    }

    @Override
    public void endCatalog(ICatalogMetadata catalog) {
        out.leave();
        impl.endCatalog(catalog);
    }

    @Override
    public void beginSchema(ISchemaMetadata schema) {
        impl.beginSchema(schema);
        out.enter();
    }

    @Override
    public void endSchema(ISchemaMetadata schema) {
        out.leave();
        impl.endSchema(schema);
    }

    @Override
    public void beginTables(ISchemaMetadata schema) {
        impl.beginTables(schema);
        out.enter();
    }

    @Override
    public void endTables(ISchemaMetadata schema) {
        out.leave();
        impl.endTables(schema);
    }

    @Override
    public void beginViews(ISchemaMetadata schema) {
        impl.beginViews(schema);
        out.enter();
    }

    @Override
    public void endViews(ISchemaMetadata schema) {
        out.leave();
        impl.endSchema(schema);
    }

    @Override
    public void beginTableView(ITableViewMetadata table) {
        impl.beginTableView(table);
        out.enter();
    }

    @Override
    public void endTableView(ITableViewMetadata table) {
        out.leave();
        impl.endTableView(table);
    }

    @Override
    public void beginColumns(ITableViewMetadata table) {
        impl.beginColumns(table);
        out.enter();
    }

    @Override
    public void endColumns(ITableViewMetadata table) {
        out.leave();
        impl.endColumns(table);
    }

    @Override
    public void beginRowSet(IRowSetMetadata rowSet) {
        impl.beginRowSet(rowSet);
        out.enter();
    }

    @Override
    public void endRowSet(IRowSetMetadata rowSet) {
        out.leave();
        impl.endRowSet(rowSet);
    }

    @Override
    public void column(IColumnMetadata column) {
        impl.column(column);
    }

    @Override
    public void primaryKey(ITableMetadata table, TableKey key) {
        impl.primaryKey(table, key);
    }

    @Override
    public void foreignKey(ITableMetadata table, CrossReference crossRef) {
        impl.foreignKey(table, crossRef);
    }

    @Override
    public void row(IRow row) {
        impl.row(row);
    }

}
