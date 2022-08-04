package net.bodz.bas.t.catalog;

import java.util.HashMap;
import java.util.Map;

public class SchemaSubset {

    public String schemaName;

    public Map<String, Boolean> tableNames = new HashMap<>();
    static final Map<String, Boolean> ALL_TABLES = null;

    public SchemaSubset(String schemaName) {
        this.schemaName = schemaName;
    }

    public boolean isAll() {
        return tableNames == ALL_TABLES;
    }

    public boolean isEmpty() {
        if (tableNames == ALL_TABLES)
            return false;
        return tableNames.isEmpty();
    }

    public boolean contains(String tableName) {
        if (tableName == null)
            throw new NullPointerException("tableName");
        if (tableNames == ALL_TABLES)
            return true;
        Boolean any = tableNames.get(tableName);
        if (tableNames != null && any != null)
            return true;
        return false;
    }

    public void addAllTables() {
        tableNames = ALL_TABLES;
    }

    public boolean addTable(String tableName) {
        if (tableName == null)
            throw new NullPointerException("tableName");
        if (tableNames == ALL_TABLES)
            return false;
        Boolean old = tableNames.put(tableName, Boolean.TRUE);
        return old == null;
    }

    @Override
    public String toString() {
        if (tableNames == ALL_TABLES)
            return "\\ALL";
        else
            return tableNames.keySet().toString();
    }

}
