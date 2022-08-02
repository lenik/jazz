package net.bodz.bas.t.table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RowList
        implements
            IRowSet,
            IResultSetConsumer {

    IRowSetMetadata metadata;
    List<IRow> rows;

    protected RowList() {
    }

    public RowList(IRowSetMetadata metadata) {
        this(metadata, new ArrayList<>(), false);
    }

    public RowList(IRowSetMetadata metadata, List<IRow> rows) {
        this(metadata, rows, true);
    }

    private RowList(IRowSetMetadata metadata, List<IRow> rows, boolean copy) {
        if (metadata == null)
            throw new NullPointerException("metadata");
        if (rows == null)
            throw new NullPointerException("rows");

        this.metadata = metadata;
        if (copy)
            this.rows = new ArrayList<>(rows);
        else
            this.rows = rows;
    }

    public RowList(ResultSet resultSet)
            throws SQLException {
        this(resultSet, null);
    }

    public RowList(ResultSet resultSet, Long maxRows)
            throws SQLException {
        DefaultRowSetMetadata metadata = createMetadata();
        Connection cn = resultSet.getStatement().getConnection();
        metadata.loadFromRSMD(cn, resultSet.getMetaData());
        this.metadata = metadata;
        this.rows = new ArrayList<>();

        boolean unlimit = maxRows == null;
        long max = unlimit ? -1L : maxRows.longValue();
        int count = 0;
        while (unlimit || count < max) {
            if (!resultSet.next())
                break;
            MutableRow row = new MutableRow(this, count);
            row.readObject(resultSet);
            this.rows.add(row);
            if (!unlimit)
                count++;
        }
    }

    public static RowList wrap(IRowSetMetadata metadata, List<IRow> rows) {
        return new RowList(metadata, rows, false);
    }

    @Override
    public IRowSetMetadata getMetadata() {
        return metadata;
    }

    protected DefaultRowSetMetadata createMetadata() {
        return new DefaultRowSetMetadata();
    }

    public List<IRow> getList() {
        return rows;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public IRow getRow(int index) {
        return rows.get(index);
    }

    public IRow getRow(int index, IRow fallback) {
        if (0 <= index && index < rows.size())
            return rows.get(index);
        else
            return fallback;
    }

    public void addRow(IRow row) {
        if (row == null)
            throw new NullPointerException("row");
        IRowSetMetadata other = row.getRowSet().getMetadata();
        if (other != null) {
            IRowSetMetadata mine = getMetadata();
            if (other != mine)
                throw new IllegalArgumentException("Conflict metadata");
        }
        rows.add(row);
    }

    @Override
    public Iterator<IRow> iterator() {
        return rows.iterator();
    }

    @Override
    public synchronized long consume(ResultSet rs, Long limit)
            throws SQLException {
        int rowIndex = getRowCount();
        long n = 0;
        while (rs.next()) {
            MutableRow row = new MutableRow(this, rowIndex++);
            row.readObject(rs);
            this.rows.add(row);
            n++;
            if (limit != null && n >= limit)
                break;
        }
        return n;
    }

}
