package net.bodz.bas.t.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class DefaultViewMetadata
        extends DefaultTableViewMetadata
        implements
            IViewMetadata {

    TableKey primaryKey;
    Map<TableId, MutableTableUsage> tableUsages = new LinkedHashMap<>();

    public DefaultViewMetadata() {
        super();
    }

    public DefaultViewMetadata(ISchemaMetadata parent) {
        super(parent);
    }

    @Override
    public TableKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(TableKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public Collection<? extends ITableUsage> getTableUsages() {
        return tableUsages.values();
    }

    @Override
    public void wireUp() {
        ISchemaMetadata schema = getParent();
        if (schema == null)
            return;
        ICatalogMetadata catalog = schema.getParent();
        if (catalog == null)
            return;

        TableKey primaryKey = null;
        for (ITableUsage usage : tableUsages.values()) {
            ITableMetadata usedTable = catalog.getTable(usage.getTableId());
            if (usedTable == null)
                throw new NoSuchKeyException("No table: " + usage.getTableId());

            IColumnMetadata[] pkv = usedTable.getPrimaryKeyColumns();
            int n = pkv.length;

            // ignore empty usage
            if (n == 0)
                continue;

            int nExists = 0;
            for (IColumnMetadata pkColumn : pkv) {
                String pkColName = pkColumn.getName();
                IColumnMetadata viewColumn = getColumn(pkColName);
                if (viewColumn == null) {
                    viewColumn = getColumn(usedTable.getId().getTableName() + "__" + pkColName);
                    if (viewColumn == null)
                        continue;
                }
                nExists++;
            }

            if (nExists == n) {
                primaryKey = new TableKey(getId());
                String[] columnNames = new String[n];
                for (int i = 0; i < n; i++)
                    columnNames[i] = pkv[i].getName();
                primaryKey.columnNames = columnNames;
                break;
            }
        } // for usage

        if (primaryKey == null) {
            IColumnMetadata idColumn = getColumn("id");
            if (idColumn != null) {
                primaryKey = new TableKey(getId());
                primaryKey.columnNames = new String[] { idColumn.getName() };
            }
        }
        this.primaryKey = primaryKey;
    }

    class ViewHandler
            extends TableViewHandler {

        @Override
        public void viewColumnUsage(ResultSet rs)
                throws SQLException {
            String catalog = rs.getString("table_catalog");
            String schema = rs.getString("table_schema");
            String name = rs.getString("table_name");
            TableId id = new TableId(catalog, schema, name);
            MutableTableUsage usage = tableUsages.get(id);
            if (usage == null) {
                usage = new MutableTableUsage(id);
                tableUsages.put(id, usage);
            }
            String column = rs.getString("column_name");
            usage.columns.add(column);
        }

    }

    @Override
    protected IJDBCMetaDataHandler createJDBCMetaDataHandler() {
        return new ViewHandler();
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);

        JsonArray o_usages = o.getJsonArray(K_TABLE_UAGES);
        if (o_usages != null) {
            int n = o_usages.length();
            for (int i = 0; i < n; i++) {
                JsonObject o_usage = o_usages.getJsonObject(i);
                MutableTableUsage usage = new MutableTableUsage();
                usage.readObject(o_usage);
                tableUsages.put(usage.getTableId(), usage);
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
                tableUsages.put(usage.getTableId(), usage);
            }
        }
    }

    @Override
    protected void _acceptMore(ICatalogVisitor visitor) {
        // visitor.tableUsages(this, getTableUsages());
    }

}
