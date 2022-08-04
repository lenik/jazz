package net.bodz.bas.t.catalog;

import net.bodz.bas.io.ITreeOut;

public class CatalogDumper
        implements
            ICatalogVisitor {

    ITreeOut out;

    public CatalogDumper(ITreeOut out) {
        this.out = out;
    }

    public ICatalogVisitor indented() {
        return new TreeOutVisitor(out, this);
    }

    @Override
    public boolean beginCatalog(ICatalogMetadata metadata) {
        out.println("catalog: " + metadata.getName());
        return true;
    }

    @Override
    public boolean beginSchema(ISchemaMetadata metadata) {
        out.println("schema: " + metadata.getCompactName());
        return true;
    }

    @Override
    public boolean beginTables(ISchemaMetadata metadata) {
        out.println("tables:");
        return true;
    }

    @Override
    public boolean beginViews(ISchemaMetadata metadata) {
        out.println("---------------------------------------");
        out.println("views:");
        return true;
    }

    @Override
    public boolean beginTableView(ITableViewMetadata table) {
        out.println(table.getTableType() + " " + table.getCompactName());
        return true;
    }

    @Override
    public void endTableView(ITableViewMetadata table) {
        out.println();
    }

    @Override
    public boolean beginColumns(ITableViewMetadata table) {
        out.print("columns: ");
        return true;
    }

    @Override
    public void endColumns(ITableViewMetadata table) {
        out.println();
    }

    @Override
    public void column(IColumnMetadata metadata) {
        out.print(metadata.getName() + ", ");
    }

    @Override
    public void primaryKey(ITableMetadata table, TableKey key) {
        out.println("primary-key: " + key);
    }

    @Override
    public void foreignKey(ITableMetadata table, CrossReference reference) {
        out.println("foreign-key: " + reference);
    }

    @Override
    public boolean beginRowSet(IRowSetMetadata rowSet) {
        out.println("rows: ");
        return true;
    }

    @Override
    public void row(IRow row) {
        out.println("-- " + row);
    }

}
