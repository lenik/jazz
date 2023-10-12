package net.bodz.bas.doc.node;

import net.bodz.bas.err.ReadOnlyException;

public class Hr
        extends AbstractDocPar {

    public static final String TEXT = "----------\n";
    // "\n----------\n\n";

    public Hr(INode parent) {
        super(parent);
    }

    @Override
    public NodeType getType() {
        return NodeType.HR;
    }

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
    public void accept(IDocVisitor visitor) {
        visitor.hr(this);
    }

}
