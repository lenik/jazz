package net.bodz.bas.t.tree;

import java.util.Collection;
import java.util.List;

public abstract class AbstractTreeNode<node_t extends ITreeNode>
        implements ITreeNode {

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public node_t getParent() {
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
    public abstract node_t getChild(String key);

    @Override
    public node_t getDescendant(String path) {
        int slash = path.indexOf('/');
        if (slash == -1)
            return getChild(path);

        String key = path.substring(0, slash);
        ITreeNode child = getChild(key);
        if (child == null)
            return null;

        path = path.substring(slash + 1);
        return (node_t) child.getDescendant(path);
    }

    @Override
    public node_t resolve(String path) {
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

        return _resolve(key, path);
    }

    protected node_t _resolve(String firstPathEntry, String remainingPath) {
        node_t child = getChild(firstPathEntry);
        if (child == null)
            return null;
        else
            return (node_t) child.resolve(remainingPath);
    }

    @Override
    public abstract Collection<? extends node_t> children();

    @Override
    public String keyOf(ITreeNode child) {
        List<String> keys = keysOf(child);
        if (keys.isEmpty())
            return null;
        else
            return keys.get(0);
    }

}
