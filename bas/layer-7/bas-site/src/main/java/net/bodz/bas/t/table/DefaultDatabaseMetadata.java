package net.bodz.bas.t.table;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DefaultDatabaseMetadata {

    // QName => data
    Map<String, ITableMetadata> tables = new HashMap<>();

    public ITableMetadata resolveTable(Connection cn, String qName)
            throws SQLException {
        ITableMetadata cached = tables.get(qName);
        if (cached == null) {
            DefaultTableMetadata metadata = new DefaultTableMetadata(qName);
            metadata.readObject(cn);
            tables.put(qName, metadata);
            cached = metadata;
        }
        return cached;
    }

    public ITableMetadata resolveTable(Connection cn, String catalogName, String schemaName, String tableName)
            throws SQLException {
        String qName = new QualifiedTableName(catalogName, schemaName, tableName).getQualifiedName();
        return resolveTable(cn, qName);
    }

    static DefaultDatabaseMetadata instance = new DefaultDatabaseMetadata();

    public static DefaultDatabaseMetadata getInstance() {
        return instance;
    }

}
