package net.bodz.bas.doc.node;

import net.bodz.bas.t.list.AutoList;
import net.bodz.bas.t.list.IAutoList;

public class TableRow
        extends AbstractDocNode {

    int spanCount;
    public final IAutoList<TableCell> cells = new AutoList<>(() -> new TableCell(this));

    public TableRow(Table parent) {
        super(parent);
    }

    @Override
    public NodeType getType() {
        return NodeType.TABLE_ROW;
    }

    @Override
    public Table getParent() {
        return (Table) super.getParent();
    }

    public Table getTable() {
        return (Table) super.getParent();
    }

    public IAutoList<TableCell> getCells() {
        return cells;
    }

    @Override
    public IAutoList<TableCell> getChildren() {
        return cells;
    }

    public int getRowIndex() {
        return super.getChildIndex();
    }

    @Override
    public void buildText(StringBuilder a) {
        int n = cells.size();
        for (int i = 0; i < n; i++) {
            boolean last = i == n - 1;
            TableCell cell = cells.get(i);
            if (!last)
                a.append(Table.CELL_SEP);
            cell.buildText(a);
        }
    }

    @Override
    public void setText(String s) {
        cells.clear();
        TableCell cell = addCell();
        cell.setText(s);
        cells.add(cell);
    }

    @Override
    public void nodeAccept(IDocVisitor visitor) {
        int index = getRowIndex();
        visitor.tableRow(this, index);
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
        for (TableCell cell : cells) {
            cell.accept(visitor);
        }
    }

    public TableCell addCell() {
        return cells.append();
    }

}
