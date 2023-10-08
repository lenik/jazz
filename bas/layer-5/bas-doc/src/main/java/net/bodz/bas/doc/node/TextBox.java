package net.bodz.bas.doc.node;

public class TextBox
        extends AbstractParGroup
        implements
            IRun {

    public TextBox(INode parent) {
        super(parent);
    }

    @Override
    public NodeType getType() {
        return NodeType.TEXT_BOX;
    }

    @Override
    public boolean canReduce() {
        return pars.isEmpty();
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.textBox(this);
    }

}
