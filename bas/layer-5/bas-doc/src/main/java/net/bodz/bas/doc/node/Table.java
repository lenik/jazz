package net.bodz.bas.doc.node;

import net.bodz.bas.doc.io.TableHeaderPosition;
import net.bodz.bas.t.list.AutoList;
import net.bodz.bas.t.list.IAutoList;

public class Table
        extends AbstractDocPar {

    public static final String END_OF_ROW = "\n";
    public static final String CELL_SEP = "\t";

    public static final TableHeaderPosition defaultHeaderPosition //
            = TableHeaderPosition.TOP;

    TableHeaderPosition headerPosition = defaultHeaderPosition;

    public final IAutoList<TableRow> rows = new AutoList<>(() -> new TableRow(this));

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

    public int getMaxColumnCount() {
        int max = 0;
        for (TableRow row : rows) {
            int n = row.cells.size();
            if (n > max)
                max = n;
        }
        return max;
    }

    public TableHeaderPosition getHeaderPosition() {
        return headerPosition;
    }

    public void setHeaderPosition(TableHeaderPosition headerPosition) {
        this.headerPosition = headerPosition;
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

}
