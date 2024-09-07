package net.bodz.bas.t.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class DefaultViewMetadata
        extends DefaultTableMetadata
        implements
            IViewMetadata {

    Map<TableOid, MutableTableUsage> tableUsages = new LinkedHashMap<>();

    public DefaultViewMetadata() {
        super();
    }

    public DefaultViewMetadata(ISchemaMetadata parent) {
        super(parent);
    }

    @Override
    public Collection<? extends ITableUsage> getTableUsages() {
        return tableUsages.values();
    }

    @Override
    public void wireUp() {
        super.wireUp();

        ISchemaMetadata schema = getParent();
        if (schema == null)
            return;

        ICatalogMetadata catalog = schema.getParent();
        if (catalog == null)
            return;

        if (primaryKey == null) {
            TableKey primaryKey = findPrimaryKeyFromForeignTables(catalog);

            // FIX FOR VIEW
            if (primaryKey == null) {
                IColumnMetadata idColumn = getColumn("id");
                if (idColumn != null) {
                    primaryKey = new TableKey(getId());
                    primaryKey.columnNames = new String[] { idColumn.getName() };
                }
            }

            this.primaryKey = primaryKey;
        }
    }

    TableKey findPrimaryKeyFromForeignTables(ICatalogMetadata catalog) {
        TableKey primaryKey = null;
        for (ITableUsage usage : tableUsages.values()) {
            ITableMetadata source = catalog.getTable(usage.getFromTableId());
            if (source == null) {
                // source = catalog.getView(usage.getTableId());
                // throw new NoSuchKeyException("No table: " + usage.getTableId());
                // TODO checkout views
                continue;
            }

            IColumnMetadata[] pkv = source.getPrimaryKeyColumns();
            int nSourcePKColumnCount = pkv.length;

            // ignore empty usage
            if (nSourcePKColumnCount == 0)
                continue;

            int nMatchedColumnCount = 0;
            for (IColumnMetadata pkColumn : pkv) {
                String pkColName = pkColumn.getName();
                IColumnMetadata viewColumn = getColumn(pkColName);
                if (viewColumn == null) {
                    viewColumn = getColumn(source.getId().getTableName() + "__" + pkColName);
                    if (viewColumn == null)
                        continue;
                }
                nMatchedColumnCount++;
            }

            if (nMatchedColumnCount == nSourcePKColumnCount) {
                primaryKey = new TableKey(getId());
                String[] columnNames = new String[nSourcePKColumnCount];
                for (int i = 0; i < nSourcePKColumnCount; i++)
                    columnNames[i] = pkv[i].getName();
                primaryKey.columnNames = columnNames;
                return primaryKey;
            }
        } // for usage
        return primaryKey;
    }

    class ViewHandler
            extends TableHandler {

        @Override
        public void viewColumnUsage(ResultSet rs)
                throws SQLException {
            String catalog = rs.getString("table_catalog");
            String schema = rs.getString("table_schema");
            String name = rs.getString("table_name");
            TableOid oid = new TableOid(catalog, schema, name);
            MutableTableUsage usage = tableUsages.get(oid);
            if (usage == null) {
                usage = new MutableTableUsage(oid);
                tableUsages.put(oid, usage);
            }
            String fromColumn = rs.getString("column_name");

            DefaultViewMetadata view = DefaultViewMetadata.this;
            IColumnMetadata viewColumn = view.getColumn(fromColumn);
            if (viewColumn != null) {
                usage.fromColumns.add(fromColumn);
                usage.viewColumns.add(viewColumn.getName());
            }
        }

    }

    @Override
    protected IJDBCMetaDataHandler createJDBCMetaDataHandler() {
        return new ViewHandler();
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);

        JsonArray o_usages = o.getJsonArray(K_TABLE_UAGES);
        if (o_usages != null) {
            int n = o_usages.length();
            for (int i = 0; i < n; i++) {
                JsonObject o_usage = o_usages.getJsonObject(i);
                MutableTableUsage usage = new MutableTableUsage();
                usage.jsonIn(o_usage, opts);
                tableUsages.put(usage.getFromTableId(), usage);
            }
        }
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
        super.readObject(x_table);

        IElement x_usages = x_table.selectByTag(K_TABLE_UAGES).getFirst();
        if (x_usages != null) {
            tableUsages.clear();
            for (IElement x_usage : x_usages.children()) {
                MutableTableUsage usage = new MutableTableUsage();
                usage.readObject(x_usage);
                tableUsages.put(usage.getFromTableId(), usage);
            }
        }
    }

    @Override
    protected void _acceptMore(ICatalogVisitor visitor) {
        // visitor.tableUsages(this, getTableUsages());
    }

}
