package net.bodz.bas.t.table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;

public class DefaultTableMetadata
        extends DefaultRowSetMetadata
        implements
            ITableMetadata {

    private static final long serialVersionUID = 1L;

    boolean defaultCatalog;
    boolean defaultSchema;

    String label;
    String description;
    String[] primaryKey;

    public DefaultTableMetadata() {
    }

    public DefaultTableMetadata(String catalogName, String schemaName, String tableName) {
        this.catalogName = catalogName;
        this.schemaName = schemaName;
        this.tableName = tableName;
    }

    public DefaultTableMetadata(String qualifiedName) {
        setQualifiedName(qualifiedName);
    }

    public static DefaultTableMetadata fromMetaData(Connection connection, String catalogName, String schemaName,
            String tableName)
            throws SQLException {
        DefaultTableMetadata table = new DefaultTableMetadata(catalogName, schemaName, tableName);
        table.readObject(connection);
        return table;
    }

    public static DefaultTableMetadata fromMetaData(Connection connection, String qualifiedName)
            throws SQLException {
        DefaultTableMetadata table = new DefaultTableMetadata(qualifiedName);
        table.readObject(connection);
        return table;
    }

    public boolean isDefaultCatalog() {
        return defaultCatalog;
    }

    public void setDefaultCatalog(boolean defaultCatalog) {
        this.defaultCatalog = defaultCatalog;
    }

    public boolean isDefaultSchema() {
        return defaultSchema;
    }

    public void setDefaultSchema(boolean defaultSchema) {
        this.defaultSchema = defaultSchema;
    }

    @Override
    public String getName() {
        return tableName;
    }

    public void setName(String name) {
        this.tableName = name;
    }

    @Override
    public String getNecessaryQualifiedName() {
        StringBuilder sb = new StringBuilder();
        if (catalogName != null && !defaultCatalog) {
            sb.append(catalogName);
            sb.append('.');
        }
        if (schemaName != null && !defaultSchema) {
            sb.append(schemaName);
            sb.append('.');
        }
        sb.append(tableName);
        return sb.toString();
    }

    @Override
    public String[] getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String[] primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setPrimaryKey(String s) {
        if (Nullables.isEmpty(s))
            primaryKey = new String[0];
        else {
            primaryKey = s.split(",");
            for (int i = 0; i < primaryKey.length; i++)
                primaryKey[i] = primaryKey[i].trim();
        }
    }

    @Override
    public IColumnMetadata[] getPrimaryKeyColumns() {
        String[] pk = getPrimaryKey();
        IColumnMetadata[] columns = new IColumnMetadata[pk.length];
        for (int i = 0; i < pk.length; i++) {
            columns[i] = getColumn(pk[i]);
        }
        return columns;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);

        tableName = o.getString(K_NAME);

        String s = o.getString(K_PRIMARY_KEY);
        setPrimaryKey(s);
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
        super.readObject(x_table);

        tableName = x_table.getAttribute("name");
        String s = x_table.getString(K_PRIMARY_KEY);
        setPrimaryKey(s);
    }

    @Override
    public void readObject(ResultSetMetaData jdbcMetadata)
            throws SQLException {
        super.readObject(jdbcMetadata);
        // TODO
    }

    public void readObject(Connection connection)
            throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs;

        // Parse from table's metadata
        rs = metaData.getTables(catalogName, schemaName, tableName, null);
        while (rs.next()) {
            catalogName = rs.getString("table_cat");
            schemaName = rs.getString("table_schem");
            tableName = rs.getString("table_name");
            // String table_type = rs.getString("table_type");
            description = rs.getString("remarks");
            break;
        }
        rs.close();

        // Parse from columns' metadata
        rs = metaData.getColumns(catalogName, schemaName, tableName, null);
        while (rs.next()) {
            DefaultColumnMetadata column = new DefaultColumnMetadata();
            column.readObject(rs);
            addColumn(column);
        }
        rs.close();

        // Parse from empty-query.
        // Statement statement = connection.createStatement();
        // rs = statement.executeQuery(//
        // "select * from " + escape(tableQName) + " where 1=2");
        // table.readObject(rs.getMetaData());
        // rs.close();

        // Find out primary key
        rs = metaData.getPrimaryKeys(catalogName, schemaName, tableName);
        List<String> pkColumnNames = new ArrayList<>();
        while (rs.next()) {
            String pkColumnName = rs.getString("COLUMN_NAME");
            pkColumnNames.add(pkColumnName);

            DefaultColumnMetadata column = (DefaultColumnMetadata) getColumn(pkColumnName);
            column.setPrimaryKey(true);
        }
        String[] primaryKey = pkColumnNames.toArray(new String[0]);
        setPrimaryKey(primaryKey);
    }

    @Override
    public String toString() {
        return tableName + "(" + getColumnNames() + ")";
    }

}
