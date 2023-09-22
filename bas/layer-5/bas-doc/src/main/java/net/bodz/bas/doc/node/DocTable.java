package net.bodz.bas.doc.node;

import net.bodz.bas.t.list.AutoList;
import net.bodz.bas.t.list.IAutoList;

public class DocTable
        extends AbstractDocPar {

    public static final String END_OF_ROW = "\n";
    public static final String CELL_SEP = "\t";

    public final IAutoList<DocTableRow> rows = new AutoList<>(() -> new DocTableRow(this));

    public DocTable(IDocNode parent) {
        super(parent);
    }

    @Override
    public IAutoList<DocTableRow> getChildren() {
        return rows;
    }

    @Override
    public void buildText(StringBuilder a) {
        for (DocTableRow row : rows) {
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
    public void nodeAccept(IDocVisitor visitor) {
        visitor.table(this);
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
        for (DocTableRow row : rows)
            row.accept(visitor);
    }

}
