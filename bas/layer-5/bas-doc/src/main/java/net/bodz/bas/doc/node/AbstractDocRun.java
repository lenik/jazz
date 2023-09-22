package net.bodz.bas.doc.node;

import net.bodz.bas.t.list.AutoLists;
import net.bodz.bas.t.list.IAutoList;

public abstract class AbstractDocRun
        extends AbstractDocNode
        implements
            IDocRun {

    public AbstractDocRun(IDocNode parent) {
        super(parent);
    }

    @Override
    public boolean isRun() {
        return true;
    }

    @Override
    public IAutoList<? extends IDocNode> getChildren() {
        return AutoLists.emptyAutoList();
    }

}
