package net.bodz.bas.t.tree;

import java.util.List;

import net.bodz.bas.err.CreateException;

public class ListTreeNode
        extends AbstractListTreeNode<ITreeNode> {

    private static final long serialVersionUID = 1L;

    public ListTreeNode() {
        super();
    }

    public ListTreeNode(List<ITreeNode> list) {
        super(list);
    }

    @Override
    protected ITreeNode newChild()
            throws CreateException {
        return new ListTreeNode();
    }

}
