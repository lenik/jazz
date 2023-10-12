package net.bodz.bas.doc.node;

public class RunGroup
        extends AbstractRunGroup {

    public RunGroup(INode parent) {
        super(parent);
    }

    @Override
    public NodeType getType() {
        return NodeType.RUN_GROUP;
    }

    @Override
    public void accept(IDocVisitor visitor) {
        visitor.runGroup(this);
    }

}
