package net.bodz.xml.xmod.modpdb;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private Table      table;
    private List<Cell> cells;

    protected Row() {
        cells = new ArrayList<Cell>();
    }

    public Row(Table table) {
        this();
        this.table = table;
    }

    @Deprecated
    public Row(Table table, int rowIndex) {
        this(table);
        int fields = table.fields.size();
        for (int fieldIndex = 0; fieldIndex < fields; fieldIndex++) {
            Column column = table.getColumn(fieldIndex);
            if (column != null) {
                Object cellValue = column.get(rowIndex);
                Cell cell = new Cell(fieldIndex, cellValue);
                cells.add(cell);
            }
        }
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void addCell(String name, String value) {
        int index = table.getFieldIndex(name);
        Cell cell = new Cell(index, value);
        cells.add(cell);
    }

    protected int getActualIndex(int fieldIndex) {
        for (int actual = 0; actual < cells.size(); actual++) {
            Cell cell = cells.get(actual);
            if (cell.getIndex() == fieldIndex)
                return actual;
        }
        return -1;
    }

    public boolean hasField(int fieldIndex) {
        return getActualIndex(fieldIndex) >= 0;
    }

    public Object getField(int fieldIndex) {
        int actualIndex = getActualIndex(fieldIndex);
        if (actualIndex == -1)
            return null;
        return cells.get(actualIndex).getValue();
    }
}
