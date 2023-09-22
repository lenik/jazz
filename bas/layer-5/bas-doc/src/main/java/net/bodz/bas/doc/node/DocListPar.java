package net.bodz.bas.doc.node;

import net.bodz.bas.t.list.IAutoList;

public class DocListPar
        extends DocParGroup {

    int level;

    boolean ordered;
    String shape = "*";
    int startNumber;

    public final IAutoList<IDocPar> items = super.pars;

    public DocListPar(IDocNode parent) {
        this(parent, false);
    }

    public DocListPar(IDocNode parent, boolean ordered) {
        super(parent);
        this.ordered = ordered;
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.list(this);
    }

    @Override
    public String toString() {
        StringBuilder a = new StringBuilder();
        int i = 1;
        for (IDocPar item : pars) {
            if (ordered) {
                a.append(i);
                a.append(".");
            } else {
                a.append(shape);
            }
            a.append(' ');
            item.buildText(a);
            a.append("\n");
        }
        return a.toString();
    }

}
