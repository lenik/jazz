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

    QualifiedTableName qName = new QualifiedTableName();
    QualifiedTableName defaultName = new QualifiedTableName();

    String label;
    String description;
    String[] primaryKey;

    public DefaultTableMetadata() {
    }

    public DefaultTableMetadata(ISchemaMetadata parent) {
        super(parent);
    }

    @Override
    public QualifiedTableName getQName() {
        return qName;
    }

    public void setQName(QualifiedTableName qName) {
        if (qName == null)
            throw new NullPointerException("qName");
        this.qName = qName;
    }

    @Override
    public QualifiedTableName getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(QualifiedTableName defaultName) {
        if (defaultName == null)
            throw new NullPointerException("defaultName");
        this.defaultName = defaultName;
    }

    @Override
    public ISchemaMetadata getParent() {
        return super.getParent();
    }

    @Override
    public void setParent(ISchemaMetadata parent) {
        ISchemaMetadata schema = parent;
        super.setParent(schema);
    }

    @Override
    public String[] getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String[] primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void parsePrimaryKey(String keyStr) {
        if (Nullables.isEmpty(keyStr))
            primaryKey = new String[0];
        else {
            primaryKey = keyStr.split(",");
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

    static final String K_DEFAULT_NAME = "defaultName";

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        qName.readObject(o);

        JsonObject dn = o.getJsonObject(K_DEFAULT_NAME);
        if (dn != null)
            defaultName.readObject(dn);
        else
            defaultName.clear();

        super.readObject(o);

        String s = o.getString(K_PRIMARY_KEY);
        parsePrimaryKey(s);
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
        qName.readObject(x_table);

        IElement x_defaultName = x_table.selectByTag(K_DEFAULT_NAME).getFirst();
        if (x_defaultName != null)
            defaultName.readObject(x_defaultName);
        else
            defaultName.clear();

        super.readObject(x_table);

        String s = x_table.getString(K_PRIMARY_KEY);
        parsePrimaryKey(s);
    }

    @Override
    public void loadFromRSMD(ResultSetMetaData rsmd)
            throws SQLException {
        super.loadFromRSMD(rsmd);

        int columnOfThisTable = 1;
        qName.catalogName = rsmd.getCatalogName(columnOfThisTable);
        qName.schemaName = rsmd.getSchemaName(columnOfThisTable);
        qName.tableName = rsmd.getTableName(columnOfThisTable);

//        QualifiedTableName parent = qName;
//        QualifiedTableName foreign = parent;
//        cn.getMetaData().getCrossReference(//
//                parent.catalogName, parent.schemaName, parent.tableName, //
//                foreign.catalogName, foreign.schemaName, foreign.tableName);
    }

    public void loadFromJDBC(Connection connection)
            throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs;

        // Parse from table's metadata
        rs = metaData.getTables(qName.catalogName, qName.schemaName, qName.tableName, null);
        while (rs.next()) {
            qName.catalogName = rs.getString("table_cat");
            qName.schemaName = rs.getString("table_schem");
            qName.tableName = rs.getString("table_name");
            // String table_type = rs.getString("table_type");
            description = rs.getString("remarks");
            break;
        }
        rs.close();

        // Parse from columns' metadata
        rs = metaData.getColumns(qName.catalogName, qName.schemaName, qName.tableName, null);
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
        rs = metaData.getPrimaryKeys(qName.catalogName, qName.schemaName, qName.tableName);
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
        return "table " + qName.getCompactName(defaultName) + "(" + getColumnNames() + ")";
    }

}
