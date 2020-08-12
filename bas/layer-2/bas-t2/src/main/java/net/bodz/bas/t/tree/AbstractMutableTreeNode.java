package net.bodz.bas.t.tree;

import java.beans.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.err.CreateException;

public abstract class AbstractMutableTreeNode<node_t extends IMutableTreeNode<node_t>>
        extends AbstractTreeNode<node_t>
        implements IMutableTreeNode<node_t>, Serializable {

    private static final long serialVersionUID = 1L;

    private node_t parent;

    public AbstractMutableTreeNode() {
    }

    public AbstractMutableTreeNode(node_t parent) {
        attach(parent);
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
    public <self_t extends node_t> self_t attach(node_t parent) {
        @SuppressWarnings("unchecked")
        self_t _this = (self_t) this;

        if (this.parent != parent) {
            detach();

            this.parent = parent;
            if (parent != null && parent.isMutable())
                parent.addChild(_this);
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

    protected abstract node_t newChild()
            throws CreateException;

    @Override
    protected final node_t _resolveChild(String childKey, String remainingPath) {
        node_t child = getChild(childKey);
        if (child == null) {
            child = newChild();
            putChild(childKey, child);
        }

        if (remainingPath == null)
            return child;
        else
            return child.resolve(remainingPath);
    }

    @Override
    public void clear() {
        Set<String> keySet = new HashSet<String>(childKeySet());
        for (String key : keySet)
            removeChild(key);
    }

}
