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
    protected final node_t _resolveChild(String childKey, String remainingPath) {
        node_t child = getChild(childKey);
        if (child == null) {
            child = newChild();
            putChild(childKey, child);
        }

        if (remainingPath == null)
            return child;
        else
            return (node_t) child.resolve(remainingPath);
    }

    @Override
    public void clear() {
        Set<String> keySet = new HashSet<String>(childKeySet());
        for (String key : keySet)
            removeChild(key);
    }

}
