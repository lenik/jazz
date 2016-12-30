package net.bodz.bas.db.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SharedSqlExecutor
        extends AbstractSqlExecutor {

    private static final long serialVersionUID = 1L;

    Integer preferredResultSetType;
    Integer preferredResultSetConcurrency;
    Integer preferredResultSetHoldability;

    public SharedSqlExecutor(Connection connection) {
        super(connection, false, null, true);
    }

    @Override
    protected Statement createStatement()
            throws SQLException {
        Connection connection = getConnection();
        if (preferredResultSetType == null || preferredResultSetConcurrency == null)
            return connection.createStatement();
        if (preferredResultSetHoldability == null)
            return connection.createStatement(preferredResultSetType, preferredResultSetConcurrency);
        else
            return connection.createStatement(preferredResultSetType, preferredResultSetConcurrency,
                    preferredResultSetHoldability);
    }

}
