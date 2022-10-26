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
    public boolean beginCatalog(ICatalogMetadata catalog) {
        if (impl.beginCatalog(catalog)) {
            out.enter();
            return true;
        }
        return false;
    }

    @Override
    public void endCatalog(ICatalogMetadata catalog) {
        out.leave();
        impl.endCatalog(catalog);
    }

    @Override
    public boolean beginSchema(ISchemaMetadata schema) {
        if (impl.beginSchema(schema)) {
            out.enter();
            return true;
        }
        return false;
    }

    @Override
    public void endSchema(ISchemaMetadata schema) {
        out.leave();
        impl.endSchema(schema);
    }

    @Override
    public boolean beginTables(ISchemaMetadata schema) {
        if (impl.beginTables(schema)) {
            out.enter();
            return true;
        }
        return false;
    }

    @Override
    public void endTables(ISchemaMetadata schema) {
        out.leave();
        impl.endTables(schema);
    }

    @Override
    public boolean beginViews(ISchemaMetadata schema) {
        if (impl.beginViews(schema)) {
            out.enter();
            return true;
        }
        return false;
    }

    @Override
    public void endViews(ISchemaMetadata schema) {
        out.leave();
        impl.endSchema(schema);
    }

    @Override
    public boolean beginTableOrView(ITableMetadata table) {
        if (impl.beginTableOrView(table)) {
            out.enter();
            return true;
        }
        return false;
    }

    @Override
    public void endTableOrView(ITableMetadata table) {
        out.leave();
        impl.endTableOrView(table);
    }

    @Override
    public boolean beginColumns(ITableMetadata table) {
        if (impl.beginColumns(table)) {
            out.enter();
            return true;
        }
        return false;
    }

    @Override
    public void endColumns(ITableMetadata table) {
        out.leave();
        impl.endColumns(table);
    }

    @Override
    public boolean beginRowSet(IRowSetMetadata rowSet) {
        if (impl.beginRowSet(rowSet)) {
            out.enter();
            return true;
        }
        return false;
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
