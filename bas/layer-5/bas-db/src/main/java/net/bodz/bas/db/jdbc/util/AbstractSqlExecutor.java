package net.bodz.bas.db.jdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import net.bodz.bas.proxy.java.sql.DecoratedStatement;
import net.bodz.bas.t.variant.IVariant;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.MutableVariant;
import net.bodz.bas.t.variant.MutableVariantMap;

public abstract class AbstractSqlExecutor
        extends DecoratedStatement
        implements ISqlExecutor {

    private static final long serialVersionUID = 1L;

    private Connection connection;
    private boolean connectionManaged;
    private boolean statementManaged;

    public AbstractSqlExecutor(Connection connection, boolean connectionManaged, Statement statement,
            boolean statementManaged) {
        super(statement);
        this.connection = connection;
        this.connectionManaged = connectionManaged;
        this.statementManaged = statementManaged;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean isConnectionManaged() {
        return connectionManaged;
    }

    @Override
    public Statement getStatement() {
        return getWrapped();
    }

    @Override
    public boolean isStatementManaged() {
        return statementManaged;
    }

    protected abstract Statement createStatement()
            throws SQLException;

    protected Statement openStatement()
            throws SQLException {
        if (statementManaged)
            return createStatement();
        else
            return getStatement();
    }

    void close(Statement statement)
            throws SQLException {
        if (statementManaged)
            statement.close();
    }

    @Override
    public int update(String sql)
            throws SQLException {
        Statement statement = openStatement();
        try {
            int r = statement.executeUpdate(sql);
            return r;
        } finally {
            close(statement);
        }
    }

    @Override
    public ResultSet query(String sql)
            throws SQLException {
        Statement statement = openStatement();
        return statement.executeQuery(sql);
    }

    @Override
    public long count(String sql)
            throws SQLException {
        Statement statement = openStatement();
        try {
            long count = 0;
            ResultSet rs = query(sql);
            try {
                while (rs.next())
                    count++;
            } finally {
                rs.close();
            }
            return count;
        } finally {
            close(statement);
        }
    }

    @Override
    public IVariantMap<Object> row(String sql, int row)
            throws SQLException {
        if (row < 1)
            throw new IllegalArgumentException("Bad row number, must be positive: " + row);
        Statement statement = openStatement();
        try {
            long count = 0;
            ResultSet rs = query(sql);
            try {
                while (rs.next()) {
                    count++;
                    if (count == row) {
                        Map<Object, Object> map = ResultSets.toMap(rs, true, true);
                        return MutableVariantMap.fromMap(map);
                    }
                }
                return null;
            } finally {
                rs.close();
            }
        } finally {
            close(statement);
        }
    }

    @Override
    public IVariant cell(String sql, int row, int column)
            throws SQLException {
        IVariantMap<Object> vmap = row(sql, row);
        if (vmap == null)
            return null;
        if (!vmap.containsKey(column))
            return null;
        Object val = vmap.get(column);
        return MutableVariant.wrap(val);
    }

    @Override
    public IVariant cell(String sql, int row, String columnName)
            throws SQLException {
        IVariantMap<Object> vmap = row(sql, row);
        if (vmap == null)
            return null;
        if (!vmap.containsKey(columnName))
            return null;
        Object val = vmap.get(columnName);
        return MutableVariant.wrap(val);
    }

}
