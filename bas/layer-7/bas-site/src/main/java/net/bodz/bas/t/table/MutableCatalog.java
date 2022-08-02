package net.bodz.bas.t.table;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.IElements;
import net.bodz.bas.json.JsonObject;

public class MutableCatalog
        implements
            ICatalog {

    IMutableCatalogMetadata metadata;
    Map<String, ISchema> schemas = createMap();

    public MutableCatalog() {
        this(null);
    }

    public MutableCatalog(DefaultCatalogMetadata metadata) {
        if (metadata == null)
            metadata = createMetadata();
        this.metadata = metadata;
    }

    public static MutableCatalog fromSchemaElement(IElement x_schema)
            throws ParseException, LoaderException {
        MutableCatalog o = new MutableCatalog();
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
        return new DefaultCatalogMetadata();
    }

    @Override
    public Map<String, ISchema> getSchemas() {
        return schemas;
    }

    public synchronized void addSchema(MutableSchema o) {
        if (o == null)
            throw new NullPointerException("schema");

        if (o.getMetadata() == null)
            throw new IllegalArgumentException("Schema without metadata");

        if (o.getParent() != null)
            throw new IllegalStateException("Already attached to another parent");

        ICatalogMetadata oMetadataParent = o.getMetadata().getParent();
        if (oMetadataParent != getMetadata())
            throw new IllegalArgumentException("Conflict metadata");

        String oName = o.getMetadata().getName();

        // metadata.addTable(o.getMetadata())
        if (metadata instanceof IMutableCatalogMetadata) {
            IMutableCatalogMetadata _metadata = metadata;
            ISchemaMetadata om = o.getMetadata();
            _metadata.addSchema(om);

            if (om instanceof IMutableSchemaMetadata) {
                IMutableSchemaMetadata _om = (IMutableSchemaMetadata) om;
                _om.setParent(_metadata);
            }
        }
        schemas.put(oName, o);
    }

    public boolean removeSchema(String schemaName) {
        ISchema o = schemas.remove(schemaName);
        if (o == null)
            return false;

        // metadata.removeTable(o.getMetadata());
        if (metadata instanceof IMutableCatalogMetadata) {
            IMutableCatalogMetadata _metadata = metadata;
            ISchemaMetadata om = o.getMetadata();
            _metadata.removeSchema(om);

            if (om instanceof IMutableSchemaMetadata) {
                IMutableSchemaMetadata _om = (IMutableSchemaMetadata) om;
                _om.setParent(null);
            }
        }

        return true;
    }

    public boolean removeSchema(ISchema schema) {
        String name = schema.getMetadata().getName();
        return removeSchema(name);
    }

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
    public void readObject(JsonObject o)
            throws ParseException {
        JsonObject j_md = o.getJsonObject(K_METADATA);
        if (j_md != null) {
            DefaultCatalogMetadata metadata = createMetadata();
            metadata.readObject(j_md);
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
            schema.readObjectBoxed(j_schema);
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
