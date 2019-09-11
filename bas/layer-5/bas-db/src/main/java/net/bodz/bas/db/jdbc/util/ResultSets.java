package net.bodz.bas.db.jdbc.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ResultSets {

    public static ResultRows iterate(ResultSet rs) {
        if (rs == null)
            throw new NullPointerException("rs");
        return new ResultRows(rs);
    }

    public static Map<Object, Object> toMap(ResultSet rs, boolean indexKeys, boolean nameKeys)
            throws SQLException {
        Map<Object, Object> map = new HashMap<Object, Object>();
        ResultSetMetaData metaData = rs.getMetaData();
        int cc = metaData.getColumnCount();

        for (int i = 1; i <= cc; i++) {
            Object cell = rs.getObject(i);
            if (indexKeys)
                map.put(i, cell);
            if (nameKeys)
                map.put(metaData.getCatalogName(i), cell);
        }
        return map;
    }

}
