package net.bodz.bas.t.tree;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractMutableTreeNode
        extends AbstractTreeNode
        implements IMutableTreeNode, Serializable {

    private static final long serialVersionUID = 1L;

    private ITreeNode parent;

    @Override
    public ITreeNode getParent() {
        return parent;
    }

    @Override
    public void setParent(ITreeNode parent) {
        this.parent = parent;
    }

    @Override
    public synchronized ITreeNode resolve(String path) {
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

        ITreeNode child = getChild(key);
        if (child == null) {
            child = newChild();
            putChild(key, child);
        }

        if (path == null)
            return child;
        else
            return child.getDescendant(path);
    }

    @Override
    public void clear() {
        Set<String> keySet = new HashSet<String>(childKeySet());
        for (String key : keySet)
            removeChild(key);
    }

}
