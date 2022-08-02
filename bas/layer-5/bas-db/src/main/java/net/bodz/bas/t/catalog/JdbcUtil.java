package net.bodz.bas.t.catalog;

import java.sql.Connection;

public class JdbcUtil {

    Connection connection;

    public JdbcUtil(Connection connection) {
        this.connection = connection;
    }

    public static String escape(String qName) {
        String escaped = "\"" + qName.replace(".", "\".\"") + "\"";
        return escaped;
    }

}
