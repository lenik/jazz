package net.bodz.bas.t.tree;

import java.util.Map;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.repr.form.SortOrder;

public class MapTreeNode<node_t extends IMutableTreeNode<node_t>>
        extends AbstractMapTreeNode<node_t> {

    private static final long serialVersionUID = 1L;

    public MapTreeNode() {
        super();
    }

    public MapTreeNode(node_t parent) {
        super(parent);
    }

    public MapTreeNode(node_t parent, Map<String, node_t> map) {
        super(parent, map);
    }

    public MapTreeNode(SortOrder order) {
        super(order);
    }

    public MapTreeNode(node_t parent, SortOrder order) {
        super(parent, order);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected node_t newChild()
            throws CreateException {
        return (node_t) new MapTreeNode<node_t>((node_t) this);
    }

}
