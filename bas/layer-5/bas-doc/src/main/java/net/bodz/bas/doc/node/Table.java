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

    /**
     * Update cell.mergeTo of all cells.
     */
    public synchronized void updateMergeTos() {
        int maxColumnCount = getMaxColumnCount();
        for (int iCol = 0; iCol < maxColumnCount; iCol++)
            updateVMergeTo(iCol);
        updateHMergeTos();
    }

    synchronized void updateHMergeTos() {
        for (TableRow row : rows)
            row.updateHMergeTo();
    }

    /**
     * update all cell.mergeTo in a specific column
     */
    synchronized void updateVMergeTo(int iCol) {
        int nRow = rows.size();
        int rowSpanRemains = 0;
        TableCell mergeFrom = null;
        for (int iRow = 0; iRow < nRow; iRow++) {
            TableRow row = rows.get(iRow);
            int nCol = row.getChildCount();
            if (iCol >= nCol)
                continue;
            TableCell cell = row.cells.get(iCol);
            if (rowSpanRemains > 0) {
                cell.mergedTo = mergeFrom;
                rowSpanRemains--;
            } else {
                int span = cell.getRowSpan();
                if (span > 1) {
                    rowSpanRemains = span - 1;
                    mergeFrom = cell;
                } else {
                    // rowSpanRemains = 0;
                    // mergeFrom = null;
                }
            }
        }
    }

    public void dumpMergeTos(IPrintOut out) {
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
