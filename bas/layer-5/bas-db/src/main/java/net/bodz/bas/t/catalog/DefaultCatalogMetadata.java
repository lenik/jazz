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
    QualifiedSchemaName defaultSchemaName = new QualifiedSchemaName(null, "public");

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

    public QualifiedSchemaName getDefaultSchemaName() {
        return defaultSchemaName;
    }

    public void setDefaultSchemaName(QualifiedSchemaName defaultSchemaName) {
        this.defaultSchemaName = defaultSchemaName;
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
        return schemas.get(name);
    }

    synchronized ISchemaMetadata getOrCreateSchema(QualifiedSchemaName qName) {
        if (!NamePattern.matches(name, qName.catalogName))
            return null;
        return getOrCreateSchema(qName.schemaName);
    }

    synchronized DefaultSchemaMetadata getOrCreateSchema(String schemaName) {
        if (schemaName == null)
            throw new NullPointerException("schemaName");
        DefaultSchemaMetadata schema = (DefaultSchemaMetadata) schemas.get(schemaName);
        if (schema == null) {
            DefaultSchemaMetadata dsm = newSchema(schemaName);
            addSchema(schema = dsm);
        }
        return schema;
    }

    protected DefaultSchemaMetadata newSchema(String schemaName) {
        DefaultSchemaMetadata dsm = new DefaultSchemaMetadata(this);
        dsm.getQName().assign(name, schemaName);
        dsm.setDefaultName(defaultSchemaName);
        return dsm;
    }

    @Override
    public void addSchema(ISchemaMetadata schema) {
        if (schema == null)
            throw new NullPointerException("schema");
        String name = schema.getName();
        ISchemaMetadata existing = schemas.get(name);
        if (existing != null)
            throw new DuplicatedKeyException("Schema already existed: " + name);
        schemas.put(name, schema);
    }

    @Override
    public boolean removeSchema(ISchemaMetadata schema) {
        return removeSchema(schema.getName());
    }

    @Override
    public boolean removeSchema(String schemaName) {
        ISchemaMetadata schema = schemas.remove(schemaName);
        return schema != null;
    }

    public void addTable(ITableMetadata table) {
        if (table == null)
            throw new NullPointerException("table");
        QualifiedSchemaName schemaQName = table.getQName().getSchemaQName();
        DefaultSchemaMetadata schema = getOrCreateSchema(schemaQName.getSchemaName());
        schema.addTable(table);
    }

    @Override
    public SchemaList findSchemas(QualifiedSchemaName pattern, boolean ignoreCase) {
        if (pattern != null) {
            if (!NamePattern.matches(getName(), pattern.getCatalogName(), ignoreCase))
                return SchemaList.empty();
        }
        SchemaList schemaList = new SchemaList();
        for (ISchemaMetadata schema : this) {
            if (pattern != null)
                if (!pattern.contains(schema.getQName(), ignoreCase))
                    continue;
            schemaList.add(schema);
        }
        return schemaList;
    }

    @Override
    public TableList findTables(QualifiedTableName pattern, boolean ignoreCase) {
        QualifiedSchemaName schemaPattern = null;
        if (pattern != null) {
            if (!NamePattern.matches(getName(), pattern.getCatalogName(), ignoreCase))
                return TableList.empty();
            schemaPattern = pattern.getSchemaQName();
        }

        TableList tableList = new TableList();
        for (ISchemaMetadata schema : this) {
            if (pattern != null)
                if (!schemaPattern.contains(schema.getQName(), ignoreCase))
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
            sb.append(schema.getName());
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

}
