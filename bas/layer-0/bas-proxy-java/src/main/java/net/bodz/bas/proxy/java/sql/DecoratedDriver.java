package net.bodz.bas.proxy.java.sql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedDriver
        extends AbstractDecorator<Driver>
        implements Driver {

    private static final long serialVersionUID = 1L;

    public DecoratedDriver(Driver _orig) {
        super(_orig);
    }

    @Override
    public Connection connect(String url, Properties info)
            throws SQLException {
        return _orig.connect(url, info);
    }

    @Override
    public boolean acceptsURL(String url)
            throws SQLException {
        return _orig.acceptsURL(url);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
            throws SQLException {
        return _orig.getPropertyInfo(url, info);
    }

    @Override
    public int getMajorVersion() {
        return _orig.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return _orig.getMinorVersion();
    }

    @Override
    public boolean jdbcCompliant() {
        return _orig.jdbcCompliant();
    }

    @Override
    public Logger getParentLogger()
            throws SQLFeatureNotSupportedException {
        return _orig.getParentLogger();
    }

}
