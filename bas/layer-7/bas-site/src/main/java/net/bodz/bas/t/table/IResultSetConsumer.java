package net.bodz.bas.t.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetConsumer {

    /**
     * @param limit
     *            Max number of rows can be consumed. <code>null</code> if unlimited.
     * @return Number of rows consumed.
     */
    long consume(ResultSet rs, Long limit)
            throws SQLException;

    default long consume(ResultSet rs)
            throws SQLException {
        return consume(rs, null);
    }

}
