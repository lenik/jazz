package net.bodz.bas.doc.node;

import net.bodz.bas.doc.node.util.DocNodeLists;
import net.bodz.bas.doc.node.util.IDocNodeList;

public abstract class AbstractDocRun
        extends AbstractDocNode
        implements
            IRun {

    public AbstractDocRun(INode parent) {
        super(parent);
    }

    @Override
    public boolean isRun() {
        return true;
    }

    @Override
    public IDocNodeList<? extends INode> getChildren() {
        return DocNodeLists.emptyList();
    }

    @Override
    protected void nodeProperties(IDocVisitor visitor) {
        // String text = getText();
        // if (text != null)
        // visitor.property("text", text);
    }

}
