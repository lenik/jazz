package net.bodz.inplas.kid;

import java.sql.SQLException;

import net.sf.freejava.sql.Connection;
import net.sf.freejava.sql.ConnectionProvider;
import net.sf.freejava.sql.Diagnostic;
import net.sf.freejava.sql.dialect.Dialect;
import net.sf.freejava.sql.dialect.dbms.MSSQL;

public class Config {

    // Not used.
    public String              dialect;

    public String              server;
    public String              database;
    public String              user;
    public String              password;

    private ConnectionProvider provider;

    /**
     * Connection for getDialect purpose
     */
    private Connection         dconn;

    public Connection getConnection() throws SQLException {
        if (provider == null)
            provider = MSSQL.CM_jtds.lazyConnect(server, database, user,
                    password);
        return provider.getConnection();
    }

    public Dialect getDialect() throws SQLException {
        if (!Diagnostic.checkConnection(dconn))
            dconn = getConnection();
        return dconn.getDialect();
    }

}
