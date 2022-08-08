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
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;

public abstract class DefaultTableViewMetadata
        extends DefaultRowSetMetadata
        implements
            ITableViewMetadata {

    TableId id = new TableId();
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
    public TableId getId() {
        return id;
    }

    public void setId(TableId id) {
        if (id == null)
            throw new NullPointerException("id");
        this.id = id;
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

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        id.readObject(o);
        tableType = o.getEnum(TableType.class, K_TABLE_TYPE, getDefaultTableType());
        super.readObject(o);
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
        id.readObject(x_table);
        tableType = x_table.getAttributeVar(K_TABLE_TYPE)//
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
        id.catalogName = rsmd.getCatalogName(columnOfThisTable);
        id.schemaName = rsmd.getSchemaName(columnOfThisTable);
        id.tableName = rsmd.getTableName(columnOfThisTable);
    }

    class TableViewHandler
            implements
                IJDBCMetaDataHandler {

        @Override
        public ITableViewMetadata tableView(ResultSet rs)
                throws SQLException {
            id.catalogName = rs.getString("table_cat");
            id.schemaName = rs.getString("table_schem");
            id.tableName = rs.getString("table_name");
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

    public void loadFromJDBC(Connection connection)
            throws SQLException {
        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs;

        // Parse from table's metadata
        rs = dmd.getTables(id.catalogName, id.schemaName, id.tableName, null);
        int count = 0;
        while (rs.next()) {
            if (count == 0)
                metaDataHandler.tableView(rs);
            count++;
        }
        rs.close();
        if (count == 0)
            throw new NoSuchKeyException("undefined view " + id);

        // Parse from columns' metadata
        rs = dmd.getColumns(id.catalogName, id.schemaName, id.tableName, null);
        while (rs.next())
            metaDataHandler.column(rs);
        rs.close();
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
        SchemaId ns = getParent().getId();
        return tableType + " " + id.getCompactName(ns) + "(" + getColumnNames() + ")";
    }

}
