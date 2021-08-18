package net.bodz.bas.t.table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtil {

    Connection connection;

    public JdbcUtil(Connection connection) {
        this.connection = connection;
    }

    public static String escape(String qName) {
        String escaped = "\"" + qName.replace(".", "\".\"") + "\"";
        return escaped;
    }

    public DefaultTableMetadata getTableMetadata(String tableName)
            throws SQLException {
        Statement statement = connection.createStatement();

        DefaultTableMetadata metadata;
        ResultSet rs = statement.executeQuery(//
                "select * from " + escape(tableName) + " where 1=2");

        metadata = new DefaultTableMetadata();
        metadata.setName(tableName);
        metadata.readObject(rs.getMetaData());
        rs.close();

        String schema = null;
        String tab = tableName;
        int dot = tableName.indexOf('.');
        if (dot != -1) {
            schema = tableName.substring(0, dot);
            tab = tableName.substring(dot + 1);
        }
        rs = connection.getMetaData().getPrimaryKeys(null, schema, tab);
        List<String> cols = new ArrayList<>();
        while (rs.next()) {
            String pkColumnName = rs.getString("COLUMN_NAME");
            cols.add(pkColumnName);
        }
        String[] primaryKey = cols.toArray(new String[0]);
        metadata.setPrimaryKey(primaryKey);

        return metadata;
    }

}
