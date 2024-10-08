package net.bodz.bas.t.tree;

import java.util.Map;

import net.bodz.bas.repr.form.SortOrder;

public class MapTreeNode<node_t extends IMutableTreeNode<node_t>>
        extends AbstractMapTreeNode<node_t> {

    private static final long serialVersionUID = 1L;

    public MapTreeNode() {
        super();
    }

    public MapTreeNode(SortOrder order) {
        super(order);
    }

    protected MapTreeNode(SortOrder order, Map<String, node_t> map) {
        super(order, map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public node_t addNewChild(String key) {
        node_t _this = (node_t) this;
        node_t child = new MapTreeNode<node_t>().attach(_this, key);
        putChild(key, child);
        return child;

    }

}
