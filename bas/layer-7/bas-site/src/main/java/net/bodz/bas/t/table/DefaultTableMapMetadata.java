package net.bodz.bas.t.table;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.IElements;
import net.bodz.bas.json.JsonObject;

public class DefaultTableMapMetadata
        extends QualifiedSchemaName
        implements
            ITableMapMetadata {

    private static final long serialVersionUID = 1L;

    Map<String, ITableMetadata> tables = new LinkedHashMap<>();
    Boolean convertToUpperCase;
    Map<String, String> canonicalNames = new HashMap<>();

    public DefaultTableMapMetadata() {
        super();
    }

    public DefaultTableMapMetadata(String catalogName, String schemaName) {
        super(catalogName, schemaName);
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
    public Map<String, ITableMetadata> getTables() {
        return tables;
    }

    @Override
    public ITableMetadata getTable(String name) {
        return tables.get(name);
    }

    @Override
    public Iterator<ITableMetadata> iterator() {
        return tables.values().iterator();
    }

    @Override
    public int getTableCount() {
        return tables.size();
    }

    public synchronized void addTable(ITableMetadata table) {
        if (table == null)
            throw new NullPointerException("table");
        String name = table.getName();
        tables.put(name, table);
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        JsonObject jm = o.getJsonObject(K_TABLES);
        Map<String, ITableMetadata> tables = new LinkedHashMap<>();
        for (String key : jm.keySet()) {
            JsonObject item = jm.getJsonObject(key);
            DefaultTableMetadata table = new DefaultTableMetadata();
            table.readObject(item);
            tables.put(key, table);
        }
        this.tables = tables;
    }

    @Override
    public void readObject(IElement x_metadata)
            throws ParseException, LoaderException {
        IElement x_tables = x_metadata.selectByTag(K_TABLES).first();
        IElements x_table_v = x_tables.children();
        int n = x_table_v.getElementCount();
        Map<String, ITableMetadata> tables = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            IElement x_table = x_table_v.get(i);
            assert x_table.getTagName().equals(K_TABLE);
            DefaultTableMetadata table = new DefaultTableMetadata();
            table.readObject(x_table);
            String key = table.getName();
            tables.put(key, table);
        }
        this.tables = tables;
    }

    public String getTableNames() {
        StringBuilder sb = new StringBuilder(tables.size() * 16);
        for (int i = 0; i < tables.size(); i++) {
            if (i != 0)
                sb.append(", ");
            ITableMetadata table = tables.get(i);
            sb.append(table.getName());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getTableNames();
    }

}
