package net.bodz.bas.proxy.java.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

public class DecoratedCallableStatement
        extends DecoratedPreparedStatement
        implements CallableStatement {

    private static final long serialVersionUID = 1L;

    public DecoratedCallableStatement(CallableStatement _orig) {
        super(_orig);
    }

    @Override
    public CallableStatement getWrapped() {
        return (CallableStatement) super.getWrapped();
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType)
            throws SQLException {
        getWrapped().registerOutParameter(parameterIndex, sqlType);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, int scale)
            throws SQLException {
        getWrapped().registerOutParameter(parameterIndex, sqlType, scale);
    }

    @Override
    public boolean wasNull()
            throws SQLException {
        return getWrapped().wasNull();
    }

    @Override
    public String getString(int parameterIndex)
            throws SQLException {
        return getWrapped().getString(parameterIndex);
    }

    @Override
    public boolean getBoolean(int parameterIndex)
            throws SQLException {
        return getWrapped().getBoolean(parameterIndex);
    }

    @Override
    public byte getByte(int parameterIndex)
            throws SQLException {
        return getWrapped().getByte(parameterIndex);
    }

    @Override
    public short getShort(int parameterIndex)
            throws SQLException {
        return getWrapped().getShort(parameterIndex);
    }

    @Override
    public int getInt(int parameterIndex)
            throws SQLException {
        return getWrapped().getInt(parameterIndex);
    }

    @Override
    public long getLong(int parameterIndex)
            throws SQLException {
        return getWrapped().getLong(parameterIndex);
    }

    @Override
    public float getFloat(int parameterIndex)
            throws SQLException {
        return getWrapped().getFloat(parameterIndex);
    }

    @Override
    public double getDouble(int parameterIndex)
            throws SQLException {
        return getWrapped().getDouble(parameterIndex);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BigDecimal getBigDecimal(int parameterIndex, int scale)
            throws SQLException {
        return getWrapped().getBigDecimal(parameterIndex, scale);
    }

    @Override
    public byte[] getBytes(int parameterIndex)
            throws SQLException {
        return getWrapped().getBytes(parameterIndex);
    }

    @Override
    public Date getDate(int parameterIndex)
            throws SQLException {
        return getWrapped().getDate(parameterIndex);
    }

    @Override
    public Time getTime(int parameterIndex)
            throws SQLException {
        return getWrapped().getTime(parameterIndex);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex)
            throws SQLException {
        return getWrapped().getTimestamp(parameterIndex);
    }

    @Override
    public Object getObject(int parameterIndex)
            throws SQLException {
        return getWrapped().getObject(parameterIndex);
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex)
            throws SQLException {
        return getWrapped().getBigDecimal(parameterIndex);
    }

    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> map)
            throws SQLException {
        return getWrapped().getObject(parameterIndex, map);
    }

    @Override
    public Ref getRef(int parameterIndex)
            throws SQLException {
        return getWrapped().getRef(parameterIndex);
    }

    @Override
    public Blob getBlob(int parameterIndex)
            throws SQLException {
        return getWrapped().getBlob(parameterIndex);
    }

    @Override
    public Clob getClob(int parameterIndex)
            throws SQLException {
        return getWrapped().getClob(parameterIndex);
    }

    @Override
    public Array getArray(int parameterIndex)
            throws SQLException {
        return getWrapped().getArray(parameterIndex);
    }

    @Override
    public Date getDate(int parameterIndex, Calendar cal)
            throws SQLException {
        return getWrapped().getDate(parameterIndex, cal);
    }

    @Override
    public Time getTime(int parameterIndex, Calendar cal)
            throws SQLException {
        return getWrapped().getTime(parameterIndex, cal);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex, Calendar cal)
            throws SQLException {
        return getWrapped().getTimestamp(parameterIndex, cal);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, String typeName)
            throws SQLException {
        getWrapped().registerOutParameter(parameterIndex, sqlType, typeName);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType)
            throws SQLException {
        getWrapped().registerOutParameter(parameterName, sqlType);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, int scale)
            throws SQLException {
        getWrapped().registerOutParameter(parameterName, sqlType, scale);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, String typeName)
            throws SQLException {
        getWrapped().registerOutParameter(parameterName, sqlType, typeName);
    }

    @Override
    public URL getURL(int parameterIndex)
            throws SQLException {
        return getWrapped().getURL(parameterIndex);
    }

    @Override
    public void setURL(String parameterName, URL val)
            throws SQLException {
        getWrapped().setURL(parameterName, val);
    }

    @Override
    public void setNull(String parameterName, int sqlType)
            throws SQLException {
        getWrapped().setNull(parameterName, sqlType);
    }

    @Override
    public void setBoolean(String parameterName, boolean x)
            throws SQLException {
        getWrapped().setBoolean(parameterName, x);
    }

    @Override
    public void setByte(String parameterName, byte x)
            throws SQLException {
        getWrapped().setByte(parameterName, x);
    }

    @Override
    public void setShort(String parameterName, short x)
            throws SQLException {
        getWrapped().setShort(parameterName, x);
    }

    @Override
    public void setInt(String parameterName, int x)
            throws SQLException {
        getWrapped().setInt(parameterName, x);
    }

    @Override
    public void setLong(String parameterName, long x)
            throws SQLException {
        getWrapped().setLong(parameterName, x);
    }

    @Override
    public void setFloat(String parameterName, float x)
            throws SQLException {
        getWrapped().setFloat(parameterName, x);
    }

    @Override
    public void setDouble(String parameterName, double x)
            throws SQLException {
        getWrapped().setDouble(parameterName, x);
    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x)
            throws SQLException {
        getWrapped().setBigDecimal(parameterName, x);
    }

    @Override
    public void setString(String parameterName, String x)
            throws SQLException {
        getWrapped().setString(parameterName, x);
    }

    @Override
    public void setBytes(String parameterName, byte[] x)
            throws SQLException {
        getWrapped().setBytes(parameterName, x);
    }

    @Override
    public void setDate(String parameterName, Date x)
            throws SQLException {
        getWrapped().setDate(parameterName, x);
    }

    @Override
    public void setTime(String parameterName, Time x)
            throws SQLException {
        getWrapped().setTime(parameterName, x);
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x)
            throws SQLException {
        getWrapped().setTimestamp(parameterName, x);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length)
            throws SQLException {
        getWrapped().setAsciiStream(parameterName, x, length);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length)
            throws SQLException {
        getWrapped().setBinaryStream(parameterName, x, length);
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scale)
            throws SQLException {
        getWrapped().setObject(parameterName, x, targetSqlType, scale);
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType)
            throws SQLException {
        getWrapped().setObject(parameterName, x, targetSqlType);
    }

    @Override
    public void setObject(String parameterName, Object x)
            throws SQLException {
        getWrapped().setObject(parameterName, x);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, int length)
            throws SQLException {
        getWrapped().setCharacterStream(parameterName, reader, length);
    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal)
            throws SQLException {
        getWrapped().setDate(parameterName, x, cal);
    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal)
            throws SQLException {
        getWrapped().setTime(parameterName, x, cal);
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal)
            throws SQLException {
        getWrapped().setTimestamp(parameterName, x, cal);
    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName)
            throws SQLException {
        getWrapped().setNull(parameterName, sqlType, typeName);
    }

    @Override
    public String getString(String parameterName)
            throws SQLException {
        return getWrapped().getString(parameterName);
    }

    @Override
    public boolean getBoolean(String parameterName)
            throws SQLException {
        return getWrapped().getBoolean(parameterName);
    }

    @Override
    public byte getByte(String parameterName)
            throws SQLException {
        return getWrapped().getByte(parameterName);
    }

    @Override
    public short getShort(String parameterName)
            throws SQLException {
        return getWrapped().getShort(parameterName);
    }

    @Override
    public int getInt(String parameterName)
            throws SQLException {
        return getWrapped().getInt(parameterName);
    }

    @Override
    public long getLong(String parameterName)
            throws SQLException {
        return getWrapped().getLong(parameterName);
    }

    @Override
    public float getFloat(String parameterName)
            throws SQLException {
        return getWrapped().getFloat(parameterName);
    }

    @Override
    public double getDouble(String parameterName)
            throws SQLException {
        return getWrapped().getDouble(parameterName);
    }

    @Override
    public byte[] getBytes(String parameterName)
            throws SQLException {
        return getWrapped().getBytes(parameterName);
    }

    @Override
    public Date getDate(String parameterName)
            throws SQLException {
        return getWrapped().getDate(parameterName);
    }

    @Override
    public Time getTime(String parameterName)
            throws SQLException {
        return getWrapped().getTime(parameterName);
    }

    @Override
    public Timestamp getTimestamp(String parameterName)
            throws SQLException {
        return getWrapped().getTimestamp(parameterName);
    }

    @Override
    public Object getObject(String parameterName)
            throws SQLException {
        return getWrapped().getObject(parameterName);
    }

    @Override
    public BigDecimal getBigDecimal(String parameterName)
            throws SQLException {
        return getWrapped().getBigDecimal(parameterName);
    }

    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> map)
            throws SQLException {
        return getWrapped().getObject(parameterName, map);
    }

    @Override
    public Ref getRef(String parameterName)
            throws SQLException {
        return getWrapped().getRef(parameterName);
    }

    @Override
    public Blob getBlob(String parameterName)
            throws SQLException {
        return getWrapped().getBlob(parameterName);
    }

    @Override
    public Clob getClob(String parameterName)
            throws SQLException {
        return getWrapped().getClob(parameterName);
    }

    @Override
    public Array getArray(String parameterName)
            throws SQLException {
        return getWrapped().getArray(parameterName);
    }

    @Override
    public Date getDate(String parameterName, Calendar cal)
            throws SQLException {
        return getWrapped().getDate(parameterName, cal);
    }

    @Override
    public Time getTime(String parameterName, Calendar cal)
            throws SQLException {
        return getWrapped().getTime(parameterName, cal);
    }

    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal)
            throws SQLException {
        return getWrapped().getTimestamp(parameterName, cal);
    }

    @Override
    public URL getURL(String parameterName)
            throws SQLException {
        return getWrapped().getURL(parameterName);
    }

    @Override
    public RowId getRowId(int parameterIndex)
            throws SQLException {
        return getWrapped().getRowId(parameterIndex);
    }

    @Override
    public RowId getRowId(String parameterName)
            throws SQLException {
        return getWrapped().getRowId(parameterName);
    }

    @Override
    public void setRowId(String parameterName, RowId x)
            throws SQLException {
        getWrapped().setRowId(parameterName, x);
    }

    @Override
    public void setNString(String parameterName, String value)
            throws SQLException {
        getWrapped().setNString(parameterName, value);
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value, long length)
            throws SQLException {
        getWrapped().setNCharacterStream(parameterName, value, length);
    }

    @Override
    public void setNClob(String parameterName, NClob value)
            throws SQLException {
        getWrapped().setNClob(parameterName, value);
    }

    @Override
    public void setClob(String parameterName, Reader reader, long length)
            throws SQLException {
        getWrapped().setClob(parameterName, reader, length);
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream, long length)
            throws SQLException {
        getWrapped().setBlob(parameterName, inputStream, length);
    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length)
            throws SQLException {
        getWrapped().setNClob(parameterName, reader, length);
    }

    @Override
    public NClob getNClob(int parameterIndex)
            throws SQLException {
        return getWrapped().getNClob(parameterIndex);
    }

    @Override
    public NClob getNClob(String parameterName)
            throws SQLException {
        return getWrapped().getNClob(parameterName);
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject)
            throws SQLException {
        getWrapped().setSQLXML(parameterName, xmlObject);
    }

    @Override
    public SQLXML getSQLXML(int parameterIndex)
            throws SQLException {
        return getWrapped().getSQLXML(parameterIndex);
    }

    @Override
    public SQLXML getSQLXML(String parameterName)
            throws SQLException {
        return getWrapped().getSQLXML(parameterName);
    }

    @Override
    public String getNString(int parameterIndex)
            throws SQLException {
        return getWrapped().getNString(parameterIndex);
    }

    @Override
    public String getNString(String parameterName)
            throws SQLException {
        return getWrapped().getNString(parameterName);
    }

    @Override
    public Reader getNCharacterStream(int parameterIndex)
            throws SQLException {
        return getWrapped().getNCharacterStream(parameterIndex);
    }

    @Override
    public Reader getNCharacterStream(String parameterName)
            throws SQLException {
        return getWrapped().getNCharacterStream(parameterName);
    }

    @Override
    public Reader getCharacterStream(int parameterIndex)
            throws SQLException {
        return getWrapped().getCharacterStream(parameterIndex);
    }

    @Override
    public Reader getCharacterStream(String parameterName)
            throws SQLException {
        return getWrapped().getCharacterStream(parameterName);
    }

    @Override
    public void setBlob(String parameterName, Blob x)
            throws SQLException {
        getWrapped().setBlob(parameterName, x);
    }

    @Override
    public void setClob(String parameterName, Clob x)
            throws SQLException {
        getWrapped().setClob(parameterName, x);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length)
            throws SQLException {
        getWrapped().setAsciiStream(parameterName, x, length);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length)
            throws SQLException {
        getWrapped().setBinaryStream(parameterName, x, length);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, long length)
            throws SQLException {
        getWrapped().setCharacterStream(parameterName, reader, length);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x)
            throws SQLException {
        getWrapped().setAsciiStream(parameterName, x);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x)
            throws SQLException {
        getWrapped().setBinaryStream(parameterName, x);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader)
            throws SQLException {
        getWrapped().setCharacterStream(parameterName, reader);
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value)
            throws SQLException {
        getWrapped().setNCharacterStream(parameterName, value);
    }

    @Override
    public void setClob(String parameterName, Reader reader)
            throws SQLException {
        getWrapped().setClob(parameterName, reader);
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream)
            throws SQLException {
        getWrapped().setBlob(parameterName, inputStream);
    }

    @Override
    public void setNClob(String parameterName, Reader reader)
            throws SQLException {
        getWrapped().setNClob(parameterName, reader);
    }

    @Override
    public <T> T getObject(int parameterIndex, Class<T> type)
            throws SQLException {
        return getWrapped().getObject(parameterIndex, type);
    }

    @Override
    public <T> T getObject(String parameterName, Class<T> type)
            throws SQLException {
        return getWrapped().getObject(parameterName, type);
    }

}
