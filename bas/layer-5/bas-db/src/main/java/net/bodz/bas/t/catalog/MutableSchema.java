package net.bodz.bas.t.catalog;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.IElements;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class MutableSchema
        implements ISchema {

    ICatalog parent;
    SchemaOid id;

    ISchemaMetadata metadata;
    Map<String, ITable> tables = createMap();

    public MutableSchema() {
        this(null, null);
    }

    public MutableSchema(ICatalog catalog, ISchemaMetadata metadata) {
        this.parent = catalog;
        if (metadata == null) {
            metadata = createMetadata(catalog.getMetadata());
        }
        this.metadata = metadata;
    }

    public static MutableSchema fromSchemaElement(IElement x_schema)
            throws ParseException, LoaderException {
        MutableSchema o = new MutableSchema();
        o.readObject(x_schema);
        return o;
    }

    protected Map<String, ITable> createMap() {
        return new LinkedHashMap<>();
    }

    @Override
    public ISchemaMetadata getMetadata() {
        return metadata;
    }

    protected DefaultSchemaMetadata createMetadata(ICatalogMetadata parent) {
        DefaultSchemaMetadata dsm = new DefaultSchemaMetadata(parent);
        if (id != null)
            dsm.getId().assign(id.catalogName, id.schemaName);
        return dsm;
    }

    @Override
    public ICatalog getParent() {
        return parent;
    }

    public void setParent(ICatalog parent) {
        this.parent = parent;
    }

    @Override
    public SchemaOid getId() {
        if (id != null)
            return id;
        ISchemaMetadata metadata = getMetadata();
        if (metadata != null)
            return metadata.getId();
        return null;
    }

    public void setId(SchemaOid id) {
        this.id = id;
    }

    @Override
    public Map<String, ITable> getTables() {
        return tables;
    }

    public synchronized void addTable(MutableTable o) {
        if (o == null)
            throw new NullPointerException("table");

        if (o.getMetadata() == null)
            throw new IllegalArgumentException("Table without metadata");

        if (o.getParent() != null)
            throw new IllegalStateException("Already attached to another parent");

        ISchemaMetadata oMetadataParent = o.getMetadata().getParent();
        if (oMetadataParent != getMetadata())
            throw new IllegalArgumentException("Conflict metadata");

        String oName = o.getMetadata().getName();

        // metadata.addTable(o.getMetadata())
        if (metadata instanceof IMutableSchemaMetadata) {
            IMutableSchemaMetadata _metadata = (IMutableSchemaMetadata) metadata;
            ITableMetadata om = o.getMetadata();
            _metadata.addTable(om);

            if (om instanceof DefaultTableMetadata) {
                DefaultTableMetadata _om = (DefaultTableMetadata) om;
                _om.setParent(_metadata);
            }
        }
        tables.put(oName, o);
    }

    public boolean removeTable(String tableName) {
        ITable o = tables.remove(tableName);
        if (o == null)
            return false;

        // metadata.removeTable(o.getMetadata());
        if (metadata instanceof IMutableSchemaMetadata) {
            IMutableSchemaMetadata _metadata = (IMutableSchemaMetadata) metadata;
            ITableMetadata om = o.getMetadata();
            _metadata.removeTable(om);

            if (om instanceof DefaultTableMetadata) {
                DefaultTableMetadata _om = (DefaultTableMetadata) om;
                _om.setParent(null);
            }
        }

        return true;
    }

    public boolean removeTable(ITable table) {
        String name = table.getMetadata().getName();
        return removeTable(name);
    }

    @NotNull
    @Override
    public Iterator<ITable> iterator() {
        return tables.values().iterator();
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", //
                getTableCount(), getMetadata().toString());
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        JsonObject j_md = o.getJsonObject(K_METADATA);
        if (j_md != null) {
            DefaultSchemaMetadata metadata = createMetadata(null);
            metadata.jsonIn(j_md, opts);
            this.metadata = metadata;
        }

        JsonObject j_tables = o.getJsonObject(K_TABLES);
        Map<String, ITable> map = createMap();

        for (String key : j_tables.keySet()) {
            ITableMetadata tableMetadata = metadata.getTable(key);
            if (tableMetadata == null)
                throw new ParseException("No metadata for table " + key);

            JsonObject j_table = j_tables.getJsonObject(key);
            MutableTable table = new MutableTable(tableMetadata);
            table.jsonIn(j_table, opts);
            map.put(key, table);
        }
        this.tables = map;
    }

    @Override
    public void readObject(IElement x_schema)
            throws ParseException, LoaderException {
        IElement x_md = x_schema.selectByTag(K_METADATA).getFirst();
        if (x_md != null && x_md.getParentNode() == x_schema) {
            DefaultSchemaMetadata metadata = createMetadata(null);
            metadata.readObject(x_md);
            this.metadata = metadata;
        }

        IElement x_tables = x_schema.selectByTag(K_TABLES).getFirst();
        IElements x_table_v = x_tables.children();
        int rowCount = x_tables.getElementCount();
        Map<String, ITable> map = createMap();

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            IElement x_table = x_table_v.get(rowIndex);
            assert x_table.getTagName().equals(K_TABLE);

            MutableTable table = MutableTable.fromTableElement(x_table);

            String key = table.getMetadata().getName();

            map.put(key, table);
        }
        this.tables = map;
    }

}
