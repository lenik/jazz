package net.bodz.bas.t.table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.IElements;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class RowList
        implements
            IRowSet {

    IRowSetMetadata metadata;
    List<IRow> rows;

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
        metadata.readObject(resultSet.getMetaData());
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

    protected DefaultRowSetMetadata createMetadata() {
        return new DefaultRowSetMetadata();
    }

    public static RowList wrap(IRowSetMetadata metadata, List<IRow> rows) {
        return new RowList(metadata, rows, false);
    }

    @Override
    public IRowSetMetadata getMetadata() {
        return metadata;
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

    protected IRowSetMetadata newMetadata() {
        return new DefaultRowSetMetadata();
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        JsonObject j_md = o.getJsonObject(K_METADATA);
        if (j_md != null) {
            IRowSetMetadata metadata = newMetadata();
            metadata.readObject(j_md);
            this.metadata = metadata;
        }

        JsonArray j_rows = o.getJsonArray(K_ROWS);
        int n = j_rows.length();
        List<IRow> rows = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < n; rowIndex++) {
            JsonArray j_row = j_rows.getJsonArray(rowIndex);
            MutableRow row = new MutableRow(this, rowIndex);
            row.readObjectBoxed(j_row);
            rows.add(row);
        }
        this.rows = rows;
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
        IElement x_md = x_table.selectByTag(K_METADATA).getFirst();
        if (x_md != null && x_md.getParentNode() == x_table) {
            IRowSetMetadata metadata = new DefaultRowSetMetadata();
            metadata.readObject(x_md);
            this.metadata = metadata;
        }

        IElement x_rows = x_table.selectByTag(K_ROWS).getFirst();
        IElements x_row_v = x_rows.children(); // <row>
        int rowCount = x_rows.getElementCount();
        List<IRow> rows = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            IElement x_row = x_row_v.get(rowIndex);
            MutableRow row = new MutableRow(this, rowIndex);
            row.readObject(x_row);
            rows.add(row);
        }
        this.rows = rows;
    }

    public List<IRow> getList() {
        return rows;
    }

    public synchronized void addAll(ResultSet rs)
            throws SQLException {
        int rowIndex = getRowCount();
        while (rs.next()) {
            MutableRow row = new MutableRow(this, rowIndex++);
            row.readObject(rs);
            this.rows.add(row);
        }
    }

}
