package net.bodz.bas.t.table;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
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

public class DefaultRowSetMetadata
        implements
            IRowSetMetadata {

    static final int MAX_SPARSE_COLUMNS = 1000;

    ITableMapMetadata parent;
    List<IColumnMetadata> columns = new ArrayList<>();

    public DefaultRowSetMetadata() {
    }

    public DefaultRowSetMetadata(ITableMapMetadata parent) {
        this.parent = parent;
    }

    @Override
    public ITableMapMetadata getParent() {
        return parent;
    }

    public void setParent(ITableMapMetadata parent) {
        this.parent = parent;
    }

    @Override
    public Iterator<IColumnMetadata> iterator() {
        return columns.iterator();
    }

    @Override
    public boolean isSparse() {
        return false;
    }

    @Override
    public List<IColumnMetadata> getColumns() {
        return columns;
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public IColumnMetadata getColumn(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Invalid column index: " + index);
        if (index >= columns.size())
            throw new IndexOutOfBoundsException("Invalid column index: " + index);

        return columns.get(index);
    }

    public synchronized void addColumn(IColumnMetadata column) {
        if (column == null)
            throw new NullPointerException("column");
        columns.add(column);
    }

    public synchronized void setColumn(int index, IColumnMetadata column) {
        if (index < 0)
            throw new IndexOutOfBoundsException("invalid column index: " + index);

        int maxSize = columns.size();
        if (index >= maxSize)
            throw new IndexOutOfBoundsException("invalid column index: " + index);

        columns.set(index, column);
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        JsonArray jv = o.getJsonArray(K_COLUMNS);
        int n = jv.length();
        List<IColumnMetadata> columns = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            JsonObject item = jv.getJsonObject(i);
            DefaultColumnMetadata column = new DefaultColumnMetadata();
            column.readObject(item);
            columns.add(column);
        }
        this.columns = columns;
    }

    @Override
    public void readObject(IElement x_metadata)
            throws ParseException, LoaderException {
        IElement x_columns = x_metadata.selectByTag(K_COLUMNS).first();
        IElements x_column_v = x_columns.children();
        int n = x_column_v.getElementCount();
        List<IColumnMetadata> columns = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            IElement x_column = x_column_v.get(i);
            assert x_column.getTagName().equals(K_COLUMN);
            DefaultColumnMetadata column = new DefaultColumnMetadata();
            column.readObject(x_column);
            columns.add(column);
        }
        this.columns = columns;
    }

    public void readObject(Connection cn, ResultSetMetaData rsmd)
            throws SQLException {
        int cc = rsmd.getColumnCount();
        for (int i = 1; i <= cc; i++) {
            DefaultColumnMetadata column = new DefaultColumnMetadata();
            column.readObject(rsmd, i);
            addColumn(column);
        }
    }

    public String getColumnNames() {
        StringBuilder sb = new StringBuilder(columns.size() * 16);
        for (int i = 0; i < columns.size(); i++) {
            if (i != 0)
                sb.append(", ");
            IColumnMetadata column = columns.get(i);
            sb.append(column.getName());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getColumnNames();
    }

}
