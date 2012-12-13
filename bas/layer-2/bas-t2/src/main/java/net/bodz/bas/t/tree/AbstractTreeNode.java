package net.bodz.bas.t.tree;

import java.util.List;

public abstract class AbstractTreeNode
        implements ITreeNode {

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public ITreeNode getParent() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(String childKey) {
        ITreeNode child = getChild(childKey);
        return child != null;
    }

    @Override
    public boolean contains(ITreeNode child) {
        String key = keyOf(child);
        return key != null;
    }

    @Override
    public ITreeNode getDescendant(String path) {
        int slash = path.indexOf('/');
        if (slash == -1)
            return getChild(path);

        String key = path.substring(0, slash);
        ITreeNode child = getChild(key);
        if (child == null)
            return null;

        path = path.substring(slash + 1);
        return child.getDescendant(path);
    }

    @Override
    public String keyOf(ITreeNode child) {
        List<String> keys = keysOf(child);
        if (keys.isEmpty())
            return null;
        else
            return keys.get(0);
    }

}
