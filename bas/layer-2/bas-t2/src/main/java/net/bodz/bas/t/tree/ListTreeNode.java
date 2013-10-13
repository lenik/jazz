package net.bodz.bas.t.tree;

import java.util.List;

import net.bodz.bas.err.CreateException;

public class ListTreeNode<node_t extends IMutableTreeNode<node_t>>
        extends AbstractListTreeNode<node_t> {

    private static final long serialVersionUID = 1L;

    public ListTreeNode(node_t parent) {
        super(parent);
    }

    public ListTreeNode(node_t parent, List<node_t> list) {
        super(parent, list);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected node_t newChild()
            throws CreateException {
        return (node_t) new ListTreeNode<node_t>((node_t) this);
    }

}
