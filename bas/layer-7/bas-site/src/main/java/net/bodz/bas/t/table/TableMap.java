package net.bodz.bas.t.table;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.IElements;
import net.bodz.bas.json.JsonObject;

public class TableMap
        implements
            ITableMap {

    ITableMapMetadata metadata;
    Map<String, ITable> tables = createMap();

    public TableMap(ITableMapMetadata metadata) {
        if (metadata == null)
            throw new NullPointerException("metadata");
        this.metadata = metadata;
    }

    protected Map<String, ITable> createMap() {
        return new LinkedHashMap<>();
    }

    public static TableMap wrap(ITableMapMetadata metadata, Map<String, ITable> tables) {
        TableMap a = new TableMap(metadata);
        a.tables = tables;
        return a;
    }

    @Override
    public ITableMapMetadata getMetadata() {
        return metadata;
    }

    @Override
    public Map<String, ITable> getTables() {
        return tables;
    }

    public void addTable(MutableTable o) {
        if (o == null)
            throw new NullPointerException("table");

        if (o.getParent() != null)
            throw new IllegalStateException("Already attached to another parent");

        ISchemaMetadata oMetadataParent = o.getMetadata().getParent();
        if (oMetadataParent != getMetadata())
            throw new IllegalArgumentException("Conflict metadata");

        o.setParent(this);
        String oName = o.getMetadata().getName();
        tables.put(oName, o);
    }

    @Override
    public Iterator<ITable> iterator() {
        return tables.values().iterator();
    }

    protected ITableMapMetadata newMetadata() {
        return new DefaultTableMapMetadata();
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        JsonObject j_md = o.getJsonObject(K_METADATA);
        if (j_md != null) {
            ITableMapMetadata metadata = newMetadata();
            metadata.readObject(j_md);
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
            table.readObjectBoxed(j_table);
            map.put(key, table);
        }
        this.tables = map;
    }

    @Override
    public void readObject(IElement x_schema)
            throws ParseException, LoaderException {
        IElement x_md = x_schema.selectByTag(K_METADATA).getFirst();
        if (x_md != null && x_md.getParentNode() == x_schema) {
            ITableMapMetadata metadata = newMetadata();
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

        // DefaultTableMapMetadata

        this.tables = map;
    }

}
