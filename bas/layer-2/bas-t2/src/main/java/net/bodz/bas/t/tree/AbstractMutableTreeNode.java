package net.bodz.bas.t.tree;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.meta.bean.Transient;

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
    public void clear() {
        Set<String> keySet = new HashSet<String>(childKeySet());
        for (String key : keySet)
            removeChild(key);
    }

    @Override
    public node_t getOrCreateChild(String key) {
        node_t child = getChild(key);
        if (child == null)
            child = addNewChild(key);
        return child;
    }

    @Override
    public node_t findOrCreatePath(String path) {
        if (path == null)
            throw new NullPointerException("path");

        @SuppressWarnings("unchecked")
        node_t _this = (node_t) this;

        if (path.isEmpty())
            return _this;

        int slash = path.indexOf('/');
        String key;
        if (slash == -1) {
            key = path;
            path = null;
        } else {
            key = path.substring(0, slash);
            path = path.substring(slash + 1);
        }

        if (".".equals(key))
            return findOrCreatePath(path);

        if ("..".equals(key)) {
            node_t next = getParent();
            if (next == null)
                next = _this;
            return next.findOrCreatePath(path);
        }

        node_t child = getOrCreateChild(key);
        if (child == null)
            return null;

        if (path == null)
            return child;
        else
            return child.findOrCreatePath(path);
    }

    @Override
    public node_t findOrCreate(Iterable<String> path) {
        if (path == null)
            throw new NullPointerException("path");

        @SuppressWarnings("unchecked")
        node_t _this = (node_t) this;

        node_t ptr = _this;
        for (String key : path) {
            if (".".equals(key))
                continue;

            if ("..".equals(key)) {
                node_t parent = getParent();
                if (parent == null)
                    ; // return null;
                ptr = parent;
                continue;
            }

            node_t child = ptr.getOrCreateChild(key);
            if (child == null)
                return null;
            ptr = child;
        }
        return ptr;
    }

}
