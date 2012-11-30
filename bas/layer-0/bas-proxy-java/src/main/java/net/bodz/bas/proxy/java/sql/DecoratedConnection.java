package net.bodz.bas.proxy.java.sql;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import net.bodz.bas.model.IWrapper;

public class DecoratedConnection
        extends AbstractJavasqlWrapper<Connection>
        implements IWrapper<Connection>, Connection {

    private static final long serialVersionUID = 1L;

    Connection _orig;

    public DecoratedConnection(Connection _orig) {
        super(_orig);
    }

    @Override
    protected <_T> _T _unwrap(Class<_T> iface)
            throws SQLException {
        return _orig.unwrap(iface);
    }

    @Override
    protected boolean _isWrapperFor(Class<?> iface)
            throws SQLException {
        return _orig.isWrapperFor(iface);
    }

    @Override
    public Statement createStatement()
            throws SQLException {
        return _orig.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql)
            throws SQLException {
        return _orig.prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql)
            throws SQLException {
        return _orig.prepareCall(sql);
    }

    @Override
    public String nativeSQL(String sql)
            throws SQLException {
        return _orig.nativeSQL(sql);
    }

    @Override
    public void setAutoCommit(boolean autoCommit)
            throws SQLException {
        _orig.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit()
            throws SQLException {
        return _orig.getAutoCommit();
    }

    @Override
    public void commit()
            throws SQLException {
        _orig.commit();
    }

    @Override
    public void rollback()
            throws SQLException {
        _orig.rollback();
    }

    @Override
    public void close()
            throws SQLException {
        _orig.close();
    }

    @Override
    public boolean isClosed()
            throws SQLException {
        return _orig.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData()
            throws SQLException {
        return _orig.getMetaData();
    }

    @Override
    public void setReadOnly(boolean readOnly)
            throws SQLException {
        _orig.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly()
            throws SQLException {
        return _orig.isReadOnly();
    }

    @Override
    public void setCatalog(String catalog)
            throws SQLException {
        _orig.setCatalog(catalog);
    }

    @Override
    public String getCatalog()
            throws SQLException {
        return _orig.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int level)
            throws SQLException {
        _orig.setTransactionIsolation(level);
    }

    @Override
    public int getTransactionIsolation()
            throws SQLException {
        return _orig.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings()
            throws SQLException {
        return _orig.getWarnings();
    }

    @Override
    public void clearWarnings()
            throws SQLException {
        _orig.clearWarnings();
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency)
            throws SQLException {
        return _orig.createStatement(resultSetType, resultSetConcurrency);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
            throws SQLException {
        return _orig.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
            throws SQLException {
        return _orig.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public Map<String, Class<?>> getTypeMap()
            throws SQLException {
        return _orig.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map)
            throws SQLException {
        _orig.setTypeMap(map);
    }

    @Override
    public void setHoldability(int holdability)
            throws SQLException {
        _orig.setHoldability(holdability);
    }

    @Override
    public int getHoldability()
            throws SQLException {
        return _orig.getHoldability();
    }

    @Override
    public Savepoint setSavepoint()
            throws SQLException {
        return _orig.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name)
            throws SQLException {
        return _orig.setSavepoint(name);
    }

    @Override
    public void rollback(Savepoint savepoint)
            throws SQLException {
        _orig.rollback(savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint)
            throws SQLException {
        _orig.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return _orig.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
            int resultSetHoldability)
            throws SQLException {
        return _orig.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
            int resultSetHoldability)
            throws SQLException {
        return _orig.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
            throws SQLException {
        return _orig.prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
            throws SQLException {
        return _orig.prepareStatement(sql, columnIndexes);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames)
            throws SQLException {
        return _orig.prepareStatement(sql, columnNames);
    }

    @Override
    public Clob createClob()
            throws SQLException {
        return _orig.createClob();
    }

    @Override
    public Blob createBlob()
            throws SQLException {
        return _orig.createBlob();
    }

    @Override
    public NClob createNClob()
            throws SQLException {
        return _orig.createNClob();
    }

    @Override
    public SQLXML createSQLXML()
            throws SQLException {
        return _orig.createSQLXML();
    }

    @Override
    public boolean isValid(int timeout)
            throws SQLException {
        return _orig.isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value)
            throws SQLClientInfoException {
        _orig.setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(Properties properties)
            throws SQLClientInfoException {
        _orig.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name)
            throws SQLException {
        return _orig.getClientInfo(name);
    }

    @Override
    public Properties getClientInfo()
            throws SQLException {
        return _orig.getClientInfo();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements)
            throws SQLException {
        return _orig.createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes)
            throws SQLException {
        return _orig.createStruct(typeName, attributes);
    }

    @Override
    public void setSchema(String schema)
            throws SQLException {
        _orig.setSchema(schema);
    }

    @Override
    public String getSchema()
            throws SQLException {
        return _orig.getSchema();
    }

    @Override
    public void abort(Executor executor)
            throws SQLException {
        _orig.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds)
            throws SQLException {
        _orig.setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout()
            throws SQLException {
        return _orig.getNetworkTimeout();
    }

}
