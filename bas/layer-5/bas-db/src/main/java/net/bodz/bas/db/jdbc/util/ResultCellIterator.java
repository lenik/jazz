package net.bodz.bas.db.jdbc.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ResultCellIterator
        implements Iterator<ResultCell> {

    ResultSet rs;

    int i = 1;
    int columnCount;

    public ResultCellIterator(ResultSet rs)
            throws SQLException {
        this.rs = rs;
        this.columnCount = rs.getMetaData().getColumnCount();
    }

    @Override
    public boolean hasNext() {
        return i <= columnCount;
    }

    @Override
    public ResultCell next() {
        try {
            Object obj = rs.getObject(i);
            i++;
            return new ResultCell(obj);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void remove() {
        try {
            int lastColumn = i - 1;
            if (lastColumn >= 1 && lastColumn <= columnCount)
                rs.updateObject(lastColumn, null);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
