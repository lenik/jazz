package net.bodz.bas.t.catalog;

import java.util.HashMap;
import java.util.Map;

public class CatalogSubset {

    public String catalogName;

    public SchemaSubset anySchema;
    public Map<String, SchemaSubset> schemas = new HashMap<>();
    static final Map<String, SchemaSubset> ALL_SCHEMAS = null;

    public CatalogSubset(String catalogName) {
        this.catalogName = catalogName;
        this.anySchema = new SchemaSubset(catalogName);
    }

    public boolean isAll() {
        return schemas == ALL_SCHEMAS;
    }

    public boolean isEmpty() {
        if (!anySchema.isEmpty())
            return false;
        if (schemas != null)
            if (!schemas.isEmpty())
                return false;
        return true;
    }

    public boolean contains(TableId id) {
        if (schemas == ALL_SCHEMAS)
            return true;

        if (anySchema.contains(id.getTableName()))
            return true;

        SchemaSubset schema = schemas.get(id.getSchemaName());
        if (schema != null)
            if (schema.contains(id.getTableName()))
                return true;

        return false;
    }

    public ContainingType contains(String schemaName) {
        if (schemaName == null)
            throw new NullPointerException("schemaName");
        if (schemas == ALL_SCHEMAS)
            return ContainingType.FULL;

        if (anySchema.isAll())
            return ContainingType.FULL;

        SchemaSubset subset = schemas.get(schemaName);
        if (subset != null && !subset.isEmpty())
            return ContainingType.FULL;

        if (!anySchema.isEmpty())
            return ContainingType.PARTIAL;

        return ContainingType.NONE;
    }

    public void addAllSchemas() {
        schemas = ALL_SCHEMAS;
    }

    public SchemaSubset getSchema(String schemaName) {
        return schemas.get(schemaName);
    }

    public SchemaSubset addSchema(String schemaName) {
        if (schemaName == null)
            throw new NullPointerException("schemaName");
        if (schemas == ALL_SCHEMAS)
            return null;
        SchemaSubset subset = schemas.get(schemaName);
        if (subset == null) {
            subset = new SchemaSubset(schemaName);
            schemas.put(schemaName, subset);
        }
        return subset;
    }

    public void addFullSchema(String schemaName) {
        SchemaSubset subset = addSchema(schemaName);
        if (subset != ALL_SCHEMAS)
            subset.addAllTables();
    }

    public boolean addTable(TableId id) {
        String schemaName = id.getSchemaName();
        SchemaSubset subset = (schemaName == null) ? anySchema : addSchema(schemaName);
        return subset.addTable(id.getTableName());
    }

    @Override
    public String toString() {
        if (schemas == ALL_SCHEMAS)
            return "\\ALL";
        else
            return schemas.keySet().toString();
    }

}
