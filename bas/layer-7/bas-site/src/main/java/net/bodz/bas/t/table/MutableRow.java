package net.bodz.bas.t.table;

import java.nio.BufferOverflowException;
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
import net.bodz.bas.t.iterator.PrefetchedIterator;

public class MutableRow
        implements
            IAppendableRow {

    IRowSetMetadata metadata;
    int rowIndex;
    List<Object> list;
    List<Boolean> sets;

    public MutableRow(IRowSetMetadata metadata, int rowIndex) {
        if (metadata == null)
            throw new NullPointerException("metadata");
        this.metadata = metadata;
        this.rowIndex = rowIndex;

        int n = metadata.getColumnCount();
        this.list = new ArrayList<Object>(n);
        this.sets = new ArrayList<Boolean>(n);
    }

    @Override
    public IRowSetMetadata getRowSetMetadata() {
        return metadata;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    @Override
    public IColumnMetadata getMetadata(int index) {
        return metadata.getColumn(index);
    }

    @Override
    public Class<?> getType(int index) {
        IColumnMetadata column = metadata.getColumn(index);
        if (column == null)
            return null;
        else
            return column.getType();
    }

    @Override
    public Object get(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Invalid column index: " + index);

        if (index >= getRowSetMetadata().getColumnCount()) {
            if (metadata.isSparse())
                return null;
            else
                throw new IndexOutOfBoundsException("Invalid column index: " + index);
        }

        if (index >= list.size())
            return null;

        return list.get(index);
    }

    @Override
    public void set(int index, Object o) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Invalid column index: " + index);

        int maxSize = metadata.getColumnCount();
        if (metadata.isSparse())
            maxSize = DefaultRowSetMetadata.MAX_SPARSE_COLUMNS;

        if (index >= maxSize)
            throw new IndexOutOfBoundsException("Invalid column index: " + index);

        int lack = index - (list.size() - 1);
        for (int i = 0; i < lack; i++) {
            list.add(null);
            sets.add(false);
        }

        list.set(index, o);
        sets.set(index, true);
    }

    @Override
    public boolean isSet(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Invalid column index: " + index);

        if (index >= getRowSetMetadata().getColumnCount()) {
            if (metadata.isSparse())
                return false;
            else
                throw new IndexOutOfBoundsException("Invalid column index: " + index);
        }

        if (index >= sets.size())
            return false;

        return sets.get(index);
    }

    @Override
    public void append(Object o) {
        int maxSize = metadata.getColumnCount();
        if (metadata.isSparse())
            maxSize = DefaultRowSetMetadata.MAX_SPARSE_COLUMNS;

        if (list.size() >= maxSize)
            throw new BufferOverflowException();

        list.add(o);
        sets.add(true);
    }

    @Override
    public Iterator<Object> iterator() {
        int maxSize = metadata.getColumnCount();
        if (metadata.isSparse())
            maxSize = list.size();
        final int retSize = maxSize;
        return new PrefetchedIterator<Object>() {

            int i = 0;

            @Override
            protected Object fetch() {
                if (i < retSize) {
                    i++;
                    return list.get(i);
                }
                return end();
            }

        };
    }

    @Override
    public void readObject(JsonObject j_row)
            throws ParseException {
    }

    @Override
    public void readObjectBoxed(Object j_row)
            throws ParseException {
        JsonArray jv = (JsonArray) j_row;
        int jn = jv.length();

        int cc = metadata.getColumnCount();
        List<Object> list = new ArrayList<Object>(cc);

        for (int i = 0; i < cc && i < jn; i++) {
            Object j_cell_box = jv.get(i);
            IColumnMetadata column = metadata.getColumn(i);
            Object cell = column.readJson(j_cell_box);
            list.add(cell);
        }
        this.list = list;
    }

    @Override
    public void readObject(IElement x_row)
            throws ParseException, LoaderException {
        int cc = metadata.getColumnCount();
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < cc; i++) {
            IColumnMetadata column = getMetadata(i);
            String tagName = column.getName();
            IElement x_cell = x_row.selectByTag(tagName).getFirst();
            if (x_cell != null) {
                Object cell = column.readXml(x_cell);
                list.add(cell);
            } else {
                list.add(null);
            }
        }
        this.list = list;
    }

    @Override
    public void readObjectBoxed(IElement x_rows)
            throws ParseException, LoaderException {
        // XXX xpath for immediate children.
        IElements x_row_v = x_rows.selectByTag("row");
        IElement x_row = x_row_v.get(rowIndex);
        readObject(x_row);
    }

    public void readObject(ResultSet rs)
            throws SQLException {
        int cc = metadata.getColumnCount();
        for (int i = 0; i < cc; i++) {
            Object cell = rs.getObject(i + 1);
            set(i, cell);
        }
    }

}
