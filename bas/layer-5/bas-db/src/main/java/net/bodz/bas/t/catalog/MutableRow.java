package net.bodz.bas.t.catalog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.iterator.PrefetchedIterator;

public class MutableRow
        implements IAppendableRow {

    static final Logger logger = LoggerFactory.getLogger(MutableRow.class);

    final IRowSet rowSet;
    List<IMutableCell> cells;

    public MutableRow(IRowSet rowSet) {
        if (rowSet == null)
            throw new NullPointerException("rowSet");
        this.rowSet = rowSet;

        IRowSetMetadata metadata = rowSet.getMetadata();
        if (metadata == null)
            throw new NullPointerException("metadata");

        int columnCount = metadata.getColumnCount();
        this.cells = new ArrayList<>(columnCount);
    }

    public IRowSetMetadata getRowSetMetadata() {
        return rowSet.getMetadata();
    }

    @Override
    public IRowSet getRowSet() {
        return rowSet;
    }

    @Override
    public IColumnMetadata getColumn(int index) {
        return getRowSetMetadata().getColumn(index);
    }

    @Override
    public IColumnMetadata getColumn(String name) {
        IRowSetMetadata metadata = getRowSetMetadata();
        if (metadata == null)
            throw new IllegalUsageException("No metadata bound.");

        IColumnMetadata column = metadata.getColumn(name);
        if (column == null)
            throw new NoSuchKeyException(name);

        return column;
    }

    @Override
    public Class<?> getColumnType(int columnIndex) {
        IColumnMetadata column = getRowSetMetadata().getColumn(columnIndex);
        if (column == null)
            return null;
        else
            return column.getJavaClass();
    }

    @Override
    public Class<?> getColumnType(String columnName) {
        IColumnMetadata column = getRowSetMetadata().getColumn(columnName);
        if (column == null)
            return null;
        else
            return column.getJavaClass();
    }

    @Override
    public List<? extends IMutableCell> getCells() {
        return cells;
    }

    @Override
    public int getCellCount() {
        return cells.size();
    }

    @Override
    public IMutableCell getCell(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Invalid column index: " + index);

        if (index >= getCellCount()) {
            if (getRowSetMetadata().isSparse())
                return null;
            else
                throw new IndexOutOfBoundsException("Invalid column index: " + index);
        }

        if (index >= cells.size())
            return null;

        return cells.get(index);
    }

    @Override
    public void setCell(int index, IMutableCell cell) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Invalid column index: " + index);

        int maxSize = getRowSetMetadata().getColumnCount();
        if (getRowSetMetadata().isSparse())
            maxSize = DefaultRowSetMetadata.MAX_SPARSE_COLUMNS;

        if (index >= maxSize)
            throw new IndexOutOfBoundsException("Invalid column index: " + index);

        // padding
        for (int i = cells.size(); i <= index; i++) {
            cells.add(null);
        }

        cells.set(index, cell);
    }

    @Override
    public Object getCellData(int columnIndex) {
        IMutableCell cell = getCell(columnIndex);
        if (cell == null)
            return null;
        else
            return cell.getData();
    }

    @Override
    public synchronized void setCellData(int index, Object data) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Invalid column index: " + index);

        int maxSize = getRowSetMetadata().getColumnCount();
        if (getRowSetMetadata().isSparse())
            maxSize = DefaultRowSetMetadata.MAX_SPARSE_COLUMNS;

        if (index >= maxSize)
            throw new IndexOutOfBoundsException("Invalid column index: " + index);

        // padding
        for (int i = cells.size(); i <= index; i++) {
            cells.add(null);
        }

        IMutableCell cell = cells.get(index);
        if (cell == null) {
            cell = new MutableCell(this);
            setCell(index, cell);
        }
        cell.setData(data);
    }

    @Override
    public MutableCell createCell(int columnIndex) {
        return new MutableCell(this, columnIndex);
    }

    @Override
    public void addCell(IMutableCell cell) {
        MutableCell mutable = Nullables.upCast(cell, MutableCell.class, "cell");
        cells.add(mutable);
    }

    @Override
    public void removeCell(IMutableCell cell) {
        cells.remove(cell);
    }

    @Override
    public void removeCell(int columnIndex) {
        cells.remove(columnIndex);
    }

    @Override
    public boolean isSet(int index) {
        IMutableCell cell = getCell(index);
        if (cell == null)
            return false;
        return cell.isSet();
    }

    @Override
    public void append(Object data) {
        IMutableCell cell = addNewCell();
        cell.setData(data);
    }

    @Override
    public Iterable<? extends ICell> flatten() {
        return new Iterable<ICell>() {
            @NotNull
            @Override
            public Iterator<ICell> iterator() {
                return _iterator();
            }
        };
    }

    Iterator<ICell> _iterator() {
        int maxSize = getRowSetMetadata().getColumnCount();
        if (getRowSetMetadata().isSparse())
            maxSize = cells.size();
        final int retSize = maxSize;
        return new PrefetchedIterator<ICell>() {

            int i = 0;

            @Override
            protected ICell fetch() {
                if (i < retSize) {
                    i++;
                    return cells.get(i);
                }
                return end();
            }

        };
    }

    @Override
    public void jsonIn(@NotNull JsonVariant in, JsonFormOptions opts)
            throws ParseException {
        if (!in.isArray())
            throw new ParseException("expect array: " + in);

        JsonArray jarray = in.getArray();
        assert jarray != null;
        int jn = jarray.length();

        int cc = getRowSetMetadata().getColumnCount();
        List<IMutableCell> list = new ArrayList<>(cc);

        for (int i = 0; i < cc && i < jn; i++) {
            Object jsonValue = jarray.get(i);

            IColumnMetadata column = getRowSetMetadata().getColumn(i);
            Object cellData = column.readColumnJsonValue(jsonValue);

            IMutableCell cell = addNewCell();
            cell.setData(cellData);
        }
        this.cells = list;
    }

    @Override
    public final void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        jsonIn(JsonVariant.of(o), opts);
    }

    @Override
    public void readObject(IElement x_row)
            throws ParseException, LoaderException {
        int cc = getRowSetMetadata().getColumnCount();
        List<IMutableCell> list = new ArrayList<>();
        for (int i = 0; i < cc; i++) {
            IColumnMetadata column = getColumn(i);
            String tagName = column.getName();
            IElement x_cell = x_row.selectByTag(tagName).getFirst();
            if (x_cell != null) {
                Object cellData = column.readColumnXmlValue(x_cell);
                IMutableCell cell = addNewCell();
                cell.setData(cellData);
            } else {
                list.add(null);
            }
        }
        this.cells = list;
    }

    @Override
    public void readObjectBoxed(IElement x_row)
            throws ParseException, LoaderException {
        // XXX xpath for immediate children.
//        IElements x_row_v = x_rows.selectByTag("row");
//        IElement x_row = x_row_v.get(rowIndex);
        readObject(x_row);
    }

    @Override
    public String toString() {
        return cells.toString();
    }

}
