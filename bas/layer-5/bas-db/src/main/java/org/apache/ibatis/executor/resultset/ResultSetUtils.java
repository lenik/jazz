package org.apache.ibatis.executor.resultset;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetUtils {

    public static boolean hasColumn(ResultSet rs, String name)
            throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int n = metaData.getColumnCount();
        for (int i = 1; i <= n; i++) {
            String columnName = metaData.getColumnName(i);
            if (columnName.equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

}
