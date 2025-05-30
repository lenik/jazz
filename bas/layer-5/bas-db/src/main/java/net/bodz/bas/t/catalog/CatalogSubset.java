package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class CatalogSubset
        implements IJsonForm {

    public String catalogName;

    public SchemaSubset anySchema;

    public final Map<String, SchemaSubset> schemas = new HashMap<>();
    boolean allSchemas = false;

    public CatalogSubset(String catalogName) {
        this.catalogName = catalogName;
        this.anySchema = new SchemaSubset(catalogName);
    }

    public boolean isAll() {
        return allSchemas;
    }

    public boolean isEmpty() {
        if (!anySchema.isEmpty())
            return false;
        if (!schemas.isEmpty())
            return false;
        if (allSchemas)
            return false; // XXX unknown
        return true;
    }

    public boolean contains(TableOid oid) {
        if (allSchemas)
            return true;

        if (anySchema.contains(oid.getTableName()))
            return true;

        SchemaSubset schema = schemas.get(oid.getSchemaName());
        if (schema != null)
            if (schema.contains(oid.getTableName()))
                return true;

        return false;
    }

    public ContainingType contains(String schemaName) {
        if (schemaName == null)
            throw new NullPointerException("schemaName");
        if (allSchemas)
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
        allSchemas = true;
    }

    public SchemaSubset getSchema(String schemaName) {
        return schemas.get(schemaName);
    }

    public SchemaSubset addSchema(String schemaName) {
        if (schemaName == null)
            throw new NullPointerException("schemaName");
        if (allSchemas)
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
        if (subset != null)
            subset.addAllTables();
    }

    public boolean addTable(TableOid oid) {
        String schemaName = oid.getSchemaName();
        SchemaSubset subset = (schemaName == null) ? anySchema : addSchema(schemaName);
        return subset.addTableWildcards(oid.getTableName());
    }

    @Override
    public String toString() {
        if (allSchemas)
            return "\\ALL";
        else
            return schemas.keySet().toString();
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry("catalogName", catalogName);
        if (anySchema != null) {
            out.key("anySchema");
            anySchema.jsonOutValue(out, opts);
        }
        if (allSchemas)
            out.entry("allSchemas", true);
        else {
            out.key("schemas");
            out.object();
            for (String key : schemas.keySet())
                out.entry(key, schemas.get(key));
            out.endObject();
        }
    }

}
