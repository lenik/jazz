package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.map.ListMap;

public class DefaultSchemaMetadata
        implements
            IMutableSchemaMetadata {

    static final Logger logger = LoggerFactory.getLogger(DefaultSchemaMetadata.class);

    QualifiedSchemaName qName = new QualifiedSchemaName();
    QualifiedSchemaName defaultName = new QualifiedSchemaName();

    String label;
    String description;

    ICatalogMetadata parent;

    Map<String, ITableMetadata> tables = new LinkedHashMap<>();
    Boolean convertToUpperCase;
    Map<String, String> canonicalNames = new HashMap<>();

    public DefaultSchemaMetadata() {
    }

    public DefaultSchemaMetadata(ICatalogMetadata parent) {
        this.parent = parent;
    }

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

    @Override
    public ICatalogMetadata getParent() {
        return parent;
    }

    @Override
    public void setParent(ICatalogMetadata parent) {
        this.parent = parent;
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

    public ITableMetadata getTable(QualifiedTableName qName) {
        return tables.get(qName.getTableName());
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

    @Override
    public void addTable(ITableMetadata table) {
        if (table == null)
            throw new NullPointerException("table");
        String name = table.getName();
        ITableMetadata existing = tables.get(name);
        if (existing != null)
            throw new DuplicatedKeyException("Table already existed: " + name);
        tables.put(name, table);
    }

    @Override
    public boolean removeTable(ITableMetadata table) {
        return removeTable(table.getName());
    }

    @Override
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
        return "schema " + getCompactName() + "(" + getTableNames() + ")";
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
        Map<String, ITableMetadata> tables = new LinkedHashMap<>();
        for (IElement x_table : x_tables.children()) {
            assert x_table.getTagName().equals(K_TABLE);
            DefaultTableMetadata table = new DefaultTableMetadata();
            table.readObject(x_table);
            String key = table.getName();
            tables.put(key, table);
        }
        this.tables = tables;
    }

    class MetaDataHandler
            implements
                IJDBCMetaDataHandler {

        @Override
        public void schema(ResultSet rs)
                throws SQLException {
            String catalogName = rs.getString("TABLE_CATALOG");
            String schemaName = rs.getString("TABLE_SCHEM");
            qName.assign(catalogName, schemaName); // correct char case.
        }

        @Override
        public void table(ResultSet rs)
                throws SQLException {
            DefaultTableMetadata table = new DefaultTableMetadata(DefaultSchemaMetadata.this);
            table.getJDBCMetaDataHandler().table(rs);
            addTable(table);
        }

        @Override
        public void column(ResultSet rs)
                throws SQLException {
            QualifiedTableName qName = new QualifiedTableName();
            qName.readFromJDBC(rs);
            ITableMetadata table = getTable(qName.getTableName());
            if (table == null)
                throw new UnexpectedException("Detected new table when scanning: " + qName);
            table.getJDBCMetaDataHandler().column(rs);
        }

        @Override
        public void primaryKey(ITableMetadata table, TableKey primaryKey)
                throws SQLException {
            table.getJDBCMetaDataHandler().primaryKey(table, primaryKey);
        }

        @Override
        public void crossReference(ITableMetadata table, CrossReference crossRef)
                throws SQLException {
        }

    }

    @Override
    public MetaDataHandler getJDBCMetaDataHandler() {
        return new MetaDataHandler();
    }

    public void loadFromJDBC(Connection connection, String... types)
            throws SQLException {
        DatabaseMetaData dmd = connection.getMetaData();
        MetaDataHandler handler = new MetaDataHandler();
        ResultSet rs;
        QualifiedSchemaName qName = getQName();

        // Parse from schema's metadata
        rs = dmd.getSchemas(qName.catalogName, qName.schemaName);
        while (rs.next()) {
            handler.schema(rs);
            break;
        }
        rs.close();

        rs = dmd.getTables(qName.catalogName, qName.schemaName, null, types);
        while (rs.next())
            handler.table(rs);
        rs.close();

        rs = dmd.getColumns(qName.catalogName, qName.schemaName, null, null);
        while (rs.next())
            handler.column(rs);
        rs.close();

        rs = dmd.getPrimaryKeys(qName.catalogName, qName.schemaName, null);
        Map<QualifiedTableName, TableKey> pkmap = TableKey.convertFromJDBC(rs);
        for (QualifiedTableName tableQName : pkmap.keySet()) {
            ITableMetadata table = getTable(tableQName);
            TableKey primaryKey = pkmap.get(tableQName);
            handler.primaryKey(table, primaryKey);
        }
        rs.close();

        rs = dmd.getCrossReference(qName.catalogName, qName.schemaName, null, //
                qName.catalogName, qName.schemaName, null);
        ListMap<QualifiedTableName, CrossReference> fMap = CrossReference.convertToForeignMap(rs);
        for (QualifiedTableName foreignName : fMap.keySet()) {
            ITableMetadata foreignTable = getTable(foreignName.getTableName());

            List<CrossReference> crossRefs = fMap.get(foreignName);
            for (CrossReference crossRef : crossRefs) {
                handler.crossReference(foreignTable, crossRef);
            }
        }
        rs.close();
    }

}
