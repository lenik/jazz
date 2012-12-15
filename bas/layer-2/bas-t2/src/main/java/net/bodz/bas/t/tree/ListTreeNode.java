package net.bodz.bas.t.tree;

import java.util.List;

import net.bodz.bas.err.CreateException;

public class ListTreeNode
        extends AbstractListTreeNode<ITreeNode> {

    private static final long serialVersionUID = 1L;

    public ListTreeNode(ITreeNode parent) {
        super(parent);
    }

    public ListTreeNode(ITreeNode parent, List<ITreeNode> list) {
        super(parent, list);
    }

    @Override
    protected ITreeNode newChild()
            throws CreateException {
        return new ListTreeNode(this);
    }

}
