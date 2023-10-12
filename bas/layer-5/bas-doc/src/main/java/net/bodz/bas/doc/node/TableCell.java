package net.bodz.bas.doc.node;

public class TableCell
        extends AbstractParGroup {

    final TableRow row;

    boolean header;
    int rowSpan = 1;
    int columnSpan = 1;

    public TableCell(TableRow row) {
        super(row);
        this.row = row;
    }

    @Override
    public NodeType getType() {
        return NodeType.TABLE_CELL;
    }

    @Override
    public boolean isPar() {
        return false;
    }

    public TableRow getRow() {
        return row;
    }

    public Table getTable() {
        return row.getTable();
    }

    @Override
    public void buildText(StringBuilder a) {
        int n = pars.size();
        for (int i = 0; i < n; i++) {
            IPar par = pars.get(i);
            boolean last = i == n - 1;
            if (last)
                a.append(par.getText().trim());
            else
                par.buildText(a);
        }
    }

    @Override
    public void setText(String s) {
        pars.clear();
        addTextPar().addText(s);
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public int getColumnSpan() {
        return columnSpan;
    }

    public void setColumnSpan(int columnSpan) {
        this.columnSpan = columnSpan;
    }

    @Override
    public void accept(IDocVisitor visitor) {
        int index = getChildIndex();
        visitor.tableCell(this, index);
    }

}
