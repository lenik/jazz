package net.bodz.bas.doc.node;

public class DocTableCell
        extends DocParGroup {

    final DocTableRow row;

    boolean header;
    int rowSpan;
    int columnSpan;

    public DocTableCell(DocTableRow row) {
        super(row);
        this.row = row;
    }

    public DocTableRow getRow() {
        return row;
    }

    @Override
    public void buildText(StringBuilder a) {
        int n = pars.size();
        for (int i = 0; i < n; i++) {
            IDocPar par = pars.get(i);
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
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.tableCell(this);
    }

}
