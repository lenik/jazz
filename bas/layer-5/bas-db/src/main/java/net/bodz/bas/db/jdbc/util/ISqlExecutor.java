package net.bodz.bas.db.jdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.bodz.bas.t.variant.IVariant;
import net.bodz.bas.t.variant.IVariantMap;

public interface ISqlExecutor
        extends Statement {

    Connection getConnection();

    boolean isConnectionManaged();

    Statement getStatement();

    boolean isStatementManaged();

    int update(String sql)
            throws SQLException;

    ResultSet query(String sql)
            throws SQLException;

    long count(String sql)
            throws SQLException;

    /**
     * @param row
     *            1-based.
     */
    IVariantMap<Object> row(String sql, int row)
            throws SQLException;

    /**
     * @param row
     *            1-based.
     * @param column
     *            1-based.
     */
    IVariant cell(String sql, int row, int column)
            throws SQLException;

    /**
     * @param row
     *            1-based.
     */
    IVariant cell(String sql, int row, String columnName)
            throws SQLException;

}
