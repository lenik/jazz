package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.map.ListMap;

public class DefaultSchemaMetadata
        implements
            IMutableSchemaMetadata {

    static final Logger logger = LoggerFactory.getLogger(DefaultSchemaMetadata.class);

    SchemaId id = new SchemaId();
    SchemaId defaultName = new SchemaId();

    String label;
    String description;

    ICatalogMetadata parent;

    Map<String, ITableMetadata> tables = new LinkedHashMap<>();
    Boolean convertToUpperCase;
    Map<String, String> canonicalNames = new HashMap<>();

    public DefaultSchemaMetadata() {
    }

    public DefaultSchemaMetadata(ICatalogMetadata parent) {
        this.parent = parent;
    }

    @Override
    public SchemaId getId() {
        return id;
    }

    public void setId(SchemaId id) {
        this.id = id;
    }

    @Override
    public SchemaId getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(SchemaId defaultName) {
        if (defaultName == null)
            throw new NullPointerException("defaultName");
        this.defaultName = defaultName;
    }

    @Override
    public boolean isValidTableId(TableId tableName) {
        if (tableName == null)
            throw new NullPointerException("tableName");
        if (this.id == null)
            return true;
        return this.id.contains(tableName);
    }

    @Override
    public boolean isValidIdOf(String catalogName) {
        if (NamePattern.matches(catalogName, this.id.catalogName))
            return true;
        return false;
    }

    @Override
    public ICatalogMetadata getParent() {
        return parent;
    }

    @Override
    public void setParent(ICatalogMetadata parent) {
        this.parent = parent;
    }

    public Boolean getConvertToUpperCase() {
        return convertToUpperCase;
    }

    public void setConvertToUpperCase(Boolean convertToUpperCase) {
        this.convertToUpperCase = convertToUpperCase;
    }

    @Override
    public String getCanonicalName(String name) {
        if (name == null)
            return null;
        if (convertToUpperCase == null)
            return name;
        String cName = canonicalNames.get(name);
        if (cName != null)
            return cName;
        if (convertToUpperCase)
            return name.toUpperCase();
        else
            return name.toLowerCase();
    }

    @Override
    public Map<String, ITableMetadata> getTables() {
        return tables;
    }

    public DefaultTableMetadata getOrCreateTable(TableId id) {
        checkTableId(id);
        return getOrCreateTable(id.tableName);
    }

    public DefaultTableMetadata autoloadTableFromJDBC(String name, Connection cn) {
        try {
            return loadTableFromJDBC(name, cn);
        } catch (SQLException e) {
            throw new LoadException("Failed to load table " + name + ": " + e.getMessage(), e);
        }
    }

    public synchronized DefaultTableMetadata loadTableFromJDBC(String name, Connection cn)
            throws SQLException {
        DefaultTableMetadata table = (DefaultTableMetadata) tables.get(name);
        if (table == null) {
            table = newTable(name);
            table.loadFromJDBC(cn, true);
            addTable(table);
        }
        return table;
    }

    public synchronized DefaultTableMetadata getOrCreateTable(String name) {
        if (name == null)
            throw new NullPointerException("name");
        DefaultTableMetadata table = (DefaultTableMetadata) tables.get(name);
        if (table == null) {
            table = new DefaultTableMetadata();
            table.getId().assign(id.catalogName, id.schemaName, name);
            table.setParent(this);
            tables.put(name, table);
        }
        return table;
    }

    @Override
    public ITableMetadata getTable(String name) {
        return tables.get(name);
    }

    @Override
    public Iterator<ITableMetadata> iterator() {
        return tables.values().iterator();
    }

    @Override
    public int getTableCount() {
        return tables.size();
    }

    public DefaultTableMetadata newTable(String tableName) {
        DefaultTableMetadata table = new DefaultTableMetadata(this);
        table.getId().assign(id.catalogName, id.schemaName, tableName);
        return table;
    }

    @Override
    public void addTable(ITableMetadata table) {
        if (table == null)
            throw new NullPointerException("table");
        String name = table.getName();
        ITableMetadata existing = tables.get(name);
        if (existing != null)
            throw new DuplicatedKeyException("Table already existed: " + name);
        tables.put(name, table);
    }

    @Override
    public boolean removeTable(ITableMetadata table) {
        return removeTable(table.getName());
    }

    @Override
    public boolean removeTable(String tableName) {
        ITableMetadata table = tables.remove(tableName);
        return table != null;
    }

    @Override
    public TableList findTables(TableId pattern, boolean ignoreCase) {
        if (pattern != null) {
            if (!pattern.toSchemaId().contains(this.getId(), ignoreCase))
                return TableList.empty();
        }
        TableList list = new TableList();
        for (ITableMetadata table : this) {
            if (pattern != null)
                if (!pattern.contains(table.getId(), ignoreCase))
                    continue;
            list.add(table);
        }
        return list;
    }

    String getTableNames() {
        StringBuilder sb = new StringBuilder(tables.size() * 16);
        for (String key : tables.keySet()) {
            if (sb.length() != 0)
                sb.append(", ");
            ITableMetadata table = tables.get(key);
            sb.append(table.getName());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "schema " + getCompactName() + "(" + getTableNames() + ")";
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        getId().readObject(o);

        JsonObject jm = o.getJsonObject(K_TABLES);
        Map<String, ITableMetadata> tables = new LinkedHashMap<>();
        for (String key : jm.keySet()) {
            JsonObject item = jm.getJsonObject(key);
            DefaultTableMetadata table = new DefaultTableMetadata();
            table.readObject(item);
            tables.put(key, table);
        }
        this.tables = tables;
    }

    @Override
    public void readObject(IElement x_metadata)
            throws ParseException, LoaderException {
        IElement x_tables = x_metadata.selectByTag(K_TABLES).first();
        Map<String, ITableMetadata> tables = new LinkedHashMap<>();
        for (IElement x_table : x_tables.children()) {
            assert x_table.getTagName().equals(K_TABLE);
            DefaultTableMetadata table = new DefaultTableMetadata();
            table.readObject(x_table);
            String key = table.getName();
            tables.put(key, table);
        }
        this.tables = tables;
    }

    class MetaDataHandler
            implements
                IJDBCMetaDataHandler {

        @Override
        public void schema(ResultSet rs)
                throws SQLException {
            String catalogName = rs.getString("TABLE_CATALOG");
            String schemaName = rs.getString("TABLE_SCHEM");
            id.assign(catalogName, schemaName); // correct char case.
        }

        @Override
        public void table(ResultSet rs)
                throws SQLException {
            DefaultTableMetadata table = new DefaultTableMetadata(DefaultSchemaMetadata.this);
            table.getJDBCMetaDataHandler().table(rs);
            addTable(table);
        }

        @Override
        public void column(ResultSet rs)
                throws SQLException {
            TableId id = new TableId();
            id.readFromJDBC(rs);
            ITableMetadata table = getTable(id.getTableName());
            if (table == null)
                throw new UnexpectedException("Detected new table when scanning: " + id);
            table.getJDBCMetaDataHandler().column(rs);
        }

        @Override
        public void primaryKey(ITableMetadata table, TableKey primaryKey)
                throws SQLException {
            table.getJDBCMetaDataHandler().primaryKey(table, primaryKey);
        }

        @Override
        public void crossReference(ITableMetadata table, CrossReference crossRef)
                throws SQLException {
            table.getJDBCMetaDataHandler().crossReference(table, crossRef);
        }

    }

    @Override
    public MetaDataHandler getJDBCMetaDataHandler() {
        return new MetaDataHandler();
    }

    public void loadFromJDBC(Connection connection, String... types)
            throws SQLException {
        DatabaseMetaData dmd = connection.getMetaData();
        MetaDataHandler handler = new MetaDataHandler();
        ResultSet rs;
        SchemaId id = getId();

        // Parse from schema's metadata
        rs = dmd.getSchemas(id.catalogName, id.schemaName);
        while (rs.next()) {
            handler.schema(rs);
            break;
        }
        rs.close();

        rs = dmd.getTables(id.catalogName, id.schemaName, null, types);
        while (rs.next())
            handler.table(rs);
        rs.close();

        rs = dmd.getColumns(id.catalogName, id.schemaName, null, null);
        while (rs.next())
            handler.column(rs);
        rs.close();

        rs = dmd.getPrimaryKeys(id.catalogName, id.schemaName, null);
        Map<TableId, TableKey> pkmap = TableKey.convertFromJDBC(rs);
        for (TableId tableId : pkmap.keySet()) {
            ITableMetadata table = getTable(tableId);
            TableKey primaryKey = pkmap.get(tableId);
            handler.primaryKey(table, primaryKey);
        }
        rs.close();

        rs = dmd.getCrossReference(null, null, null, //
                id.catalogName, id.schemaName, null);
        ListMap<TableId, CrossReference> foreignMap = CrossReference.convertToForeignMap(rs);
        for (TableId foreignName : foreignMap.keySet()) {
            ITableMetadata foreignTable = getTable(foreignName.getTableName());

            List<CrossReference> crossRefs = foreignMap.get(foreignName);
            for (CrossReference crossRef : crossRefs) {
                handler.crossReference(foreignTable, crossRef);
            }
        }
        rs.close();
    }

}
