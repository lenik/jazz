package net.bodz.bas.doc.node;

import net.bodz.bas.t.list.AutoList;
import net.bodz.bas.t.list.IAutoList;

public class TableRow
        extends AbstractDocNode {

    int spanCount;
    public final IAutoList<TableCell> cells = new AutoList<>(() -> new TableCell(this));

    public boolean borderTop;
    public boolean borderBottom;

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

    public TableCell getCell(int columnIndex) {
        TableCell cell = cells.get(columnIndex);
        return cell;
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
    public void accept(IDocVisitor visitor) {
        int rowIndex = getRowIndex();
        visitor.tableRow(this, rowIndex);
    }

    public TableCell addCell() {
        return cells.append();
    }

    public void expandLastColumn() {
        Table table = this.getTable();
        int maxColumnCount = table.getMaxColumnCount();

        int nActual = cells.size();
        TableCell lastCell = this.cells.get(nActual - 1);
        int nEmpty = maxColumnCount - nActual;
        lastCell.setColumnSpan(nEmpty + 1);
    }

    public synchronized void updateHMerge() {
        int n = cells.size();
        int mergeMore = 0;
        TableCell orig = null;
        for (int c = 0; c < n; c++) {
            TableCell cell = cells.get(c);
            if (mergeMore > 0) {
                cell.mergedTo = orig;
                mergeMore--;
            } else {
                if (cell.mergedTo != null) // v-orig
                    cell = cell.mergedTo;
                int span = cell.getColumnSpan();
                if (span > 1) {
                    orig = cell;
                    mergeMore = span - 1;
                } else {
                    // orig = null;
                    // mergeMore = 0;
                }
            }
        }
    }

}
