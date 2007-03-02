package net.bodz.xml.xmod.modpdb;

public class RowFull implements Row {

    private final Table table;
    private final int   size;
    private Object[]    cells;

    public RowFull(Table table) {
        assert table != null;
        this.table = table;

        size = table.sizeFields();
        assert size > 0;
    }

    public RowFull(Table table, Object[] cells) {
        this(table);
        setCells(cells);
    }

    public Table getTable() {
        return table;
    }

    public Object[] getCells() {
        return cells;
    }

    protected void initCells() {
        cells = new Object[size];
        for (int i = 0; i < size; i++)
            cells[i] = Cell.MISSING;
    }

    public void setCells(Object[] cells) {
        if (cells == null) {
            this.cells = null;
        } else {
            if (cells.length < size)
                throw new IllegalArgumentException("size of given cells is "
                        + cells.length + " < " + size);
            this.cells = cells;
        }
    }

    public boolean has(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("" + index);
        if (cells == null)
            return false;
        return cells[index] != Cell.MISSING;
    }

    public Object get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("" + index);
        if (cells == null)
            return Cell.MISSING;
        return cells[index];
    }

    public void set(int index, Object cell) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("" + index);
        if (cells == null)
            initCells();
        cells[index] = cell;
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
