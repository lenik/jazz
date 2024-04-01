package net.bodz.bas.t.catalog.poi;

import java.util.List;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.t.catalog.DefaultTableMetadata;
import net.bodz.bas.t.catalog.IColumnMetadata;

public class SheetTableMetadata
        extends DefaultTableMetadata {

    public SheetTableMetadata() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends SheetColumn> getColumns() {
        return (List<? extends SheetColumn>) super.getColumns();
    }

    @Override
    public int getColumnCount() {
        return super.getColumnCount();
    }

    @Override
    public SheetColumn getColumn(int index) {
        return (SheetColumn) super.getColumn(index);
    }

    @Override
    public synchronized SheetColumn getColumn(String name, boolean ignoreCase) {
        return (SheetColumn) super.getColumn(name, ignoreCase);
    }

    @Override
    protected SheetColumn createColumn() {
        return new SheetColumn(this, getColumnCount());
    }

    @Override
    public SheetColumn addNewColumn() {
        return (SheetColumn) super.addNewColumn();
    }

    @Override
    public void addColumn(IColumnMetadata column) {
        SheetColumn sheetColumn = Nullables.upCast(column, SheetColumn.class, "column");
        super.addColumn(sheetColumn);
    }

    public void addColumn(SheetColumn column) {
        super.addColumn(column);
    }

    @Override
    public void setColumn(int index, IColumnMetadata column) {
        SheetColumn sheetColumn = Nullables.upCast(column, SheetColumn.class, "column");
        super.setColumn(index, sheetColumn);
    }

    public void setColumn(int index, SheetColumn column) {
        super.setColumn(index, column);
    }

}
