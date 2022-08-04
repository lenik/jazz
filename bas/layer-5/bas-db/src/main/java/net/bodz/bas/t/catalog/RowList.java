package net.bodz.bas.t.catalog;

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
    public synchronized long consume(ResultSet resultSet, Long limit)
            throws SQLException {
        boolean limited = limit != null;
        long nLimit = limited ? limit.longValue() : -1L;
        int rowIndex = getRowCount();
        long n = 0;
        while (resultSet.next()) {
            MutableRow row = new MutableRow(this, rowIndex++);
            row.readObject(resultSet);
            this.rows.add(row);
            n++;
            if (limited && n >= nLimit)
                break;
        }
        return n;
    }

}