package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
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
import net.bodz.bas.fmt.json.JsonFormOptions;
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
    Map<String, IViewMetadata> viewMap = new LinkedHashMap<>();

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

    @Override
    public DefaultTableMetadata autoLoadTableFromJDBC(TableId id, Connection autoLoadConnection,
            LoadFromJDBCOptions options) {
        return autoLoadTableFromJDBC(id.getTableName(), autoLoadConnection, options);
    }

    public DefaultTableMetadata autoLoadTableFromJDBC(String name, Connection cn, LoadFromJDBCOptions options) {
        try {
            return loadTableFromJDBC(name, cn, options);
        } catch (SQLException e) {
            throw new LoadException("Failed to load table " + name + ": " + e.getMessage(), e);
        }
    }

    public synchronized DefaultTableMetadata loadTableFromJDBC(String name, Connection cn, LoadFromJDBCOptions options)
            throws SQLException {
        DefaultTableMetadata table = (DefaultTableMetadata) tableMap.get(name);
        if (table == null) {
            table = newTable(name);
            options.enterNew();
            try {
                options.setLoadKeys(true);
                table.loadFromJDBC(cn, options);
            } finally {
                options.leave();
            }
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
    public Map<String, IViewMetadata> getViewMap() {
        return viewMap;
    }

    @Override
    public Collection<IViewMetadata> getViews() {
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

    public DefaultViewMetadata autoLoadViewFromJDBC(String name, Connection cn, LoadFromJDBCOptions options) {
        try {
            return loadViewFromJDBC(name, cn, options);
        } catch (SQLException e) {
            throw new LoadException("Failed to load view " + name + ": " + e.getMessage(), e);
        }
    }

    public synchronized DefaultViewMetadata loadViewFromJDBC(String name, Connection cn, LoadFromJDBCOptions options)
            throws SQLException {
        DefaultViewMetadata view = (DefaultViewMetadata) viewMap.get(name);
        if (view == null) {
            view = newView(name);
            view.loadFromJDBC(cn, options);
            addView(view);
        }
        return view;
    }

    public synchronized DefaultViewMetadata getOrCreateView(String name) {
        if (name == null)
            throw new NullPointerException("name");
        DefaultViewMetadata view = (DefaultViewMetadata) viewMap.get(name);
        if (view == null) {
            // view = new DefaultTableViewMetadata(this);
            view = new DefaultViewMetadata(this);
            view.getId().assign(id.catalogName, id.schemaName, name);
            view.setParent(this);
            viewMap.put(name, view);
        }
        return view;
    }

    @Override
    public IViewMetadata getView(String name) {
        return viewMap.get(name);
    }

    public DefaultViewMetadata newView(String viewName) {
        // DefaultTableViewMetadata view = new DefaultTableViewMetadata(this);
        DefaultViewMetadata view = new DefaultViewMetadata(this);
        view.getId().assign(id.catalogName, id.schemaName, viewName);
        return view;
    }

    public void addView(IViewMetadata view) {
        if (view == null)
            throw new NullPointerException("view");
        String name = view.getName();
        IViewMetadata existing = viewMap.get(name);
        if (existing != null)
            throw new DuplicatedKeyException("View is already existed: " + name);
        viewMap.put(name, view);
    }

    public boolean removeView(IViewMetadata view) {
        return removeView(view.getName());
    }

    public boolean removeView(String name) {
        IViewMetadata view = viewMap.remove(name);
        return view != null;
    }

    @Override
    public TableViewList findViews(TableId pattern, boolean ignoreCase) {
        if (pattern != null) {
            if (!pattern.toSchemaId().contains(this.getId(), ignoreCase))
                return TableViewList.empty();
        }
        TableViewList list = new TableViewList();
        for (IViewMetadata view : viewMap.values()) {
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
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        getId().jsonIn(o, opts);

        JsonObject jm = o.getJsonObject(K_TABLES);
        Map<String, ITableMetadata> tables = new LinkedHashMap<>();
        for (String key : jm.keySet()) {
            JsonObject item = jm.getJsonObject(key);
            DefaultTableMetadata table = new DefaultTableMetadata();
            table.jsonIn(item, opts);
            tables.put(key, table);
        }
        this.tableMap = tables;

        jm = o.getJsonObject(K_VIEWS);
        Map<String, IViewMetadata> views = new LinkedHashMap<>();
        for (String key : jm.keySet()) {
            JsonObject item = jm.getJsonObject(key);
            DefaultViewMetadata table = new DefaultViewMetadata();
            table.jsonIn(item, opts);
            views.put(key, table);
        }
        this.viewMap = views;
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

        IElement x_views = x_metadata.selectByTag(K_VIEWS).first();
        Map<String, IViewMetadata> views = new LinkedHashMap<>();
        for (IElement x_view : x_views.children()) {
            assert x_view.getTagName().equals(K_VIEW);
            DefaultViewMetadata view = new DefaultViewMetadata();
            view.readObject(x_view);
            String key = view.getName();
            views.put(key, view);
        }
        this.viewMap = views;
    }

    class SchemaHandler
            implements
                IJDBCMetaDataHandler {

        @Override
        public ISchemaMetadata schema(ResultSet rs)
                throws SQLException {
            String catalogName = rs.getString("TABLE_CATALOG");
            String schemaName = rs.getString("TABLE_SCHEM");
            id.assign(catalogName, schemaName); // correct char case.
            return DefaultSchemaMetadata.this;
        }

        @Override
        public ITableViewMetadata tableView(ResultSet rs)
                throws SQLException {
            TableId id = new TableId();
            id.readFromJDBC(rs);

            TableType type = TableType.parseJDBC(rs);
            if (!loadSelector.selectTable(id, type))
                return null;

            switch (type) {
            case TABLE:
                DefaultTableMetadata table = new DefaultTableMetadata(DefaultSchemaMetadata.this);
                table.getJDBCMetaDataHandler().tableView(rs);
                addTable(table);
                return table;

            case VIEW:
                DefaultViewMetadata view = new DefaultViewMetadata(DefaultSchemaMetadata.this);
                view.getJDBCMetaDataHandler().tableView(rs);
                addView(view);
                return view;

            default:
                return null;
            }
        }

        @Override
        public IColumnMetadata column(ResultSet rs)
                throws SQLException {
            TableId id = new TableId();
            id.readFromJDBC(rs);
            // if (!loadSelector.selectTable(id)) return null;

            ITableMetadata table = getTable(id.getTableName());
            if (table != null) {
                return table.getJDBCMetaDataHandler().column(rs);
            }

            IViewMetadata view = getView(id.getTableName());
            if (view != null) {
                return view.getJDBCMetaDataHandler().column(rs);
            }

            return null;
        }

        @Override
        public void viewColumnUsage(ResultSet rs)
                throws SQLException {
            TableId viewId = new TableId();
            viewId.catalogName = rs.getString("view_catalog");
            viewId.schemaName = rs.getString("view_schema");
            viewId.tableName = rs.getString("view_name");
            if (!loadSelector.selectTable(viewId, TableType.VIEW))
                return;

            IViewMetadata view = getView(viewId.getTableName());
            if (view == null)
                return;

            view.getJDBCMetaDataHandler().viewColumnUsage(rs);
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

    IJDBCLoadSelector loadSelector = IJDBCLoadSelector.ALL;

    @Override
    public IJDBCMetaDataHandler getJDBCMetaDataHandler() {
        return new SchemaHandler();
    }

    public IJDBCLoadSelector getJDBCLoadSelector() {
        return loadSelector;
    }

    public void setJDBCLoadSelector(IJDBCLoadSelector loadSelector) {
        this.loadSelector = loadSelector;
    }

    public void loadFromJDBC(Connection connection, String... types)
            throws SQLException {
        if (types != null && types.length == 0)
            types = null;

        DatabaseMetaData dmd = connection.getMetaData();
        IJDBCMetaDataHandler metaDataHandler = getJDBCMetaDataHandler();
        ResultSet rs;

        // Parse from schema's metadata
        rs = dmd.getSchemas(id.catalogName, id.schemaName);
        while (rs.next()) {
            metaDataHandler.schema(rs);
            break;
        }
        rs.close();

        rs = dmd.getTables(id.catalogName, id.schemaName, null, types);
        while (rs.next())
            metaDataHandler.tableView(rs);
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

        String sql = "select * from information_schema.view_column_usage where 1=1";
        {
            if (id.catalogName != null)
                sql += "and view_catalog = ?";
            if (id.schemaName != null)
                sql += " and view_schema = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            int psIndex = 0;
            if (id.catalogName != null)
                ps.setString(++psIndex, id.catalogName);
            if (id.schemaName != null)
                ps.setString(++psIndex, id.schemaName);
            rs = ps.executeQuery();
        }
        while (rs.next())
            metaDataHandler.viewColumnUsage(rs);
        rs.close();

        // creation-order
        for (ITableMetadata table : getTables())
            table.wireUp();

        for (IViewMetadata view : getViews())
            view.wireUp();
    }

}
