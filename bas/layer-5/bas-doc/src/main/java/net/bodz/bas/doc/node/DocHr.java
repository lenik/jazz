package net.bodz.bas.doc.node;

import net.bodz.bas.err.ReadOnlyException;

public class DocHr
        extends AbstractDocPar {

    public DocHr(IDocNode parent) {
        super(parent);
    }

    public static final String TEXT = "----------\n";
    // "\n----------\n\n";

    @Override
    public String getText() {
        return TEXT;
    }

    @Override
    public void buildText(StringBuilder a) {
        a.append(TEXT);
    }

    @Override
    public void setText(String s) {
        throw new ReadOnlyException();
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.hr(this);
    }

}
