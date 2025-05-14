package net.bodz.bas.db.jdbc.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.bodz.bas.t.iterator.PrefetchedIterator;

public class ResultRowIterator
        extends PrefetchedIterator<ResultRow> {

    ResultSet rs;

    public ResultRowIterator(ResultSet rs) {
        if (rs == null)
            throw new NullPointerException("rs");
        this.rs = rs;
    }

    @Override
    protected ResultRow fetch() {
        try {
            if (rs.next())
                return new ResultRow(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return end();
    }

}
