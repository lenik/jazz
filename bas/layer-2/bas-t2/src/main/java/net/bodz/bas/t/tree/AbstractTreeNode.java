package net.bodz.bas.t.tree;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.t.iterator.RecIterator;

public abstract class AbstractTreeNode<node_t extends ITreeNode<node_t>>
        implements ITreeNode<node_t> {

    @Override
    public boolean isMutable() {
        return false;
    }

    @SuppressWarnings("unchecked")
    public node_t getRoot() {
        ITreeNode<?> node = this;
        while (true) {
            ITreeNode<?> parent = node.getParent();
            if (parent == null)
                return (node_t) node;
            node = parent;
        }
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
        ITreeNode<?> child = getChild(childKey);
        return child != null;
    }

    @Override
    public boolean contains(ITreeNode<?> child) {
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
        path = path.substring(slash + 1);

        @SuppressWarnings("unchecked") node_t _this = (node_t) this;
        node_t next;

        if (".".equals(key))
            return getDescendant(path);

        if ("..".equals(key)) {
            next = getParent();
            if (next == null)
                next = _this;
        } else {
            next = getChild(key);
            if (next == null)
                return null;
        }

        return (node_t) next.getDescendant(path);
    }

    @Override
    public node_t resolve(String path) {
        if (path == null)
            throw new NullPointerException("path");

        @SuppressWarnings("unchecked") node_t _this = (node_t) this;

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
            return resolve(path);

        if ("..".equals(key)) {
            node_t next = getParent();
            if (next == null)
                next = _this;
            return (node_t) next.resolve(path);
        }

        return _resolveChild(key, path);
    }

    protected node_t _resolveChild(String childKey, String remainingPath) {
        node_t child = getChild(childKey);
        if (child == null)
            return null;
        else
            return (node_t) child.resolve(remainingPath);
    }

    @Override
    public abstract Collection<? extends node_t> getChildren();

    @Override
    public Iterable<? extends node_t> getDescendants() {
        @SuppressWarnings("unchecked") final node_t start = (node_t) this;
        return new Iterable<node_t>() {
            @Override
            public Iterator<node_t> iterator() {
                return new ChildRecIterator<node_t>(start);
            }
        };
    }

    @Override
    public String keyOf(ITreeNode<?> child) {
        List<String> keys = keysOf(child);
        if (keys.isEmpty())
            return null;
        else
            return keys.get(0);
    }

}

class ChildRecIterator<node_t extends ITreeNode<node_t>>
        extends RecIterator<node_t> {

    public ChildRecIterator(node_t start) {
        super(Arrays.asList(start).iterator()); // Iterators.once(start);
    }

    @Override
    protected Iterator<? extends node_t> expand(node_t element) {
        return element.getChildren().iterator();
    }

}
