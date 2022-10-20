package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.map.ListMap;

public class DefaultTableMetadata
        extends DefaultTableViewMetadata
        implements
            ITableMetadata {

    static final Logger logger = LoggerFactory.getLogger(DefaultTableMetadata.class);

    public DefaultTableMetadata() {
    }

    public DefaultTableMetadata(ISchemaMetadata parent) {
        super(parent);
    }

    public void parsePrimaryKey(String keyStr) {
        String[] strFields;
        if (Nullables.isEmpty(keyStr))
            strFields = new String[0];
        else {
            strFields = keyStr.split(",");
            for (int i = 0; i < strFields.length; i++)
                strFields[i] = strFields[i].trim();
        }
        primaryKey = new TableKey(getId(), strFields).normalize(this);
    }

    public void updatePrimaryKey() {
        List<String> cols = new ArrayList<>();
        for (IColumnMetadata column : columns) {
            if (column.isPrimaryKey())
                cols.add(column.getName());
        }
        String[] cv = cols.toArray(new String[0]);
        primaryKey = new TableKey(getId(), cv).normalize(this);
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
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);

        JsonObject pk = o.getJsonObject(K_PRIMARY_KEY);
        if (pk != null) {
            primaryKey = new TableKey();
            primaryKey.jsonIn(pk, opts);
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
                ref.jsonIn(fk, opts);
                foreignKeys.put(ref.getConstraintName(), ref);
            }
        }
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
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

    class TableHandler
            extends TableViewHandler {

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
    protected TableHandler createJDBCMetaDataHandler() {
        return new TableHandler();
    }

    @Override
    public boolean loadFromJDBC(Connection connection, LoadFromJDBCOptions options)
            throws SQLException {
        logger.debug("Load table from JDBC: " + getId());
        if (!super.loadFromJDBC(connection, options))
            return false;

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs;

        // Find out primary key
        if (options.isLoadKeys()) {
            rs = dmd.getPrimaryKeys(oid.catalogName, oid.schemaName, oid.tableName);
            Map<TableOid, TableKey> primaryKeyMap = TableKey.convertFromJDBC(rs);
            for (TableKey pk : primaryKeyMap.values())
                metaDataHandler.primaryKey(this, pk);
            rs.close();

            rs = dmd.getCrossReference(null, null, null, //
                    oid.catalogName, oid.schemaName, oid.tableName);
            ListMap<TableOid, CrossReference> foreignMap = CrossReference.convertToForeignMap(rs);
            assert foreignMap.size() <= 1;
            if (!foreignMap.isEmpty()) {
                List<CrossReference> parentList = foreignMap.values().iterator().next();
                for (CrossReference crossRef : parentList)
                    metaDataHandler.crossReference(this, crossRef);
            }
            rs.close();
        }
        return true;
    }

    @Override
    protected void _acceptMore(ICatalogVisitor visitor) {
        visitor.primaryKey(this, getPrimaryKey());

        for (CrossReference crossRef : getForeignKeys().values())
            visitor.foreignKey(this, crossRef);
    }

}
