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

    @Override
    public node_t getParent() {
        return parent;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setParent(ITreeNode parent) {
        this.parent = (node_t) parent;
    }

    protected abstract node_t newChild()
            throws CreateException;

    @Override
    public synchronized node_t resolve(String path) {
        if (path == null)
            throw new NullPointerException("path");

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
