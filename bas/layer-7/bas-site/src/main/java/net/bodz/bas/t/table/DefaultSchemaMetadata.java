package net.bodz.bas.t.table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.IElements;
import net.bodz.bas.json.JsonObject;

public class DefaultSchemaMetadata
        implements
            ISchemaMetadata {

    QualifiedSchemaName qName;
    QualifiedSchemaName defaultName = new QualifiedSchemaName();

    String label;
    String description;

    Map<String, ITableMetadata> tables = new LinkedHashMap<>();
    Boolean convertToUpperCase;
    Map<String, String> canonicalNames = new HashMap<>();

    @Override
    public QualifiedSchemaName getQName() {
        return qName;
    }

    public void setQName(QualifiedSchemaName qName) {
        this.qName = qName;
    }

    @Override
    public QualifiedSchemaName getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(QualifiedSchemaName defaultName) {
        if (defaultName == null)
            throw new NullPointerException("defaultName");
        this.defaultName = defaultName;
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

    /**
     * @throws DuplicatedKeyException
     *             When table with same name was existed.
     */
    public void addTable(ITableMetadata table) {
        if (table == null)
            throw new NullPointerException("table");
        String name = table.getName();
        ITableMetadata existing = tables.get(name);
        if (existing != null)
            throw new DuplicatedKeyException("Table already existed: " + name);
        tables.put(name, table);
    }

    public boolean removeTable(ITableMetadata table) {
        return removeTable(table.getName());
    }

    public boolean removeTable(String tableName) {
        ITableMetadata table = tables.remove(tableName);
        return table != null;
    }

    String getTableNames() {
        StringBuilder sb = new StringBuilder(tables.size() * 16);
        for (String key : tables.keySet()) {
            if (sb.length() != 0)
                sb.append(", ");
            ITableMetadata table = tables.get(key);
            sb.append(table.getName());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getCompactName() + "(" + getTableNames() + ")";
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        getQName().readObject(o);

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

    public void loadFromDatabase(Connection connection)
            throws SQLException {
        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs;
        QualifiedSchemaName qName = getQName();

        // Parse from schema's metadata
        rs = dmd.getSchemas(qName.catalogName, qName.schemaName);
        while (rs.next()) {
            String catalogName = rs.getString("schema_cat");
            String schemaName = rs.getString("schema_name");
            qName.assign(catalogName, schemaName); // correct char case.
            break;
        }
        rs.close();

        rs = dmd.getTables(qName.catalogName, qName.schemaName, null, null);
        List<String> tableNames = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("table_name");
            tableNames.add(name);
        }
        rs.close();

        for (String tableName : tableNames) {
            DefaultTableMetadata table = new DefaultTableMetadata();
            table.getQName().assign(qName.catalogName, qName.schemaName, tableName);
            table.loadFromJDBC(connection);
            addTable(table);
        }
        rs.close();
    }

}
