package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.json.JsonObject;

public class DefaultCatalogMetadata
        implements
            IMutableCatalogMetadata {

    static final String ANY_SCHEMA_NAME = "?";

    String name;
    String javaName;
    String javaPackage;

    String label;
    String description;

    List<String> searchPath = new ArrayList<>();
    ISchemaMetadata anySchema;
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

    @Override
    public String getJavaName() {
        return javaName;
    }

    @Override
    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    @Override
    public String getJavaPackage() {
        return javaPackage;
    }

    @Override
    public void setJavaPackage(String javaPackage) {
        this.javaPackage = javaPackage;
    }

    @Override
    public boolean isValidSchemaId(SchemaOid id) {
        if (id == null)
            throw new NullPointerException("id");
        if (NamePattern.matches(name, id.catalogName))
            return true;
        return false;
    }

    @Override
    public boolean isValidTableId(TableOid oid) {
        if (oid == null)
            throw new NullPointerException("id");
        if (NamePattern.matches(name, oid.catalogName))
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

    public List<String> getSearchPath() {
        return searchPath;
    }

    @Override
    public ISchemaMetadata getSchema(String name) {
        if (name == null)
            return anySchema;
        else
            return schemas.get(name);
    }

    public DefaultSchemaMetadata getOrCreateSchema(SchemaOid id) {
        checkSchemaId(id);
        return getOrCreateSchema(id.schemaName);
    }

    public synchronized DefaultSchemaMetadata getOrCreateSchema(String schemaName) {
        DefaultSchemaMetadata schema;
        if (schemaName == null)
            schema = (DefaultSchemaMetadata) anySchema;
        else
            schema = (DefaultSchemaMetadata) schemas.get(schemaName);

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
        DefaultSchemaMetadata dsm = new DefaultSchemaMetadata(this);
        dsm.getId().assign(name, schemaName);
        dsm.setContextCatalogName(name);
        return dsm;
    }

    @Override
    public void addSchema(ISchemaMetadata schema) {
        if (schema == null)
            throw new NullPointerException("schema");
        String schemaName = schema.getName();

        ISchemaMetadata existing;
        if (schemaName == null)
            existing = anySchema;
        else
            existing = schemas.get(schemaName);

        if (existing != null)
            throw new DuplicatedKeyException("Schema already existed: " + schemaName);

        if (schemaName == null) {
            anySchema = schema;
        } else {
            schemas.put(schemaName, schema);
        }
    }

    @Override
    public boolean removeSchema(ISchemaMetadata schema) {
        String schemaName = schema.getName();
        return removeSchema(schemaName);
    }

    @Override
    public boolean removeSchema(String schemaName) {
        ISchemaMetadata removed;
        if (schemaName == null) {
            removed = anySchema;
            anySchema = null;
        } else {
            removed = schemas.remove(schemaName);
        }
        return removed != null;
    }

    public DefaultTableMetadata newTable(TableOid oid) {
        if (oid == null)
            throw new NullPointerException("id");
        return getOrCreateSchema(oid.schemaName).newTable(oid.tableName);
    }

    @Override
    public void addTable(ITableMetadata table) {
        if (table == null)
            throw new NullPointerException("table");
        getOrCreateSchema(table.getId().toSchemaId()).addTable(table);
    }

    public DefaultTableMetadata getOrCreateTable(TableOid oid) {
        if (oid == null)
            throw new NullPointerException("id");
        return getOrCreateSchema(oid.toSchemaId()).getOrCreateTable(oid);
    }

    @Override
    public boolean removeTable(ITableMetadata table) {
        return removeTable(table.getId());
    }

    @Override
    public boolean removeTable(TableOid oid) {
        if (! isValidTableId(oid))
            return false;
        IMutableSchemaMetadata schema = (IMutableSchemaMetadata) getSchema(oid.schemaName);
        if (schema == null)
            return false;
        return schema.removeTable(oid.tableName);
    }

    @Override
    public ITableMetadata autoLoadTableFromJDBC(TableOid oid, Connection autoLoadConnection,
            LoadFromJDBCOptions options) {
        if (oid == null)
            throw new NullPointerException("id");
        DefaultSchemaMetadata dsm = getOrCreateSchema(oid.toSchemaId());
        return dsm.autoLoadTableFromJDBC(oid, autoLoadConnection, options);
    }

    public DefaultTableMetadata loadTableFromJDBC(TableOid oid, Connection connection, LoadFromJDBCOptions options)
            throws SQLException {
        if (oid == null)
            throw new NullPointerException("id");
        if (connection == null)
            throw new NullPointerException("connection");

        if (oid.getSchemaName() == null) {
            if (anySchema != null) {
                ITableMetadata anyTable = anySchema.getTable(oid.tableName);
                if (anyTable != null)
                    return (DefaultTableMetadata) anyTable;
            }
            for (ISchemaMetadata schema : schemas.values()) {
                ITableMetadata anyTable = schema.getTable(oid.tableName);
                if (anyTable != null)
                    return (DefaultTableMetadata) anyTable;
            }
        }

        DefaultSchemaMetadata schema = getOrCreateSchema(oid.toSchemaId());
        return schema.loadTableFromJDBC(oid.tableName, connection, options);
    }

    @Override
    public SchemaList findSchemas(SchemaOid pattern, boolean ignoreCase) {
        if (pattern != null) {
            if (! NamePattern.matches(name, pattern.getCatalogName(), ignoreCase))
                return SchemaList.empty();
        }
        SchemaList schemaList = new SchemaList();
        for (ISchemaMetadata schema : this) {
            if (pattern != null)
                if (! pattern.contains(schema.getId(), ignoreCase))
                    continue;
            schemaList.add(schema);
        }
        return schemaList;
    }

    @Override
    public List<ITableMetadata> findTables(TableOid pattern, boolean ignoreCase) {
        SchemaOid schemaPattern = null;
        if (pattern != null) {
            if (! NamePattern.matches(name, pattern.getCatalogName(), ignoreCase))
                return Collections.emptyList();
            schemaPattern = pattern.toSchemaId();
        }

        List<ITableMetadata> tableList = new ArrayList<>();
        for (ISchemaMetadata schema : this) {
            if (pattern != null)
                if (! schemaPattern.contains(schema.getId(), ignoreCase))
                    continue;
            List<ITableMetadata> part = schema.findTables(pattern, ignoreCase);
            tableList.addAll(part);
        }
        return tableList;
    }

    @Override
    public void findCrossReferences(Collection<CrossReference> list, TableOid parent) {
        for (ISchemaMetadata schema : getSchemas().values())
            schema.findCrossReferences(list, parent);
        if (anySchema != null)
            anySchema.findCrossReferences(list, parent);
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
                schemaName = ANY_SCHEMA_NAME;
            sb.append(schemaName);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getName() + "(" + getSchemaNames() + ")";
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        name = o.getString(K_NAME);

        javaName = o.getString(K_JAVA_NAME);

        JsonObject jm = o.getJsonObject(K_SCHEMAS);
        Map<String, ISchemaMetadata> schemas = new LinkedHashMap<>();
        for (String key : jm.keySet()) {
            JsonObject item = jm.getJsonObject(key);
            DefaultSchemaMetadata schema = newSchema(null);
            schema.jsonIn(item, opts);
            schemas.put(key, schema);
        }
        this.schemas = schemas;
    }

    @Override
    public void readObject(IElement x_metadata)
            throws ParseException, LoaderException {
        name = x_metadata.a(K_NAME).getString();
        javaName = x_metadata.a(K_JAVA_NAME).getString();

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

    class CatalogHandler
            implements
                IJDBCMetaDataHandler {

        @Override
        public ISchemaMetadata schema(ResultSet rs)
                throws SQLException {
            String schemaName = rs.getString("TABLE_SCHEM");
            DefaultSchemaMetadata schema = newSchema(schemaName);
            SelectMode mode = loadSelector.selectSchema(schema.schemaId);
            if (mode != SelectMode.SKIP) {
                schema.setJDBCLoadSelector(loadSelector);
                addSchema(schema);
            }
            return schema;
        }

    }

    IJDBCLoadSelector loadSelector = IJDBCLoadSelector.ALL;

    @Override
    public IJDBCMetaDataHandler getJDBCMetaDataHandler() {
        return new CatalogHandler();
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
        IJDBCMetaDataHandler metaDataHandler = getJDBCMetaDataHandler();
        ResultSet rs;

        rs = dmd.getSchemas(name, null);
        while (rs.next())
            metaDataHandler.schema(rs);
        rs.close();

        for (ISchemaMetadata schema : this) {
            DefaultSchemaMetadata dsm = (DefaultSchemaMetadata) schema;
            dsm.loadFromJDBC(connection, types);
        }
    }

    public void dump() {
        dump(Stdio.cout.indented());
    }

    public void dump(ITreeOut out) {
        accept(new CatalogDumper(out).indented());
    }

    static DefaultCatalogMetadata defaultInstance = new DefaultCatalogMetadata();

    public static DefaultCatalogMetadata getDefaultInstance() {
        return defaultInstance;
    }

}
