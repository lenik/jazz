package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;

public class DefaultCatalogMetadata
        implements
            IMutableCatalogMetadata {

    String name;
    String defaultSchemaName = "public";

    String label;
    String description;

    Map<String, ISchemaMetadata> schemas = new LinkedHashMap<>();
    Boolean convertToUpperCase;
    Map<String, String> canonicalNames = new HashMap<>();

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultSchemaName() {
        return defaultSchemaName;
    }

    public void setDefaultSchemaName(String defaultSchemaName) {
        this.defaultSchemaName = defaultSchemaName;
    }

    @Override
    public boolean isValidSchemaId(SchemaId id) {
        if (id == null)
            throw new NullPointerException("id");
        if (NamePattern.matches(name, id.catalogName))
            return true;
        return false;
    }

    @Override
    public boolean isValidTableId(TableId id) {
        if (id == null)
            throw new NullPointerException("id");
        if (NamePattern.matches(name, id.catalogName))
            return true;
        return false;
    }

    @Override
    public boolean isValidIdOf(String catalogName) {
        if (NamePattern.matches(catalogName, this.name))
            return true;
        return false;
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
    public Iterator<ISchemaMetadata> iterator() {
        return schemas.values().iterator();
    }

    @Override
    public Map<String, ISchemaMetadata> getSchemas() {
        return schemas;
    }

    @Override
    public int getSchemaCount() {
        return schemas.size();
    }

    @Override
    public ISchemaMetadata getSchema(String name) {
        if (name == null)
            name = defaultSchemaName;
        return schemas.get(name);
    }

    public DefaultSchemaMetadata getOrCreateSchema(SchemaId id) {
        checkSchemaId(id);
        return getOrCreateSchema(id.schemaName);
    }

    public synchronized DefaultSchemaMetadata getOrCreateSchema(String schemaName) {
        if (schemaName == null)
            schemaName = defaultSchemaName;
        DefaultSchemaMetadata schema = (DefaultSchemaMetadata) schemas.get(schemaName);
        if (schema == null) {
            DefaultSchemaMetadata dsm = newSchema(schemaName);
            addSchema(schema = dsm);
        }
        return schema;
    }

    public DefaultSchemaMetadata getOrCreateDefaultSchema() {
        return getOrCreateSchema((String) null);
    }

    protected DefaultSchemaMetadata newSchema(String schemaName) {
        if (schemaName == null)
            schemaName = defaultSchemaName;
        DefaultSchemaMetadata dsm = new DefaultSchemaMetadata(this);
        dsm.getId().assign(name, schemaName);

        SchemaId defaultName = new SchemaId();
        defaultName.assign(null, defaultSchemaName);
        dsm.setDefaultName(defaultName);
        return dsm;
    }

    @Override
    public void addSchema(ISchemaMetadata schema) {
        if (schema == null)
            throw new NullPointerException("schema");
        String schemaName = schema.getName();
        if (schemaName == null)
            schemaName = defaultSchemaName;

        ISchemaMetadata existing = schemas.get(schemaName);
        if (existing != null)
            throw new DuplicatedKeyException("Schema already existed: " + schemaName);
        schemas.put(schemaName, schema);
    }

    @Override
    public boolean removeSchema(ISchemaMetadata schema) {
        String schemaName = schema.getName();
        if (schemaName == null)
            schemaName = defaultSchemaName;
        return removeSchema(schemaName);
    }

    @Override
    public boolean removeSchema(String schemaName) {
        if (schemaName == null)
            schemaName = defaultSchemaName;
        ISchemaMetadata schema = schemas.remove(schemaName);
        return schema != null;
    }

    public DefaultTableMetadata newTable(TableId id) {
        if (id == null)
            throw new NullPointerException("id");
        return getOrCreateSchema(id.schemaName).newTable(id.tableName);
    }

    public void addTable(ITableMetadata table) {
        if (table == null)
            throw new NullPointerException("table");
        getOrCreateSchema(table.getId().toSchemaId()).addTable(table);
    }

    public DefaultTableMetadata getOrCreateTable(TableId id) {
        if (id == null)
            throw new NullPointerException("id");
        return getOrCreateSchema(id.toSchemaId()).getOrCreateTable(id);
    }

    public DefaultTableMetadata loadTableFromJDBC(TableId id, Connection connection)
            throws SQLException {
        if (id == null)
            throw new NullPointerException("id");
        if (connection == null)
            throw new NullPointerException("connection");
        return getOrCreateSchema(id.toSchemaId()).loadTableFromJDBC(id.tableName, connection);
    }

    @Override
    public SchemaList findSchemas(SchemaId pattern, boolean ignoreCase) {
        if (pattern != null) {
            if (!NamePattern.matches(name, pattern.getCatalogName(), ignoreCase))
                return SchemaList.empty();
        }
        SchemaList schemaList = new SchemaList();
        for (ISchemaMetadata schema : this) {
            if (pattern != null)
                if (!pattern.contains(schema.getId(), ignoreCase))
                    continue;
            schemaList.add(schema);
        }
        return schemaList;
    }

    @Override
    public TableList findTables(TableId pattern, boolean ignoreCase) {
        SchemaId schemaPattern = null;
        if (pattern != null) {
            if (!NamePattern.matches(name, pattern.getCatalogName(), ignoreCase))
                return TableList.empty();
            schemaPattern = pattern.toSchemaId();
        }

        TableList tableList = new TableList();
        for (ISchemaMetadata schema : this) {
            if (pattern != null)
                if (!schemaPattern.contains(schema.getId(), ignoreCase))
                    continue;
            TableList part = schema.findTables(pattern, ignoreCase);
            tableList.addAll(part);
        }
        return tableList;
    }

    String getSchemaNames() {
        StringBuilder sb = new StringBuilder(schemas.size() * 16);
        for (String key : schemas.keySet()) {
            if (sb.length() != 0)
                sb.append(", ");
            ISchemaMetadata schema = schemas.get(key);
            String schemaName = schema.getName();
            assert schemaName != null;
            if (schemaName == null)
                schemaName = defaultSchemaName;
            sb.append(schemaName);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getName() + "(" + getSchemaNames() + ")";
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        name = o.getString(K_NAME);

        JsonObject jm = o.getJsonObject(K_SCHEMAS);
        Map<String, ISchemaMetadata> schemas = new LinkedHashMap<>();
        for (String key : jm.keySet()) {
            JsonObject item = jm.getJsonObject(key);
            DefaultSchemaMetadata schema = newSchema(null);
            schema.readObject(item);
            schemas.put(key, schema);
        }
        this.schemas = schemas;
    }

    @Override
    public void readObject(IElement x_metadata)
            throws ParseException, LoaderException {
        IElement x_schemas = x_metadata.selectByTag(K_SCHEMAS).first();
        Map<String, ISchemaMetadata> schemas = new LinkedHashMap<>();
        for (IElement x_schema : x_schemas.children()) {
            assert x_schema.getTagName().equals(K_SCHEMA);
            DefaultSchemaMetadata schema = newSchema(null);
            schema.readObject(x_schema);
            String key = schema.getName();
            schemas.put(key, schema);
        }
        this.schemas = schemas;
    }

    class MetaDataHandler
            implements
                IJDBCMetaDataHandler {

        @Override
        public void schema(ResultSet rs)
                throws SQLException {
            String schemaName = rs.getString("TABLE_SCHEM");
            DefaultSchemaMetadata schema = newSchema(schemaName);
            addSchema(schema);
        }

        @Override
        public void table(ResultSet rs)
                throws SQLException {
        }

        @Override
        public void column(ResultSet rs)
                throws SQLException {
        }

        @Override
        public void primaryKey(ITableMetadata table, TableKey primaryKey)
                throws SQLException {
        }

        @Override
        public void crossReference(ITableMetadata table, CrossReference crossRef)
                throws SQLException {
        }

    }

    @Override
    public MetaDataHandler getJDBCMetaDataHandler() {
        return new MetaDataHandler();
    }

    public void loadFromJDBC(Connection connection, String... types)
            throws SQLException {
        DatabaseMetaData dmd = connection.getMetaData();
        MetaDataHandler handler = getJDBCMetaDataHandler();
        ResultSet rs;

        rs = dmd.getSchemas(name, null);
        while (rs.next())
            handler.schema(rs);
        rs.close();
    }

    static DefaultCatalogMetadata defaultInstance = new DefaultCatalogMetadata();

    public static DefaultCatalogMetadata getDefaultInstance() {
        return defaultInstance;
    }

}