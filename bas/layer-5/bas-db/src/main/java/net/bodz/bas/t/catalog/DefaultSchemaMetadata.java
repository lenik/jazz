package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import net.bodz.bas.t.tuple.QualifiedName;

public class DefaultSchemaMetadata
        implements
            IMutableSchemaMetadata {

    static final Logger logger = LoggerFactory.getLogger(DefaultSchemaMetadata.class);

    SchemaOid schemaId = new SchemaOid();
    String contextCatalogName;
    QualifiedName qName;

    String label;
    String description;

    IMutableCatalogMetadata parent;

    Map<String, ITableMetadata> tableMap = new LinkedHashMap<>();
    Map<String, IViewMetadata> viewMap = new LinkedHashMap<>();

    Boolean convertToUpperCase;
    Map<String, String> canonicalNames = new HashMap<>();

    public DefaultSchemaMetadata() {
    }

    public DefaultSchemaMetadata(IMutableCatalogMetadata parent) {
        this.parent = parent;
    }

    @Override
    public SchemaOid getId() {
        return schemaId;
    }

    public void setId(SchemaOid id) {
        this.schemaId = id;
    }

    @Override
    public String getContextCatalogName() {
        return contextCatalogName;
    }

    public void setContextCatalogName(String contextCatalogName) {
        this.contextCatalogName = contextCatalogName;
    }

    @Override
    public QualifiedName getJavaQName() {
        return qName;
    }

    @Override
    public void setJavaQName(QualifiedName qName) {
        this.qName = qName;
    }

    @Override
    public String getJavaPackage() {
        if (qName != null)
            return qName.packageName;
        if (parent != null)
            return parent.getJavaPackageName();
        return null;
    }

//    @Override
//    public void setJavaPackage(String javaPackage) {
//        this.javaPackage = javaPackage;
//    }

    @Override
    public boolean isValidTableId(TableOid tableName) {
        if (tableName == null)
            throw new NullPointerException("tableName");
        if (this.schemaId == null)
            return true;
        return this.schemaId.contains(tableName);
    }

    @Override
    public boolean isValidIdOf(String catalogName) {
        if (NamePattern.matches(catalogName, this.schemaId.catalogName))
            return true;
        return false;
    }

    @Override
    public ICatalogMetadata getParent() {
        return parent;
    }

    @Override
    public void setParent(ICatalogMetadata parent) {
        this.parent = (IMutableCatalogMetadata) parent;
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

    public DefaultTableMetadata getOrCreateTable(TableOid oid) {
        checkTableId(oid);
        return getOrCreateTable(oid.tableName);
    }

    @Override
    public DefaultTableMetadata autoLoadTableFromJDBC(TableOid oid, Connection autoLoadConnection,
            LoadFromJDBCOptions options) {
        return autoLoadTableFromJDBC(oid.getTableName(), autoLoadConnection, options);
    }

    public DefaultTableMetadata autoLoadTableFromJDBC(String name, Connection cn, LoadFromJDBCOptions options) {
        try {
            return loadTableFromJDBC(name, cn, options);
        } catch (SQLException e) {
            throw new LoadException("Failed to load table " + name + ": " + e.getMessage(), e);
        }
    }

    public synchronized DefaultTableMetadata loadTableFromJDBC(String tableName, Connection cn,
            LoadFromJDBCOptions options)
            throws SQLException {
        if (tableName == null)
            throw new NullPointerException("tableName");
        DefaultTableMetadata table = (DefaultTableMetadata) tableMap.get(tableName);
        if (table == null) {
            table = newTable(tableName);
            options.enterNew();
            try {
                options.setLoadKeys(true);
                table.loadFromJDBC(cn, options);
            } finally {
                options.leave();
            }
            if (parent != null)
                parent.addTable(table);
            else
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
            table.getId().assign(schemaId.catalogName, schemaId.schemaName, name);
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
        table.getId().assign(schemaId.catalogName, schemaId.schemaName, tableName);
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
    public List<ITableMetadata> findTables(TableOid pattern, boolean ignoreCase) {
        if (pattern != null) {
            if (! pattern.toSchemaId().contains(this.getId(), ignoreCase))
                return Collections.emptyList();
        }
        List<ITableMetadata> list = new ArrayList<>();
        for (ITableMetadata table : getTables()) {
            if (pattern != null)
                if (! pattern.contains(table.getId(), ignoreCase))
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

    public DefaultTableMetadata getOrCreateView(TableOid oid) {
        checkTableId(oid);
        return getOrCreateView(oid.tableName);
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
            view.getId().assign(schemaId.catalogName, schemaId.schemaName, name);
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
        view.getId().assign(schemaId.catalogName, schemaId.schemaName, viewName);
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
    public List<IViewMetadata> findViews(TableOid pattern, boolean ignoreCase) {
        if (pattern != null) {
            if (! pattern.toSchemaId().contains(this.getId(), ignoreCase))
                return Collections.emptyList();
        }
        List<IViewMetadata> list = new ArrayList<>();
        for (IViewMetadata view : viewMap.values()) {
            if (pattern != null)
                if (! pattern.contains(view.getId(), ignoreCase))
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
            ITableMetadata view = viewMap.get(key);
            sb.append(view.getName());
        }
        return sb.toString();
    }

    @Override
    public void findCrossReferences(Collection<CrossReference> list, TableOid parent) {
        for (ITableMetadata table : getTables()) {
            for (CrossReference xref : table.getForeignKeys().values()) {
                TableKey parentKey = xref.getParentKey();
                if (parentKey.getId().equals(parent))
                    list.add(xref);
            }
        }
    }

    @Override
    public String toString() {
        return "schema " + getCompactName() + "(" + getTableNames() + ")";
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        getId().jsonIn(o, opts);

        setJavaQName(o.getString(K_Q_NAME));

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
        setJavaQName(x_metadata.a(K_Q_NAME).getString());

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
            schemaId.assign(catalogName, schemaName); // correct char case.
            return DefaultSchemaMetadata.this;
        }

        @Override
        public ITableMetadata table(ResultSet rs)
                throws SQLException {
            TableOid oid = new TableOid();
            oid.readFromJDBC(rs);

            TableType type = TableType.parseJDBC(rs);
            SelectMode mode = loadSelector.selectTable(oid, type);
            if (mode == SelectMode.SKIP)
                return null;

            switch (type) {
            case TABLE:
                DefaultTableMetadata table = new DefaultTableMetadata(DefaultSchemaMetadata.this);
                table.getJDBCMetaDataHandler().table(rs);
                if (mode == SelectMode.EXCLUDE)
                    table.setExcluded(true);
                addTable(table);
                return table;

            case VIEW:
            case MATERIALIZED_VIEW:
                DefaultViewMetadata view = new DefaultViewMetadata(DefaultSchemaMetadata.this);
                view.getJDBCMetaDataHandler().table(rs);
                if (mode == SelectMode.EXCLUDE)
                    view.setExcluded(true);
                addView(view);
                return view;

            default:
                return null;
            }
        }

        @Override
        public IColumnMetadata column(ResultSet rs)
                throws SQLException {
            TableOid oid = new TableOid();
            oid.readFromJDBC(rs);
            // if (!loadSelector.selectTable(id)) return null;

            ITableMetadata table = getTable(oid.getTableName());
            if (table != null) {
                return table.getJDBCMetaDataHandler().column(rs);
            }

            IViewMetadata view = getView(oid.getTableName());
            if (view != null) {
                return view.getJDBCMetaDataHandler().column(rs);
            }

            return null;
        }

        @Override
        public void viewColumnUsage(ResultSet rs)
                throws SQLException {
            TableOid viewId = new TableOid();
            viewId.catalogName = rs.getString("view_catalog");
            viewId.schemaName = rs.getString("view_schema");
            viewId.tableName = rs.getString("view_name");
            SelectMode mode = loadSelector.selectTable(viewId, TableType.VIEW);
            if (mode == SelectMode.SKIP)
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
        Set<ITableMetadata> tables = new LinkedHashSet<>();

        // Parse from schema's metadata
        rs = dmd.getSchemas(schemaId.catalogName, schemaId.schemaName);
        if (rs.next())
            metaDataHandler.schema(rs);
        rs.close();

        rs = dmd.getTables(schemaId.catalogName, schemaId.schemaName, null, types);
        while (rs.next()) {
            ITableMetadata table = metaDataHandler.table(rs);
            tables.add(table);
        }
        rs.close();

        // Set<String> typeSet = new HashSet<>(Arrays.asList(types));
        rs = dmd.getColumns(schemaId.catalogName, schemaId.schemaName, null, null);
        while (rs.next())
            metaDataHandler.column(rs);
        rs.close();

        rs = dmd.getPrimaryKeys(schemaId.catalogName, schemaId.schemaName, null);
        Map<TableOid, TableKey> pkmap = TableKey.convertFromJDBC(rs);
        for (TableOid tableId : pkmap.keySet()) {
            ITableMetadata table = getTable(tableId);
            if (table == null)
                continue;
            TableKey primaryKey = pkmap.get(tableId);
            metaDataHandler.primaryKey(table, primaryKey);
        }
        rs.close();

        rs = dmd.getCrossReference(null, null, null, //
                schemaId.catalogName, schemaId.schemaName, null);

        ListMap<TableOid, CrossReference> foreignMap = //
                CrossReference.convertToForeignMap(rs);

        for (TableOid foreignName : foreignMap.keySet()) {
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
            if (schemaId.catalogName != null)
                sql += " and view_catalog = ?";
            if (schemaId.schemaName != null)
                sql += " and view_schema = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            int psIndex = 0;
            if (schemaId.catalogName != null)
                ps.setString(++psIndex, schemaId.catalogName);
            if (schemaId.schemaName != null)
                ps.setString(++psIndex, schemaId.schemaName);
            rs = ps.executeQuery();
        }
        while (rs.next())
            metaDataHandler.viewColumnUsage(rs);
        rs.close();

        for (ITableMetadata table : tables)
            metaDataHandler.endTable(table);
        metaDataHandler.endSchema(this);

        // creation-order
        for (ITableMetadata table : getTables())
            table.wireUp();

        for (IViewMetadata view : getViews())
            view.wireUp();
    }

}
