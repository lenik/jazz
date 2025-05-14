package net.bodz.bas.db.jdbc.util;

import java.sql.ResultSet;
import java.util.Iterator;

import net.bodz.bas.meta.decl.NotNull;

public class ResultRows
        implements Iterable<ResultRow> {

    ResultSet rs;

    public ResultRows(ResultSet rs) {
        if (rs == null)
            throw new NullPointerException("rs");
        this.rs = rs;
    }

    @NotNull
    @Override
    public Iterator<ResultRow> iterator() {
        return new ResultRowIterator(rs);
    }

}
