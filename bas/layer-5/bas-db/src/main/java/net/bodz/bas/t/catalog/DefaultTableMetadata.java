package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.map.ListMap;

public class DefaultTableMetadata
        extends DefaultRowSetMetadata
        implements
            ITableMetadata {

    QualifiedTableName qName = new QualifiedTableName();
    QualifiedTableName defaultName = new QualifiedTableName();

    String label;
    String description;
    TableKey primaryKey;
    Map<String, CrossReference> foreignKeys = new LinkedHashMap<>();

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
    public TableKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(TableKey primaryKey) {
        if (primaryKey == null)
            throw new NullPointerException("primaryKey");
        this.primaryKey = primaryKey;
        for (IColumnMetadata column : columns) {
            String columnName = column.getName();
            boolean isPrimaryKey = primaryKey.contains(columnName);
            if (isPrimaryKey != column.isPrimaryKey()) {
                DefaultColumnMetadata m = (DefaultColumnMetadata) column;
                m.setPrimaryKey(isPrimaryKey);
            }
        }
    }

    public void parsePrimaryKey(String keyStr) {
        String[] cols;
        if (Nullables.isEmpty(keyStr))
            cols = new String[0];
        else {
            cols = keyStr.split(",");
            for (int i = 0; i < cols.length; i++)
                cols[i] = cols[i].trim();
        }
        primaryKey = new TableKey(getQName(), cols);
    }

    public void updatePrimaryKey() {
        List<String> cols = new ArrayList<>();
        for (IColumnMetadata column : columns) {
            if (column.isPrimaryKey())
                cols.add(column.getName());
        }
        String[] cv = cols.toArray(new String[0]);
        primaryKey = new TableKey(getQName(), cv);
    }

    @Override
    public Map<String, CrossReference> getForeignKeys() {
        return foreignKeys;
    }

    @Override
    public CrossReference getForeignKey(String constraintName) {
        return foreignKeys.get(constraintName);
    }

    public void addForeignKey(CrossReference foreignKey) {
        foreignKeys.put(foreignKey.constraintName, foreignKey);
    }

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

        JsonObject pk = o.getJsonObject(K_PRIMARY_KEY);
        if (pk != null) {
            primaryKey = new TableKey();
            primaryKey.readObject(pk);
        } else {
            // HINT: primary key can also be calculated from columns.
            updatePrimaryKey();
        }

        JsonArray fkv = o.getJsonArray(K_FOREIGN_KEYS);
        if (fkv != null) {
            int nfk = fkv.length();
            for (int i = 0; i < nfk; i++) {
                JsonObject fk = fkv.getJsonObject(i);
                CrossReference ref = new CrossReference();
                ref.readObject(fk);
                foreignKeys.put(ref.getConstraintName(), ref);
            }
        }
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

        IElement x_pk = x_table.selectByTag(K_PRIMARY_KEY).getFirst();
        if (x_pk != null) {
            primaryKey = new TableKey();
            primaryKey.readObject(x_pk);
        } else {
            updatePrimaryKey();
        }

        IElement x_foreignKeys = x_table.selectByTag(K_FOREIGN_KEYS).getFirst();
        if (x_foreignKeys != null) {
            foreignKeys.clear();
            for (IElement x_fk : x_foreignKeys.children()) {
                CrossReference ref = new CrossReference();
                ref.readObject(x_fk);
                foreignKeys.put(ref.getConstraintName(), ref);
            }
        }
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
        qName.catalogName = rsmd.getCatalogName(columnOfThisTable);
        qName.schemaName = rsmd.getSchemaName(columnOfThisTable);
        qName.tableName = rsmd.getTableName(columnOfThisTable);

//        QualifiedTableName parent = qName;
//        QualifiedTableName foreign = parent;
//        cn.getMetaData().getCrossReference(//
//                parent.catalogName, parent.schemaName, parent.tableName, //
//                foreign.catalogName, foreign.schemaName, foreign.tableName);
    }

    class MetaDataHandler
            implements
                IJDBCMetaDataHandler {

        @Override
        public void schema(ResultSet rs)
                throws SQLException {
        }

        @Override
        public void table(ResultSet rs)
                throws SQLException {
            qName.catalogName = rs.getString("table_cat");
            qName.schemaName = rs.getString("table_schem");
            qName.tableName = rs.getString("table_name");
            // String table_type = rs.getString("table_type");
            description = rs.getString("remarks");
        }

        @Override
        public void column(ResultSet rs)
                throws SQLException {
            DefaultColumnMetadata column = new DefaultColumnMetadata(DefaultTableMetadata.this);
            column.readObject(rs);
            addColumn(column);
        }

        @Override
        public void primaryKey(ITableMetadata table, TableKey primaryKey)
                throws SQLException {
            setPrimaryKey(primaryKey);
        }

        @Override
        public void crossReference(ITableMetadata table, CrossReference foreignKey)
                throws SQLException {
            addForeignKey(foreignKey);
        }

    }

    @Override
    public MetaDataHandler getJDBCMetaDataHandler() {
        return new MetaDataHandler();
    }

    public void loadFromJDBC(Connection connection, boolean loadKeys)
            throws SQLException {
        DatabaseMetaData dmd = connection.getMetaData();
        MetaDataHandler handler = new MetaDataHandler();
        ResultSet rs;

        // Parse from table's metadata
        rs = dmd.getTables(qName.catalogName, qName.schemaName, qName.tableName, null);
        while (rs.next()) {
            handler.table(rs);
            break;
        }
        rs.close();

        // Parse from columns' metadata
        rs = dmd.getColumns(qName.catalogName, qName.schemaName, qName.tableName, null);
        while (rs.next())
            handler.column(rs);
        rs.close();

        // Find out primary key
        if (loadKeys) {
            rs = dmd.getPrimaryKeys(qName.catalogName, qName.schemaName, qName.tableName);
            Map<QualifiedTableName, TableKey> pkmap = TableKey.convertFromJDBC(rs);
            for (TableKey pk : pkmap.values())
                handler.primaryKey(this, pk);
            rs.close();

            rs = dmd.getCrossReference(null, null, null, //
                    qName.catalogName, qName.schemaName, qName.tableName);
            ListMap<QualifiedTableName, CrossReference> fkMap = CrossReference.convertToParentMap(rs);
            assert fkMap.size() == 1;
            List<CrossReference> parent0 = fkMap.values().iterator().next();
            for (CrossReference fk : parent0)
                handler.crossReference(this, fk);
            rs.close();
        }
    }

    @Override
    public String toString() {
        return "table " + qName.getCompactName(defaultName) + "(" + getColumnNames() + ")";
    }

}
