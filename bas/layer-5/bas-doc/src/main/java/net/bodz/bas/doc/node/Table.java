package net.bodz.bas.doc.node;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.t.list.AutoList;
import net.bodz.bas.t.list.IAutoList;

public class Table
        extends AbstractDocPar {

    public static final String END_OF_ROW = "\n";
    public static final String CELL_SEP = "\t";

    public final IAutoList<TableRow> rows = new AutoList<>(() -> new TableRow(this));

    public int firstRows;
    public int lastRows;
    public int firstColumns;
    public int lastColumns;
    public boolean hBands;
    public boolean vBands;

    public Table(INode parent) {
        super(parent);
    }

    @Override
    public NodeType getType() {
        return NodeType.TABLE;
    }

    @Override
    public IAutoList<TableRow> getChildren() {
        return rows;
    }

    public int getRowCount() {
        return rows.size();
    }

    public RowPosition getRowPosition(int rowIndex) {
        if (rowIndex < firstRows)
            return RowPosition.TOP;
        else if (rowIndex >= getRowCount() - lastRows)
            return RowPosition.BOTTOM;
        else
            return RowPosition.BODY;
    }

    public int getMaxColumnCount() {
        int max = 0;
        for (TableRow row : rows) {
            int n = row.cells.size();
            if (n > max)
                max = n;
        }
        return max;
    }

    /**
     * @throws IndexOutOfBoundsException
     */
    public TableRow getRow(int rowIndex) {
        return rows.get(rowIndex);
    }

    /**
     * @throws IndexOutOfBoundsException
     */
    public TableCell getCell(int rowIndex, int columnIndex) {
        TableRow row = getRow(rowIndex);
        TableCell cell = row.getCell(columnIndex);
        return cell;
    }

    @Override
    public void buildText(StringBuilder a) {
        for (TableRow row : rows) {
            row.buildText(a);
            a.append(END_OF_ROW);
        }
    }

    @Override
    public void setText(String s) {
        rows.clear();
        rows.append().cells.append().addTextPar().addText(s);
    }

    @Override
    public void accept(IDocVisitor visitor) {
        visitor.table(this);
    }

    public TableRow addRow() {
        return rows.append();
    }

    public synchronized void updateCellMerges() {
        int maxColumnCount = getMaxColumnCount();
        for (int c = 0; c < maxColumnCount; c++)
            updateVMerge(c);
        updateHMerge();
    }

    synchronized void updateHMerge() {
        for (TableRow row : rows)
            row.updateHMerge();
    }

    synchronized void updateVMerge(int columnIndex) {
        int n = rows.size();
        int mergeMore = 0;
        TableCell orig = null;
        for (int r = 0; r < n; r++) {
            TableRow row = rows.get(r);
            int cc = row.getChildCount();
            if (columnIndex >= cc)
                continue;
            TableCell cell = row.cells.get(columnIndex);
            if (mergeMore > 0) {
                cell.mergedTo = orig;
                mergeMore--;
            } else {
                int span = cell.getRowSpan();
                if (span > 1) {
                    mergeMore = span - 1;
                    orig = cell;
                } else {
                    // mergeMore = 0;
                    // orig = null;
                }
            }
        }
    }

    public void dumpMerges(IPrintOut out) {
        int h = rows.size();
        for (int y = 0; y < h; y++) {
            TableRow row = rows.get(y);
            int w = row.cells.size();
            out.print("    ");
            for (int x = 0; x < w; x++) {
                TableCell cell = row.cells.get(x);
                char type = cell.mergedTo == null ? 'o' : '_';
                out.print(type);
            }
            out.println();
        }
        out.println();
    }

}
