package net.bodz.bas.proxy.java.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

public class PreparedStatementDecorator
        extends StatementDecorator
        implements PreparedStatement {

    private static final long serialVersionUID = 1L;

    public PreparedStatementDecorator(PreparedStatement _orig) {
        super(_orig);
    }

    @Override
    public PreparedStatement getWrapped() {
        return (PreparedStatement) super.getWrapped();
    }

    @Override
    public ResultSet executeQuery()
            throws SQLException {
        return getWrapped().executeQuery();
    }

    @Override
    public int executeUpdate()
            throws SQLException {
        return getWrapped().executeUpdate();
    }

    @Override
    public void setNull(int parameterIndex, int sqlType)
            throws SQLException {
        getWrapped().setNull(parameterIndex, sqlType);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x)
            throws SQLException {
        getWrapped().setBoolean(parameterIndex, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x)
            throws SQLException {
        getWrapped().setByte(parameterIndex, x);
    }

    @Override
    public void setShort(int parameterIndex, short x)
            throws SQLException {
        getWrapped().setShort(parameterIndex, x);
    }

    @Override
    public void setInt(int parameterIndex, int x)
            throws SQLException {
        getWrapped().setInt(parameterIndex, x);
    }

    @Override
    public void setLong(int parameterIndex, long x)
            throws SQLException {
        getWrapped().setLong(parameterIndex, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x)
            throws SQLException {
        getWrapped().setFloat(parameterIndex, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x)
            throws SQLException {
        getWrapped().setDouble(parameterIndex, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x)
            throws SQLException {
        getWrapped().setBigDecimal(parameterIndex, x);
    }

    @Override
    public void setString(int parameterIndex, String x)
            throws SQLException {
        getWrapped().setString(parameterIndex, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x)
            throws SQLException {
        getWrapped().setBytes(parameterIndex, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x)
            throws SQLException {
        getWrapped().setDate(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x)
            throws SQLException {
        getWrapped().setTime(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x)
            throws SQLException {
        getWrapped().setTimestamp(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length)
            throws SQLException {
        getWrapped().setAsciiStream(parameterIndex, x, length);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length)
            throws SQLException {
        getWrapped().setUnicodeStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length)
            throws SQLException {
        getWrapped().setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void clearParameters()
            throws SQLException {
        getWrapped().clearParameters();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType)
            throws SQLException {
        getWrapped().setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x)
            throws SQLException {
        getWrapped().setObject(parameterIndex, x);
    }

    @Override
    public boolean execute()
            throws SQLException {
        return getWrapped().execute();
    }

    @Override
    public void addBatch()
            throws SQLException {
        getWrapped().addBatch();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length)
            throws SQLException {
        getWrapped().setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setRef(int parameterIndex, Ref x)
            throws SQLException {
        getWrapped().setRef(parameterIndex, x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x)
            throws SQLException {
        getWrapped().setBlob(parameterIndex, x);
    }

    @Override
    public void setClob(int parameterIndex, Clob x)
            throws SQLException {
        getWrapped().setClob(parameterIndex, x);
    }

    @Override
    public void setArray(int parameterIndex, Array x)
            throws SQLException {
        getWrapped().setArray(parameterIndex, x);
    }

    @Override
    public ResultSetMetaData getMetaData()
            throws SQLException {
        return getWrapped().getMetaData();
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal)
            throws SQLException {
        getWrapped().setDate(parameterIndex, x, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal)
            throws SQLException {
        getWrapped().setTime(parameterIndex, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
            throws SQLException {
        getWrapped().setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName)
            throws SQLException {
        getWrapped().setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    public void setURL(int parameterIndex, URL x)
            throws SQLException {
        getWrapped().setURL(parameterIndex, x);
    }

    @Override
    public ParameterMetaData getParameterMetaData()
            throws SQLException {
        return getWrapped().getParameterMetaData();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x)
            throws SQLException {
        getWrapped().setRowId(parameterIndex, x);
    }

    @Override
    public void setNString(int parameterIndex, String value)
            throws SQLException {
        getWrapped().setNString(parameterIndex, value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length)
            throws SQLException {
        getWrapped().setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value)
            throws SQLException {
        getWrapped().setNClob(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length)
            throws SQLException {
        getWrapped().setClob(parameterIndex, reader, length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length)
            throws SQLException {
        getWrapped().setBlob(parameterIndex, inputStream, length);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length)
            throws SQLException {
        getWrapped().setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject)
            throws SQLException {
        getWrapped().setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength)
            throws SQLException {
        getWrapped().setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length)
            throws SQLException {
        getWrapped().setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length)
            throws SQLException {
        getWrapped().setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length)
            throws SQLException {
        getWrapped().setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x)
            throws SQLException {
        getWrapped().setAsciiStream(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x)
            throws SQLException {
        getWrapped().setBinaryStream(parameterIndex, x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader)
            throws SQLException {
        getWrapped().setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value)
            throws SQLException {
        getWrapped().setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader)
            throws SQLException {
        getWrapped().setClob(parameterIndex, reader);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream)
            throws SQLException {
        getWrapped().setBlob(parameterIndex, inputStream);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader)
            throws SQLException {
        getWrapped().setNClob(parameterIndex, reader);
    }

}
