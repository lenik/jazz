package net.bodz.bas.t.tree;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.meta.bean.Transient;
import net.bodz.bas.repr.form.SortOrder;

public abstract class AbstractMutableTreeNode<node_t extends IMutableTreeNode<node_t>>
        extends AbstractTreeNode<node_t>
        implements
            IMutableTreeNode<node_t>,
            Serializable {

    private static final long serialVersionUID = 1L;

    private node_t parent;

    public AbstractMutableTreeNode() {
    }

    @Transient
    @Override
    public boolean isMutable() {
        return true;
    }

    @Transient
    @Override
    public node_t getParent() {
        return parent;
    }

    @Override
    public <self_t extends node_t> self_t detach() {
        @SuppressWarnings("unchecked")
        self_t _this = (self_t) this;

        if (parent != null) {
            if (parent.isMutable()) {
                for (String key : parent.keysOf(this))
                    parent.removeChild(key);
            }
            parent = null;
        }

        return _this;
    }

    @Override
    public <self_t extends node_t> self_t attach(node_t parent, String key) {
        @SuppressWarnings("unchecked")
        self_t _this = (self_t) this;

        if (this.parent != parent) {
            detach();

            if (parent != null && parent.isMutable())
                parent.putChild(key, _this);
        }

        return _this;
    }

    @Override
    protected final node_t _resolveChild(String childKey) {
        node_t child = getChild(childKey);
        if (child == null)
            child = addNewChild(childKey);
        return child;
    }

    @Override
    public void clear() {
        Set<String> keySet = new HashSet<String>(childKeySet());
        for (String key : keySet)
            removeChild(key);
    }

    static class _Builder<node_t extends IMutableTreeNode<node_t>> {

        SortOrder order = SortOrder.NONE;
        node_t parent;
        String key;

        public void order(SortOrder order) {
            this.order = order;
        }

        public void parent(node_t parent, String key) {
            this.parent = parent;
            this.key = key;
        }

    }

}
