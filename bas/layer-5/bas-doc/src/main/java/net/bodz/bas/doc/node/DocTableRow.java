package net.bodz.bas.doc.node;

import net.bodz.bas.t.list.AutoList;
import net.bodz.bas.t.list.IAutoList;

public class DocTableRow
        extends AbstractDocNode {

    int spanCount;
    public final IAutoList<DocTableCell> cells = new AutoList<>(() -> new DocTableCell(this));

    public DocTableRow(DocTable parent) {
        super(parent);
    }

    @Override
    public DocTable getParent() {
        return (DocTable) super.getParent();
    }

    public IAutoList<DocTableCell> getCells() {
        return cells;
    }

    @Override
    public IAutoList<DocTableCell> getChildren() {
        return cells;
    }

    @Override
    public void buildText(StringBuilder a) {
        int n = cells.size();
        for (int i = 0; i < n; i++) {
            boolean last = i == n - 1;
            DocTableCell cell = cells.get(i);
            if (!last)
                a.append(DocTable.CELL_SEP);
            cell.buildText(a);
        }
    }

    @Override
    public void setText(String s) {
        cells.clear();
        DocTableCell cell = new DocTableCell(this);
        cell.setText(s);
        cells.add(cell);
    }

    @Override
    public void nodeAccept(IDocVisitor visitor) {
        visitor.tableRow(this);
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
        for (DocTableCell cell : cells) {
            cell.accept(visitor);
        }
    }

}
