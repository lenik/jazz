package net.bodz.bas.t.catalog;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.db.sql.dialect.ISqlDialect;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.IElements;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class MutableCatalog
        implements ICatalog {

    String name;
    ISqlDialect dialect;
    IMutableCatalogMetadata metadata;

    Map<String, ISchema> schemas = createMap();

    public MutableCatalog(String name, ISqlDialect dialect) {
        this.name = name;
        this.dialect = dialect;
    }

    public MutableCatalog(@NotNull IMutableCatalogMetadata metadata) {
        this.metadata = metadata;
        this.name = metadata.getName();
        this.dialect = metadata.getDialect();
    }

    public static MutableCatalog fromSchemaElement(IElement x_schema, String catalogName, ISqlDialect dialect)
            throws ParseException, LoaderException {
        MutableCatalog o = new MutableCatalog(catalogName, dialect);
        o.readObject(x_schema);
        return o;
    }

    protected Map<String, ISchema> createMap() {
        return new LinkedHashMap<>();
    }

    @Override
    public ICatalogMetadata getMetadata() {
        return metadata;
    }

    protected DefaultCatalogMetadata createMetadata() {
        return new DefaultCatalogMetadata(name, dialect);
    }

    @Override
    public Map<String, ISchema> getSchemas() {
        return schemas;
    }

    public synchronized void addSchema(MutableSchema schema) {
        if (schema == null)
            throw new NullPointerException("schema");

        if (schema.getMetadata() == null)
            throw new IllegalArgumentException("Schema without metadata");

        if (schema.getParent() != null)
            throw new IllegalStateException("Already attached to another parent");

        ICatalogMetadata oMetadataParent = schema.getMetadata().getParent();
        if (oMetadataParent != getMetadata())
            throw new IllegalArgumentException("Conflict metadata");

        String schemaName = schema.getMetadata().getName();

        // metadata.addTable(o.getMetadata())
        if (metadata != null) {
            ISchemaMetadata schemaMetadata = schema.getMetadata();
            metadata.addSchema(schemaMetadata);

            if (schemaMetadata instanceof IMutableSchemaMetadata) {
                IMutableSchemaMetadata schemaMutable = (IMutableSchemaMetadata) schemaMetadata;
                schemaMutable.setParent(metadata);
            }
        }
        schemas.put(schemaName, schema);
    }

    public boolean removeSchema(String schemaName) {
        ISchema schema = schemas.remove(schemaName);
        if (schema == null)
            return false;

        // metadata.removeTable(o.getMetadata());
        if (metadata != null) {
            ISchemaMetadata schemaMetadata = schema.getMetadata();
            metadata.removeSchema(schemaMetadata);

            if (schemaMetadata instanceof IMutableSchemaMetadata) {
                IMutableSchemaMetadata schemaMutable = (IMutableSchemaMetadata) schemaMetadata;
                schemaMutable.setParent(null);
            }
        }

        return true;
    }

    public boolean removeSchema(ISchema schema) {
        String name = schema.getMetadata().getName();
        return removeSchema(name);
    }

    @NotNull
    @Override
    public Iterator<ISchema> iterator() {
        return schemas.values().iterator();
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", //
                getSchemaCount(), getMetadata().toString());
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        JsonObject j_md = o.getJsonObject(K_METADATA);
        if (j_md != null) {
            DefaultCatalogMetadata metadata = createMetadata();
            metadata.jsonIn(j_md, opts);
            this.metadata = metadata;
        }

        JsonObject j_schemas = o.getJsonObject(K_SCHEMAS);
        Map<String, ISchema> map = createMap();

        for (String key : j_schemas.keySet()) {
            ISchemaMetadata schemaMetadata = metadata.getSchema(key);
            if (schemaMetadata == null)
                throw new ParseException("No metadata for schema " + key);

            JsonObject j_schema = j_schemas.getJsonObject(key);
            MutableSchema schema = new MutableSchema(schemaMetadata);
            schema.jsonIn(j_schema, opts);
            map.put(key, schema);
        }
        this.schemas = map;
    }

    @Override
    public void readObject(IElement x_catalog)
            throws ParseException, LoaderException {
        IElement x_md = x_catalog.selectByTag(K_METADATA).getFirst();
        if (x_md != null && x_md.getParentNode() == x_catalog) {
            DefaultCatalogMetadata metadata = createMetadata();
            metadata.readObject(x_md);
            this.metadata = metadata;
        }

        IElement x_schemas = x_catalog.selectByTag(K_SCHEMAS).getFirst();
        IElements x_schema_v = x_schemas.children();
        int rowCount = x_schemas.getElementCount();
        Map<String, ISchema> map = createMap();

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            IElement x_schema = x_schema_v.get(rowIndex);
            assert x_schema.getTagName().equals(K_SCHEMA);

            MutableSchema schema = MutableSchema.fromSchemaElement(x_schema);

            String key = schema.getMetadata().getName();

            map.put(key, schema);
        }
        this.schemas = map;
    }

}
