package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;

public abstract class DefaultTableViewMetadata
        extends DefaultRowSetMetadata
        implements
            ITableViewMetadata,
            IMutableJavaName {

    TableOid oid = new TableOid();
    String javaName;

    TableType tableType = getDefaultTableType();

    String label;
    String description;
    TableKey primaryKey;
    Map<String, CrossReference> foreignKeys = new LinkedHashMap<>();

    public DefaultTableViewMetadata() {
    }

    public DefaultTableViewMetadata(ISchemaMetadata parent) {
        super(parent);
    }

    @Override
    public TableOid getId() {
        return oid;
    }

    public void setId(TableOid oid) {
        if (oid == null)
            throw new NullPointerException("id");
        this.oid = oid;
    }

    @Override
    public String getJavaName() {
        return javaName;
    }

    @Override
    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    @Override
    public TableType getTableType() {
        return tableType;
    }

    public void setTableType(TableType tableType) {
        this.tableType = tableType;
    }

    protected TableType getDefaultTableType() {
        return TableType.VIEW;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public TableKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(TableKey primaryKey) {
        this.primaryKey = primaryKey;
        if (primaryKey == null)
            return;
        for (IColumnMetadata column : columns) {
            String columnName = column.getName();
            boolean isPrimaryKey = primaryKey.contains(columnName);
            if (isPrimaryKey != column.isPrimaryKey()) {
                DefaultColumnMetadata mutable = (DefaultColumnMetadata) column;
                mutable.setPrimaryKey(isPrimaryKey);
            }
        }
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        oid.jsonIn(o, opts);

        javaName = o.getString(K_JAVA_NAME);

        tableType = o.getEnum(TableType.class, K_TABLE_TYPE, getDefaultTableType());
        super.jsonIn(o, opts);
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
        oid.readObject(x_table);

        javaName = x_table.a(K_JAVA_NAME).getString();

        tableType = x_table.a(K_TABLE_TYPE)//
                .getEnum(TableType.class, TableType.VIEW);
        super.readObject(x_table);
    }

    public void loadFromRSMD(Connection connection)
            throws SQLException {
        // Parse from empty-query.
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(//
                "select * from " + getCompactName() + " where 1=2");
        loadFromRSMD(rs.getMetaData());
        rs.close();
    }

    @Override
    public void loadFromRSMD(ResultSetMetaData rsmd)
            throws SQLException {
        super.loadFromRSMD(rsmd);

        int columnOfThisTable = 1;
        oid.catalogName = rsmd.getCatalogName(columnOfThisTable);
        oid.schemaName = rsmd.getSchemaName(columnOfThisTable);
        oid.tableName = rsmd.getTableName(columnOfThisTable);
    }

    class TableViewHandler
            implements
                IJDBCMetaDataHandler {

        @Override
        public ITableViewMetadata tableView(ResultSet rs)
                throws SQLException {
            oid.catalogName = rs.getString("table_cat");
            oid.schemaName = rs.getString("table_schem");
            oid.tableName = rs.getString("table_name");
            tableType = TableType.parseJDBC(rs, getDefaultTableType());
            description = rs.getString("remarks");
            return DefaultTableViewMetadata.this;
        }

        @Override
        public IColumnMetadata column(ResultSet rs)
                throws SQLException {
            DefaultColumnMetadata column = new DefaultColumnMetadata(DefaultTableViewMetadata.this);
            column.readObject(rs);
            addColumn(column);
            return column;
        }

    }

    IJDBCMetaDataHandler metaDataHandler = createJDBCMetaDataHandler();

    protected IJDBCMetaDataHandler createJDBCMetaDataHandler() {
        return new TableViewHandler();
    }

    @Override
    public IJDBCMetaDataHandler getJDBCMetaDataHandler() {
        return metaDataHandler;
    }

    public void setJDBCMetaDataHandler(IJDBCMetaDataHandler handler) {
        metaDataHandler = handler;
    }

    /**
     * @return <code>false</code> if table isn't found.
     */
    public boolean loadFromJDBC(Connection connection, LoadFromJDBCOptions options)
            throws SQLException {
        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs;

        // Parse from table's metadata
        rs = dmd.getTables(oid.catalogName, oid.schemaName, oid.tableName, null);
        int count = 0;
        while (rs.next()) {
            if (count == 0)
                metaDataHandler.tableView(rs);
            count++;
        }
        rs.close();
        if (count == 0)
            return false;

        // Parse from columns' metadata
        rs = dmd.getColumns(oid.catalogName, oid.schemaName, oid.tableName, null);
        while (rs.next())
            metaDataHandler.column(rs);
        rs.close();

        return true;
    }

    @Override
    public void accept(ICatalogVisitor visitor) {
        visitor.beginTableView(this);

        visitor.beginColumns(this);
        int n = getColumnCount();
        for (int i = 0; i < n; i++)
            getColumn(i).accept(visitor);
        visitor.endColumns(this);

        _acceptMore(visitor);

        visitor.endTableView(this);
    }

    protected void _acceptMore(ICatalogVisitor visitor) {
    }

    @Override
    public String toString() {
        SchemaOid ns = getParent().getId();
        return tableType + " " + oid.getCompactName(ns) + "(" + getColumnNames() + ")";
    }

}
