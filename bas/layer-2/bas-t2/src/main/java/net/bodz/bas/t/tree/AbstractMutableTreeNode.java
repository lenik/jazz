package net.bodz.bas.t.tree;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.err.CreateException;

public abstract class AbstractMutableTreeNode<node_t extends ITreeNode>
        extends AbstractTreeNode<node_t>
        implements IMutableTreeNode, Serializable {

    private static final long serialVersionUID = 1L;

    private node_t parent;

    public AbstractMutableTreeNode(node_t parent) {
        this.parent = parent;
    }

    @Override
    public node_t getParent() {
        return parent;
    }

    @Override
    public node_t detach() {
        if (parent != null && parent.isMutable()) {
            IMutableTreeNode _parent = (IMutableTreeNode) parent;
            for (String key : _parent.keysOf(this))
                _parent.removeChild(key);
        }
        parent = null;

        @SuppressWarnings("unchecked") node_t _this = (node_t) this;
        return _this;
    }

    @Override
    public void attach(ITreeNode parent, String key) {
        detach();

        if (parent != null && parent.isMutable()) {
            IMutableTreeNode _parent = (IMutableTreeNode) parent;
            _parent.putChild(key, this);
        }
    }

    protected abstract node_t newChild()
            throws CreateException;

    @Override
    public synchronized node_t resolve(String path) {
        if (path == null)
            throw new NullPointerException("path");

        if (path.isEmpty()) {
            @SuppressWarnings("unchecked") node_t _this = (node_t) this;
            return _this;
        }

        int slash = path.indexOf('/');
        String key;
        if (slash == -1) {
            key = path;
            path = null;
        } else {
            key = path.substring(0, slash);
            path = path.substring(slash + 1);
        }

        node_t child = getChild(key);
        if (child == null) {
            child = newChild();
            putChild(key, child);
        }

        if (path == null)
            return child;
        else
            return (node_t) child.getDescendant(path);
    }

    @Override
    public void clear() {
        Set<String> keySet = new HashSet<String>(childKeySet());
        for (String key : keySet)
            removeChild(key);
    }

}
