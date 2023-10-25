package net.bodz.bas.doc.node;

import net.bodz.bas.doc.node.util.DocNodeLists;
import net.bodz.bas.doc.node.util.IDocNodeList;
import net.bodz.bas.doc.property.Color;

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

    Color highlight;

    public Color getHighlight() {
        return highlight;
    }

}
