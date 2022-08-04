package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
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

    Map<String, ITableMetadata> tableMap = new LinkedHashMap<>();
    Map<String, ITableViewMetadata> viewMap = new LinkedHashMap<>();

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
    public Map<String, ITableMetadata> getTableMap() {
        return tableMap;
    }

    @Override
    public Collection<ITableMetadata> getTables() {
        return tableMap.values();
    }

    @Override
    public int getTableCount() {
        return tableMap.size();
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
        DefaultTableMetadata table = (DefaultTableMetadata) tableMap.get(name);
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
        DefaultTableMetadata table = (DefaultTableMetadata) tableMap.get(name);
        if (table == null) {
            table = new DefaultTableMetadata();
            table.getId().assign(id.catalogName, id.schemaName, name);
            table.setParent(this);
            tableMap.put(name, table);
        }
        return table;
    }

    @Override
    public ITableMetadata getTable(String name) {
        return tableMap.get(name);
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
        ITableMetadata existing = tableMap.get(name);
        if (existing != null)
            throw new DuplicatedKeyException("Table is already existed: " + name);
        tableMap.put(name, table);
    }

    @Override
    public boolean removeTable(ITableMetadata table) {
        return removeTable(table.getName());
    }

    @Override
    public boolean removeTable(String tableName) {
        ITableMetadata table = tableMap.remove(tableName);
        return table != null;
    }

    @Override
    public TableList findTables(TableId pattern, boolean ignoreCase) {
        if (pattern != null) {
            if (!pattern.toSchemaId().contains(this.getId(), ignoreCase))
                return TableList.empty();
        }
        TableList list = new TableList();
        for (ITableMetadata table : getTables()) {
            if (pattern != null)
                if (!pattern.contains(table.getId(), ignoreCase))
                    continue;
            list.add(table);
        }
        return list;
    }

    public String getTableNames() {
        StringBuilder sb = new StringBuilder(tableMap.size() * 16);
        for (String key : tableMap.keySet()) {
            if (sb.length() != 0)
                sb.append(", ");
            ITableMetadata table = tableMap.get(key);
            sb.append(table.getName());
        }
        return sb.toString();
    }

    @Override
    public Map<String, ITableViewMetadata> getViewMap() {
        return viewMap;
    }

    @Override
    public Collection<ITableViewMetadata> getViews() {
        return viewMap.values();
    }

    @Override
    public int getViewCount() {
        return viewMap.size();
    }

    public DefaultTableViewMetadata getOrCreateView(TableId id) {
        checkTableId(id);
        return getOrCreateView(id.tableName);
    }

    public DefaultTableViewMetadata autoloadViewFromJDBC(String name, Connection cn) {
        try {
            return loadViewFromJDBC(name, cn);
        } catch (SQLException e) {
            throw new LoadException("Failed to load view " + name + ": " + e.getMessage(), e);
        }
    }

    public synchronized DefaultTableViewMetadata loadViewFromJDBC(String name, Connection cn)
            throws SQLException {
        DefaultTableViewMetadata view = (DefaultTableViewMetadata) viewMap.get(name);
        if (view == null) {
            view = newView(name);
            view.loadFromJDBC(cn);
            addView(view);
        }
        return view;
    }

    public synchronized DefaultTableViewMetadata getOrCreateView(String name) {
        if (name == null)
            throw new NullPointerException("name");
        DefaultTableViewMetadata view = (DefaultTableViewMetadata) viewMap.get(name);
        if (view == null) {
            // view = new DefaultTableViewMetadata(this);
            view = new DefaultTableMetadata(this);
            view.getId().assign(id.catalogName, id.schemaName, name);
            view.setParent(this);
            viewMap.put(name, view);
        }
        return view;
    }

    @Override
    public ITableViewMetadata getView(String name) {
        return viewMap.get(name);
    }

    public DefaultTableViewMetadata newView(String viewName) {
        // DefaultTableViewMetadata view = new DefaultTableViewMetadata(this);
        DefaultTableViewMetadata view = new DefaultTableMetadata(this);
        view.getId().assign(id.catalogName, id.schemaName, viewName);
        return view;
    }

    public void addView(ITableViewMetadata view) {
        if (view == null)
            throw new NullPointerException("view");
        String name = view.getName();
        ITableViewMetadata existing = viewMap.get(name);
        if (existing != null)
            throw new DuplicatedKeyException("View is already existed: " + name);
        viewMap.put(name, view);
    }

    public boolean removeView(ITableViewMetadata view) {
        return removeView(view.getName());
    }

    public boolean removeView(String name) {
        ITableViewMetadata view = viewMap.remove(name);
        return view != null;
    }

    @Override
    public TableViewList findViews(TableId pattern, boolean ignoreCase) {
        if (pattern != null) {
            if (!pattern.toSchemaId().contains(this.getId(), ignoreCase))
                return TableViewList.empty();
        }
        TableViewList list = new TableViewList();
        for (ITableViewMetadata view : viewMap.values()) {
            if (pattern != null)
                if (!pattern.contains(view.getId(), ignoreCase))
                    continue;
            list.add(view);
        }
        return list;
    }

    public String getViewNames() {
        StringBuilder sb = new StringBuilder(viewMap.size() * 16);
        for (String key : viewMap.keySet()) {
            if (sb.length() != 0)
                sb.append(", ");
            ITableViewMetadata view = viewMap.get(key);
            sb.append(view.getName());
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
        this.tableMap = tables;
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
        this.tableMap = tables;
    }

    class SchemaHandler
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

            if (!loadSelector.selectTable(table.getId()))
                return;

            switch (table.getTableType()) {
            case TABLE:
                addTable(table);
                break;

            case VIEW:
                addView(table);
                break;

            default:
            }
        }

        @Override
        public void column(ResultSet rs)
                throws SQLException {
            TableId id = new TableId();
            id.readFromJDBC(rs);
            if (!loadSelector.selectTable(id))
                return;

            ITableMetadata table = getTable(id.getTableName());
            if (table != null) {
                table.getJDBCMetaDataHandler().column(rs);
                return;
            }

            ITableViewMetadata view = getView(id.getTableName());
            if (view != null) {
                view.getJDBCMetaDataHandler().column(rs);
                return;
            }
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

    IJDBCMetaDataHandler metaDataHandler = new SchemaHandler();
    IJDBCLoadSelector loadSelector = IJDBCLoadSelector.ALL;

    @Override
    public IJDBCMetaDataHandler getJDBCMetaDataHandler() {
        return metaDataHandler;
    }

    public void setJDBCMetaDataHandler(IJDBCMetaDataHandler handler) {
        metaDataHandler = handler;
    }

    public IJDBCLoadSelector getJDBCLoadSelector() {
        return loadSelector;
    }

    public void setJDBCLoadSelector(IJDBCLoadSelector loadSelector) {
        this.loadSelector = loadSelector;
    }

    public void loadFromJDBC(Connection connection, String... types)
            throws SQLException {
        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs;
        SchemaId id = getId();

        // Parse from schema's metadata
        rs = dmd.getSchemas(id.catalogName, id.schemaName);
        while (rs.next()) {
            metaDataHandler.schema(rs);
            break;
        }
        rs.close();

        rs = dmd.getTables(id.catalogName, id.schemaName, null, types);
        while (rs.next())
            metaDataHandler.table(rs);
        rs.close();

        // Set<String> typeSet = new HashSet<>(Arrays.asList(types));
        rs = dmd.getColumns(id.catalogName, id.schemaName, null, null);
        while (rs.next())
            metaDataHandler.column(rs);
        rs.close();

        rs = dmd.getPrimaryKeys(id.catalogName, id.schemaName, null);
        Map<TableId, TableKey> pkmap = TableKey.convertFromJDBC(rs);
        for (TableId tableId : pkmap.keySet()) {
            ITableMetadata table = getTable(tableId);
            if (table == null)
                continue;
            TableKey primaryKey = pkmap.get(tableId);
            metaDataHandler.primaryKey(table, primaryKey);
        }
        rs.close();

        rs = dmd.getCrossReference(null, null, null, //
                id.catalogName, id.schemaName, null);
        ListMap<TableId, CrossReference> foreignMap = CrossReference.convertToForeignMap(rs);
        for (TableId foreignName : foreignMap.keySet()) {
            ITableMetadata foreignTable = getTable(foreignName.getTableName());
            if (foreignTable == null)
                continue;
            List<CrossReference> crossRefs = foreignMap.get(foreignName);
            for (CrossReference crossRef : crossRefs) {
                metaDataHandler.crossReference(foreignTable, crossRef);
            }
        }
        rs.close();
    }

}
