package net.bodz.bas.db.jdbc.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ResultRow
        implements Iterable<ResultCell> {

    ResultSet rs;

    public ResultRow(ResultSet rs) {
        if (rs == null)
            throw new NullPointerException("rs");
        this.rs = rs;
    }

    @Override
    public Iterator<ResultCell> iterator() {
        try {
            return new ResultCellIterator(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
