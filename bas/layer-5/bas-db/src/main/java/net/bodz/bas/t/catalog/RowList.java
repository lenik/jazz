package net.bodz.bas.t.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RowList
        implements
            IMutableRowSet,
            IResultSetConsumer {

    protected IRowSetMetadata metadata;
    protected List<IMutableRow> rows;

    protected RowList() {
        rows = new ArrayList<>();
    }

    public RowList(IRowSetMetadata metadata) {
        this(metadata, new ArrayList<>(), false);
    }

    public RowList(IRowSetMetadata metadata, List<IMutableRow> rows) {
        this(metadata, rows, true);
    }

    private RowList(IRowSetMetadata metadata, List<IMutableRow> rows, boolean copy) {
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

    public static RowList wrap(IRowSetMetadata metadata, List<IMutableRow> rows) {
        return new RowList(metadata, rows, false);
    }

    @Override
    public IRowSetMetadata getMetadata() {
        return metadata;
    }

    protected DefaultRowSetMetadata createMetadata() {
        return new DefaultRowSetMetadata();
    }

    @Override
    public List<? extends IMutableRow> getRows() {
        return rows;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public IMutableRow getRow(int index) {
        return rows.get(index);
    }

    public IRow getRow(int index, IRow fallback) {
        if (0 <= index && index < rows.size())
            return rows.get(index);
        else
            return fallback;
    }

    @Override
    public void setRow(int rowIndex, IMutableRow row) {
        checkRowConsistency(row);
        rows.set(rowIndex, row);
    }

    @Override
    public final IMutableRow newRow() {
        return newRow(rows.size());
    }

    protected IMutableRow createRow() {
        return new MutableRow(this);
    }

    @Override
    public IMutableRow newRow(int rowIndex) {
        IMutableRow row = createRow();
        addRow(row);
        return row;
    }

    @Override
    public final void addRow(IMutableRow row) {
        addRow(rows.size(), row);
    }

    @Override
    public void addRow(int rowIndex, IMutableRow row) {
        checkRowConsistency(row);
        rows.add(rowIndex, row);
    }

    void checkRowConsistency(IRow row) {
        if (row == null)
            throw new NullPointerException("row");
        IRowSetMetadata other = row.getRowSet().getMetadata();
        if (other != null) {
            IRowSetMetadata mine = getMetadata();
            if (other != mine)
                throw new IllegalArgumentException("Conflict metadata");
        }
    }

    @Override
    public void removeRow(IMutableRow row) {
        rows.remove(row);
    }

    @Override
    public void removeRow(int rowIndex) {
        rows.remove(rowIndex);
    }

    @Override
    public void removeAll() {
        rows.clear();
    }

    @Override
    public synchronized long consume(ResultSet resultSet, Long limit)
            throws SQLException {
        boolean limited = limit != null;
        long nLimit = limited ? limit.longValue() : -1L;
        long n = 0;
        while (resultSet.next()) {
            IMutableRow row = newRow();
            row.readObject(resultSet);
            n++;
            if (limited && n >= nLimit)
                break;
        }
        return n;
    }

}
