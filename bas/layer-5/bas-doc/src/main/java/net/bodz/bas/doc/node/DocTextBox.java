package net.bodz.bas.doc.node;

public class DocTextBox
        extends DocParGroup
        implements
            IDocRun {

    public DocTextBox(IDocNode parent) {
        super(parent);
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.textBox(this);
    }

}
