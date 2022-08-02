package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DefaultDatabaseMetadata {

    // QName => data
    Map<String, ITableMetadata> tables = new HashMap<>();

    public ITableMetadata resolveTable(Connection cn, String fullName)
            throws SQLException {
        ITableMetadata cached = tables.get(fullName);
        if (cached == null) {
            DefaultTableMetadata metadata = new DefaultTableMetadata();
            QualifiedTableName qName = QualifiedTableName.fromFullName(fullName);
            metadata.setQName(qName);
            metadata.loadFromJDBC(cn);
            tables.put(fullName, metadata);
            cached = metadata;
        }
        return cached;
    }

    public ITableMetadata resolveTable(Connection cn, String catalogName, String schemaName, String tableName)
            throws SQLException {
        String qName = new QualifiedTableName(catalogName, schemaName, tableName).getFullName();
        return resolveTable(cn, qName);
    }

    static DefaultDatabaseMetadata instance = new DefaultDatabaseMetadata();

    public static DefaultDatabaseMetadata getInstance() {
        return instance;
    }

}
