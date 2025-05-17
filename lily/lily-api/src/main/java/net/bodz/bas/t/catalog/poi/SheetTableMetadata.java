package net.bodz.bas.t.catalog.poi;

import java.util.List;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.catalog.DefaultTableMetadata;
import net.bodz.bas.t.catalog.IColumnMetadata;

public class SheetTableMetadata
        extends DefaultTableMetadata {

    public SheetTableMetadata() {
    }

    @Override
    public boolean isSparse() {
        return true;
    }

    @NotNull
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
        SheetColumn column = new SheetColumn(this);
        int newColumnIndex = getColumnCount();
        column.setColumnIndex(newColumnIndex);
        return column;
    }

    @Override
    public SheetColumn addNewColumn(String name) {
        return (SheetColumn) super.addNewColumn(name);
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
