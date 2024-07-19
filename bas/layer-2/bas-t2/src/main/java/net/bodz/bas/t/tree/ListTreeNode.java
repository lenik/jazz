package net.bodz.bas.t.tree;

import java.util.List;

public class ListTreeNode<node_t extends IMutableTreeNode<node_t>>
        extends AbstractListTreeNode<node_t> {

    private static final long serialVersionUID = 1L;

    public ListTreeNode() {
        super();
    }

    public ListTreeNode(List<node_t> list) {
        super(list);
    }

    @SuppressWarnings("unchecked")
    @Override
    public node_t addNewChild(String key) {
        int id;
        try {
            id = Integer.parseInt(key);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }

        if (id != size())
            throw new IllegalArgumentException("bad key (index): " + key);

        node_t _this = (node_t) this;
        node_t child = new ListTreeNode<node_t>().attach(_this, key);
        list.add(child);
        return child;
    }

}
