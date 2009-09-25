package net.bodz.xml.models.pdb;

public class RowPartial implements Row {

    protected final Table table;
    protected final int[] indexes;
    protected Object[]    cells;

    public RowPartial(Table table, int... indexes) {
        assert table != null;
        this.table = table;

        assert indexes.length > 0;
        this.indexes = indexes;
    }

    public RowPartial(Table table, int[] indexes, Object[] cells) {
        this(table, indexes);
        setCells(cells);
    }

    public Table getTable() {
        return table;
    }

    public Object[] getCells() {
        return cells;
    }

    protected void initCells() {
        cells = new Object[indexes.length];
        for (int i = 0; i < indexes.length; i++)
            cells[i] = Cell.MISSING;
    }

    public void setCells(Object[] cells) {
        if (cells == null) {
            this.cells = null;
        } else {
            if (cells.length < indexes.length)
                throw new IllegalArgumentException("size of given cells is "
                        + cells.length + " < " + indexes.length);
            this.cells = cells;
        }
    }

    protected int getOffset(int index) {
        if (index < 0 || index >= table.sizeFields())
            throw new IndexOutOfBoundsException("" + index);

        for (int off = 0; off < indexes.length; off++)
            if (indexes[off] == index)
                return off;
        
        throw new IllegalArgumentException("field "
                + table.getField(index).getName()
                + " isn't used in this Row object");
    }

    public boolean has(int index) {
        int off = getOffset(index);
        if (cells == null)
            return false;
        return cells[off] != Cell.MISSING;
    }

    public Object get(int index) {
        int off = getOffset(index);
        if (cells == null)
            return Cell.MISSING;
        return cells[off];
    }

    public void set(int index, Object cell) {
        int off = getOffset(index);
        if (cells == null)
            initCells();
        cells[off] = cell;
    }

    public boolean has(String name) {
        return has(table.getFieldIndex(name));
    }

    public Object get(String name) {
        return get(table.getFieldIndex(name));
    }

    public void set(String name, Object cell) {
        set(table.getFieldIndex(name), cell);
    }

}
