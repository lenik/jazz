package net.bodz.bas.proxy.java.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class ResultSetDecorator
        extends AbstractJavasqlWrapper<ResultSet>
        implements ResultSet {

    private static final long serialVersionUID = 1L;

    public ResultSetDecorator(ResultSet _orig) {
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
    public boolean next()
            throws SQLException {
        return _orig.next();
    }

    @Override
    public void close()
            throws SQLException {
        _orig.close();
    }

    @Override
    public boolean wasNull()
            throws SQLException {
        return _orig.wasNull();
    }

    @Override
    public String getString(int columnIndex)
            throws SQLException {
        return _orig.getString(columnIndex);
    }

    @Override
    public boolean getBoolean(int columnIndex)
            throws SQLException {
        return _orig.getBoolean(columnIndex);
    }

    @Override
    public byte getByte(int columnIndex)
            throws SQLException {
        return _orig.getByte(columnIndex);
    }

    @Override
    public short getShort(int columnIndex)
            throws SQLException {
        return _orig.getShort(columnIndex);
    }

    @Override
    public int getInt(int columnIndex)
            throws SQLException {
        return _orig.getInt(columnIndex);
    }

    @Override
    public long getLong(int columnIndex)
            throws SQLException {
        return _orig.getLong(columnIndex);
    }

    @Override
    public float getFloat(int columnIndex)
            throws SQLException {
        return _orig.getFloat(columnIndex);
    }

    @Override
    public double getDouble(int columnIndex)
            throws SQLException {
        return _orig.getDouble(columnIndex);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale)
            throws SQLException {
        return _orig.getBigDecimal(columnIndex, scale);
    }

    @Override
    public byte[] getBytes(int columnIndex)
            throws SQLException {
        return _orig.getBytes(columnIndex);
    }

    @Override
    public Date getDate(int columnIndex)
            throws SQLException {
        return _orig.getDate(columnIndex);
    }

    @Override
    public Time getTime(int columnIndex)
            throws SQLException {
        return _orig.getTime(columnIndex);
    }

    @Override
    public Timestamp getTimestamp(int columnIndex)
            throws SQLException {
        return _orig.getTimestamp(columnIndex);
    }

    @Override
    public InputStream getAsciiStream(int columnIndex)
            throws SQLException {
        return _orig.getAsciiStream(columnIndex);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InputStream getUnicodeStream(int columnIndex)
            throws SQLException {
        return _orig.getUnicodeStream(columnIndex);
    }

    @Override
    public InputStream getBinaryStream(int columnIndex)
            throws SQLException {
        return _orig.getBinaryStream(columnIndex);
    }

    @Override
    public String getString(String columnLabel)
            throws SQLException {
        return _orig.getString(columnLabel);
    }

    @Override
    public boolean getBoolean(String columnLabel)
            throws SQLException {
        return _orig.getBoolean(columnLabel);
    }

    @Override
    public byte getByte(String columnLabel)
            throws SQLException {
        return _orig.getByte(columnLabel);
    }

    @Override
    public short getShort(String columnLabel)
            throws SQLException {
        return _orig.getShort(columnLabel);
    }

    @Override
    public int getInt(String columnLabel)
            throws SQLException {
        return _orig.getInt(columnLabel);
    }

    @Override
    public long getLong(String columnLabel)
            throws SQLException {
        return _orig.getLong(columnLabel);
    }

    @Override
    public float getFloat(String columnLabel)
            throws SQLException {
        return _orig.getFloat(columnLabel);
    }

    @Override
    public double getDouble(String columnLabel)
            throws SQLException {
        return _orig.getDouble(columnLabel);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale)
            throws SQLException {
        return _orig.getBigDecimal(columnLabel, scale);
    }

    @Override
    public byte[] getBytes(String columnLabel)
            throws SQLException {
        return _orig.getBytes(columnLabel);
    }

    @Override
    public Date getDate(String columnLabel)
            throws SQLException {
        return _orig.getDate(columnLabel);
    }

    @Override
    public Time getTime(String columnLabel)
            throws SQLException {
        return _orig.getTime(columnLabel);
    }

    @Override
    public Timestamp getTimestamp(String columnLabel)
            throws SQLException {
        return _orig.getTimestamp(columnLabel);
    }

    @Override
    public InputStream getAsciiStream(String columnLabel)
            throws SQLException {
        return _orig.getAsciiStream(columnLabel);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InputStream getUnicodeStream(String columnLabel)
            throws SQLException {
        return _orig.getUnicodeStream(columnLabel);
    }

    @Override
    public InputStream getBinaryStream(String columnLabel)
            throws SQLException {
        return _orig.getBinaryStream(columnLabel);
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
    public String getCursorName()
            throws SQLException {
        return _orig.getCursorName();
    }

    @Override
    public ResultSetMetaData getMetaData()
            throws SQLException {
        return _orig.getMetaData();
    }

    @Override
    public Object getObject(int columnIndex)
            throws SQLException {
        return _orig.getObject(columnIndex);
    }

    @Override
    public Object getObject(String columnLabel)
            throws SQLException {
        return _orig.getObject(columnLabel);
    }

    @Override
    public int findColumn(String columnLabel)
            throws SQLException {
        return _orig.findColumn(columnLabel);
    }

    @Override
    public Reader getCharacterStream(int columnIndex)
            throws SQLException {
        return _orig.getCharacterStream(columnIndex);
    }

    @Override
    public Reader getCharacterStream(String columnLabel)
            throws SQLException {
        return _orig.getCharacterStream(columnLabel);
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex)
            throws SQLException {
        return _orig.getBigDecimal(columnIndex);
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel)
            throws SQLException {
        return _orig.getBigDecimal(columnLabel);
    }

    @Override
    public boolean isBeforeFirst()
            throws SQLException {
        return _orig.isBeforeFirst();
    }

    @Override
    public boolean isAfterLast()
            throws SQLException {
        return _orig.isAfterLast();
    }

    @Override
    public boolean isFirst()
            throws SQLException {
        return _orig.isFirst();
    }

    @Override
    public boolean isLast()
            throws SQLException {
        return _orig.isLast();
    }

    @Override
    public void beforeFirst()
            throws SQLException {
        _orig.beforeFirst();
    }

    @Override
    public void afterLast()
            throws SQLException {
        _orig.afterLast();
    }

    @Override
    public boolean first()
            throws SQLException {
        return _orig.first();
    }

    @Override
    public boolean last()
            throws SQLException {
        return _orig.last();
    }

    @Override
    public int getRow()
            throws SQLException {
        return _orig.getRow();
    }

    @Override
    public boolean absolute(int row)
            throws SQLException {
        return _orig.absolute(row);
    }

    @Override
    public boolean relative(int rows)
            throws SQLException {
        return _orig.relative(rows);
    }

    @Override
    public boolean previous()
            throws SQLException {
        return _orig.previous();
    }

    @Override
    public void setFetchDirection(int direction)
            throws SQLException {
        _orig.setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection()
            throws SQLException {
        return _orig.getFetchDirection();
    }

    @Override
    public void setFetchSize(int rows)
            throws SQLException {
        _orig.setFetchSize(rows);
    }

    @Override
    public int getFetchSize()
            throws SQLException {
        return _orig.getFetchSize();
    }

    @Override
    public int getType()
            throws SQLException {
        return _orig.getType();
    }

    @Override
    public int getConcurrency()
            throws SQLException {
        return _orig.getConcurrency();
    }

    @Override
    public boolean rowUpdated()
            throws SQLException {
        return _orig.rowUpdated();
    }

    @Override
    public boolean rowInserted()
            throws SQLException {
        return _orig.rowInserted();
    }

    @Override
    public boolean rowDeleted()
            throws SQLException {
        return _orig.rowDeleted();
    }

    @Override
    public void updateNull(int columnIndex)
            throws SQLException {
        _orig.updateNull(columnIndex);
    }

    @Override
    public void updateBoolean(int columnIndex, boolean x)
            throws SQLException {
        _orig.updateBoolean(columnIndex, x);
    }

    @Override
    public void updateByte(int columnIndex, byte x)
            throws SQLException {
        _orig.updateByte(columnIndex, x);
    }

    @Override
    public void updateShort(int columnIndex, short x)
            throws SQLException {
        _orig.updateShort(columnIndex, x);
    }

    @Override
    public void updateInt(int columnIndex, int x)
            throws SQLException {
        _orig.updateInt(columnIndex, x);
    }

    @Override
    public void updateLong(int columnIndex, long x)
            throws SQLException {
        _orig.updateLong(columnIndex, x);
    }

    @Override
    public void updateFloat(int columnIndex, float x)
            throws SQLException {
        _orig.updateFloat(columnIndex, x);
    }

    @Override
    public void updateDouble(int columnIndex, double x)
            throws SQLException {
        _orig.updateDouble(columnIndex, x);
    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x)
            throws SQLException {
        _orig.updateBigDecimal(columnIndex, x);
    }

    @Override
    public void updateString(int columnIndex, String x)
            throws SQLException {
        _orig.updateString(columnIndex, x);
    }

    @Override
    public void updateBytes(int columnIndex, byte[] x)
            throws SQLException {
        _orig.updateBytes(columnIndex, x);
    }

    @Override
    public void updateDate(int columnIndex, Date x)
            throws SQLException {
        _orig.updateDate(columnIndex, x);
    }

    @Override
    public void updateTime(int columnIndex, Time x)
            throws SQLException {
        _orig.updateTime(columnIndex, x);
    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x)
            throws SQLException {
        _orig.updateTimestamp(columnIndex, x);
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length)
            throws SQLException {
        _orig.updateAsciiStream(columnIndex, x, length);
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length)
            throws SQLException {
        _orig.updateBinaryStream(columnIndex, x, length);
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length)
            throws SQLException {
        _orig.updateCharacterStream(columnIndex, x, length);
    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength)
            throws SQLException {
        _orig.updateObject(columnIndex, x, scaleOrLength);
    }

    @Override
    public void updateObject(int columnIndex, Object x)
            throws SQLException {
        _orig.updateObject(columnIndex, x);
    }

    @Override
    public void updateNull(String columnLabel)
            throws SQLException {
        _orig.updateNull(columnLabel);
    }

    @Override
    public void updateBoolean(String columnLabel, boolean x)
            throws SQLException {
        _orig.updateBoolean(columnLabel, x);
    }

    @Override
    public void updateByte(String columnLabel, byte x)
            throws SQLException {
        _orig.updateByte(columnLabel, x);
    }

    @Override
    public void updateShort(String columnLabel, short x)
            throws SQLException {
        _orig.updateShort(columnLabel, x);
    }

    @Override
    public void updateInt(String columnLabel, int x)
            throws SQLException {
        _orig.updateInt(columnLabel, x);
    }

    @Override
    public void updateLong(String columnLabel, long x)
            throws SQLException {
        _orig.updateLong(columnLabel, x);
    }

    @Override
    public void updateFloat(String columnLabel, float x)
            throws SQLException {
        _orig.updateFloat(columnLabel, x);
    }

    @Override
    public void updateDouble(String columnLabel, double x)
            throws SQLException {
        _orig.updateDouble(columnLabel, x);
    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x)
            throws SQLException {
        _orig.updateBigDecimal(columnLabel, x);
    }

    @Override
    public void updateString(String columnLabel, String x)
            throws SQLException {
        _orig.updateString(columnLabel, x);
    }

    @Override
    public void updateBytes(String columnLabel, byte[] x)
            throws SQLException {
        _orig.updateBytes(columnLabel, x);
    }

    @Override
    public void updateDate(String columnLabel, Date x)
            throws SQLException {
        _orig.updateDate(columnLabel, x);
    }

    @Override
    public void updateTime(String columnLabel, Time x)
            throws SQLException {
        _orig.updateTime(columnLabel, x);
    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x)
            throws SQLException {
        _orig.updateTimestamp(columnLabel, x);
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length)
            throws SQLException {
        _orig.updateAsciiStream(columnLabel, x, length);
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length)
            throws SQLException {
        _orig.updateBinaryStream(columnLabel, x, length);
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length)
            throws SQLException {
        _orig.updateCharacterStream(columnLabel, reader, length);
    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength)
            throws SQLException {
        _orig.updateObject(columnLabel, x, scaleOrLength);
    }

    @Override
    public void updateObject(String columnLabel, Object x)
            throws SQLException {
        _orig.updateObject(columnLabel, x);
    }

    @Override
    public void insertRow()
            throws SQLException {
        _orig.insertRow();
    }

    @Override
    public void updateRow()
            throws SQLException {
        _orig.updateRow();
    }

    @Override
    public void deleteRow()
            throws SQLException {
        _orig.deleteRow();
    }

    @Override
    public void refreshRow()
            throws SQLException {
        _orig.refreshRow();
    }

    @Override
    public void cancelRowUpdates()
            throws SQLException {
        _orig.cancelRowUpdates();
    }

    @Override
    public void moveToInsertRow()
            throws SQLException {
        _orig.moveToInsertRow();
    }

    @Override
    public void moveToCurrentRow()
            throws SQLException {
        _orig.moveToCurrentRow();
    }

    @Override
    public Statement getStatement()
            throws SQLException {
        return _orig.getStatement();
    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map)
            throws SQLException {
        return _orig.getObject(columnIndex, map);
    }

    @Override
    public Ref getRef(int columnIndex)
            throws SQLException {
        return _orig.getRef(columnIndex);
    }

    @Override
    public Blob getBlob(int columnIndex)
            throws SQLException {
        return _orig.getBlob(columnIndex);
    }

    @Override
    public Clob getClob(int columnIndex)
            throws SQLException {
        return _orig.getClob(columnIndex);
    }

    @Override
    public Array getArray(int columnIndex)
            throws SQLException {
        return _orig.getArray(columnIndex);
    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map)
            throws SQLException {
        return _orig.getObject(columnLabel, map);
    }

    @Override
    public Ref getRef(String columnLabel)
            throws SQLException {
        return _orig.getRef(columnLabel);
    }

    @Override
    public Blob getBlob(String columnLabel)
            throws SQLException {
        return _orig.getBlob(columnLabel);
    }

    @Override
    public Clob getClob(String columnLabel)
            throws SQLException {
        return _orig.getClob(columnLabel);
    }

    @Override
    public Array getArray(String columnLabel)
            throws SQLException {
        return _orig.getArray(columnLabel);
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal)
            throws SQLException {
        return _orig.getDate(columnIndex, cal);
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal)
            throws SQLException {
        return _orig.getDate(columnLabel, cal);
    }

    @Override
    public Time getTime(int columnIndex, Calendar cal)
            throws SQLException {
        return _orig.getTime(columnIndex, cal);
    }

    @Override
    public Time getTime(String columnLabel, Calendar cal)
            throws SQLException {
        return _orig.getTime(columnLabel, cal);
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal)
            throws SQLException {
        return _orig.getTimestamp(columnIndex, cal);
    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal)
            throws SQLException {
        return _orig.getTimestamp(columnLabel, cal);
    }

    @Override
    public URL getURL(int columnIndex)
            throws SQLException {
        return _orig.getURL(columnIndex);
    }

    @Override
    public URL getURL(String columnLabel)
            throws SQLException {
        return _orig.getURL(columnLabel);
    }

    @Override
    public void updateRef(int columnIndex, Ref x)
            throws SQLException {
        _orig.updateRef(columnIndex, x);
    }

    @Override
    public void updateRef(String columnLabel, Ref x)
            throws SQLException {
        _orig.updateRef(columnLabel, x);
    }

    @Override
    public void updateBlob(int columnIndex, Blob x)
            throws SQLException {
        _orig.updateBlob(columnIndex, x);
    }

    @Override
    public void updateBlob(String columnLabel, Blob x)
            throws SQLException {
        _orig.updateBlob(columnLabel, x);
    }

    @Override
    public void updateClob(int columnIndex, Clob x)
            throws SQLException {
        _orig.updateClob(columnIndex, x);
    }

    @Override
    public void updateClob(String columnLabel, Clob x)
            throws SQLException {
        _orig.updateClob(columnLabel, x);
    }

    @Override
    public void updateArray(int columnIndex, Array x)
            throws SQLException {
        _orig.updateArray(columnIndex, x);
    }

    @Override
    public void updateArray(String columnLabel, Array x)
            throws SQLException {
        _orig.updateArray(columnLabel, x);
    }

    @Override
    public RowId getRowId(int columnIndex)
            throws SQLException {
        return _orig.getRowId(columnIndex);
    }

    @Override
    public RowId getRowId(String columnLabel)
            throws SQLException {
        return _orig.getRowId(columnLabel);
    }

    @Override
    public void updateRowId(int columnIndex, RowId x)
            throws SQLException {
        _orig.updateRowId(columnIndex, x);
    }

    @Override
    public void updateRowId(String columnLabel, RowId x)
            throws SQLException {
        _orig.updateRowId(columnLabel, x);
    }

    @Override
    public int getHoldability()
            throws SQLException {
        return _orig.getHoldability();
    }

    @Override
    public boolean isClosed()
            throws SQLException {
        return _orig.isClosed();
    }

    @Override
    public void updateNString(int columnIndex, String nString)
            throws SQLException {
        _orig.updateNString(columnIndex, nString);
    }

    @Override
    public void updateNString(String columnLabel, String nString)
            throws SQLException {
        _orig.updateNString(columnLabel, nString);
    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob)
            throws SQLException {
        _orig.updateNClob(columnIndex, nClob);
    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob)
            throws SQLException {
        _orig.updateNClob(columnLabel, nClob);
    }

    @Override
    public NClob getNClob(int columnIndex)
            throws SQLException {
        return _orig.getNClob(columnIndex);
    }

    @Override
    public NClob getNClob(String columnLabel)
            throws SQLException {
        return _orig.getNClob(columnLabel);
    }

    @Override
    public SQLXML getSQLXML(int columnIndex)
            throws SQLException {
        return _orig.getSQLXML(columnIndex);
    }

    @Override
    public SQLXML getSQLXML(String columnLabel)
            throws SQLException {
        return _orig.getSQLXML(columnLabel);
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject)
            throws SQLException {
        _orig.updateSQLXML(columnIndex, xmlObject);
    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject)
            throws SQLException {
        _orig.updateSQLXML(columnLabel, xmlObject);
    }

    @Override
    public String getNString(int columnIndex)
            throws SQLException {
        return _orig.getNString(columnIndex);
    }

    @Override
    public String getNString(String columnLabel)
            throws SQLException {
        return _orig.getNString(columnLabel);
    }

    @Override
    public Reader getNCharacterStream(int columnIndex)
            throws SQLException {
        return _orig.getNCharacterStream(columnIndex);
    }

    @Override
    public Reader getNCharacterStream(String columnLabel)
            throws SQLException {
        return _orig.getNCharacterStream(columnLabel);
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length)
            throws SQLException {
        _orig.updateNCharacterStream(columnIndex, x, length);
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length)
            throws SQLException {
        _orig.updateNCharacterStream(columnLabel, reader, length);
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length)
            throws SQLException {
        _orig.updateAsciiStream(columnIndex, x, length);
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length)
            throws SQLException {
        _orig.updateBinaryStream(columnIndex, x, length);
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length)
            throws SQLException {
        _orig.updateCharacterStream(columnIndex, x, length);
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length)
            throws SQLException {
        _orig.updateAsciiStream(columnLabel, x, length);
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length)
            throws SQLException {
        _orig.updateBinaryStream(columnLabel, x, length);
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length)
            throws SQLException {
        _orig.updateCharacterStream(columnLabel, reader, length);
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length)
            throws SQLException {
        _orig.updateBlob(columnIndex, inputStream, length);
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length)
            throws SQLException {
        _orig.updateBlob(columnLabel, inputStream, length);
    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length)
            throws SQLException {
        _orig.updateClob(columnIndex, reader, length);
    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length)
            throws SQLException {
        _orig.updateClob(columnLabel, reader, length);
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length)
            throws SQLException {
        _orig.updateNClob(columnIndex, reader, length);
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length)
            throws SQLException {
        _orig.updateNClob(columnLabel, reader, length);
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x)
            throws SQLException {
        _orig.updateNCharacterStream(columnIndex, x);
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader)
            throws SQLException {
        _orig.updateNCharacterStream(columnLabel, reader);
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x)
            throws SQLException {
        _orig.updateAsciiStream(columnIndex, x);
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x)
            throws SQLException {
        _orig.updateBinaryStream(columnIndex, x);
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x)
            throws SQLException {
        _orig.updateCharacterStream(columnIndex, x);
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x)
            throws SQLException {
        _orig.updateAsciiStream(columnLabel, x);
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x)
            throws SQLException {
        _orig.updateBinaryStream(columnLabel, x);
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader)
            throws SQLException {
        _orig.updateCharacterStream(columnLabel, reader);
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream)
            throws SQLException {
        _orig.updateBlob(columnIndex, inputStream);
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream)
            throws SQLException {
        _orig.updateBlob(columnLabel, inputStream);
    }

    @Override
    public void updateClob(int columnIndex, Reader reader)
            throws SQLException {
        _orig.updateClob(columnIndex, reader);
    }

    @Override
    public void updateClob(String columnLabel, Reader reader)
            throws SQLException {
        _orig.updateClob(columnLabel, reader);
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader)
            throws SQLException {
        _orig.updateNClob(columnIndex, reader);
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader)
            throws SQLException {
        _orig.updateNClob(columnLabel, reader);
    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type)
            throws SQLException {
        return _orig.getObject(columnIndex, type);
    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type)
            throws SQLException {
        return _orig.getObject(columnLabel, type);
    }

}
