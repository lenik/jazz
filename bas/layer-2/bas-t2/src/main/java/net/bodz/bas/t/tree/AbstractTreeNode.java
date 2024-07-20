package net.bodz.bas.t.tree;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.meta.bean.Transient;
import net.bodz.bas.t.iterator.RecIterator;

public abstract class AbstractTreeNode<node_t extends ITreeNode<node_t>>
        implements
            ITreeNode<node_t> {

    @Transient
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

    @Transient
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
    public node_t findPath(String path) {
        int slash = path.indexOf('/');
        if (slash == -1)
            return getChild(path);

        String key = path.substring(0, slash);
        path = path.substring(slash + 1);

        @SuppressWarnings("unchecked")
        node_t _this = (node_t) this;
        node_t next;

        if (".".equals(key))
            return findPath(path);

        if ("..".equals(key)) {
            next = getParent();
            if (next == null)
                next = _this;
        } else {
            next = getChild(key);
            if (next == null)
                return null;
        }

        return next.findPath(path);
    }

    @Override
    public node_t find(Iterable<String> path) {
        @SuppressWarnings("unchecked")
        node_t node = (node_t) this;

        for (String key : path) {
            if (".".equals(key))
                continue;

            if ("..".equals(key)) {
                node_t next = getParent();
                if (next == null)
                    continue; // return null;
                node = next;
                continue;
            }

            node_t next = getChild(key);
            if (next == null)
                return null;
        }
        return node;
    }

    @Override
    public abstract Collection<? extends node_t> getChildren();

    @Override
    public Iterable<? extends node_t> descendants() {
        @SuppressWarnings("unchecked")
        final node_t start = (node_t) this;
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

    @Override
    public void accept(ITreeNodeVisitor<? super node_t> visitor) {
        @SuppressWarnings("unchecked")
        node_t _this = (node_t) this;
        visitor.node(_this);
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
