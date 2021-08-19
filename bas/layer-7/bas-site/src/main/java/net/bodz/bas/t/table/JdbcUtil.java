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

        DefaultTableMetadata table;
        ResultSet rs = statement.executeQuery(//
                "select * from " + escape(tableName) + " where 1=2");

        table = new DefaultTableMetadata();
        table.setName(tableName);
        table.readObject(rs.getMetaData());
        rs.close();

        String schema = null;
        String tab = tableName;
        int dot = tableName.indexOf('.');
        if (dot != -1) {
            schema = tableName.substring(0, dot);
            tab = tableName.substring(dot + 1);
        }
        rs = connection.getMetaData().getPrimaryKeys(null, schema, tab);
        List<String> pkColumnNames = new ArrayList<>();
        while (rs.next()) {
            String pkColumnName = rs.getString("COLUMN_NAME");
            pkColumnNames.add(pkColumnName);

            DefaultColumnMetadata column = (DefaultColumnMetadata) table.getColumn(pkColumnName);
            column.setPrimaryKey(true);
        }

        String[] primaryKey = pkColumnNames.toArray(new String[0]);
        table.setPrimaryKey(primaryKey);
        return table;
    }

}
