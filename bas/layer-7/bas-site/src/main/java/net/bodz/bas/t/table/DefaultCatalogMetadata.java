package net.bodz.bas.t.table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.IElements;
import net.bodz.bas.json.JsonObject;

public class DefaultCatalogMetadata
        implements
            IMutableCatalogMetadata {

    String name;

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
    public Map<String, ISchemaMetadata> getSchemas() {
        return schemas;
    }

    @Override
    public ISchemaMetadata getSchema(String name) {
        return schemas.get(name);
    }

    @Override
    public int getSchemaCount() {
        return schemas.size();
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
            DefaultSchemaMetadata schema = new DefaultSchemaMetadata();
            schema.readObject(item);
            schemas.put(key, schema);
        }
        this.schemas = schemas;
    }

    @Override
    public void readObject(IElement x_metadata)
            throws ParseException, LoaderException {
        IElement x_schemas = x_metadata.selectByTag(K_SCHEMAS).first();
        IElements x_schema_v = x_schemas.children();
        int n = x_schema_v.getElementCount();
        Map<String, ISchemaMetadata> schemas = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            IElement x_schema = x_schema_v.get(i);
            assert x_schema.getTagName().equals(K_SCHEMA);
            DefaultSchemaMetadata schema = new DefaultSchemaMetadata();
            schema.readObject(x_schema);
            String key = schema.getName();
            schemas.put(key, schema);
        }
        this.schemas = schemas;
    }

    public void loadFromJDBC(Connection connection)
            throws SQLException {
        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs;

        rs = dmd.getSchemas(name, null);
        List<String> schemaNames = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("TABLE_SCHEM");
            schemaNames.add(name);
        }
        rs.close();

        for (String schemaName : schemaNames) {
            DefaultSchemaMetadata schema = new DefaultSchemaMetadata();
            schema.getQName().assign(name, schemaName);
            schema.loadFromJDBC(connection);
            addSchema(schema);
        }
        rs.close();
    }

}
