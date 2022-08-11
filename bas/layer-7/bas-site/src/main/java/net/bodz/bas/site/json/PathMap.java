package net.bodz.bas.site.json;

import java.util.Map;

import net.bodz.bas.repr.form.SortOrder;

public class PathMap<T> {

    // String parentPath;
    final SortOrder order;
    final char pathSeparator;

    final PathMapNode<T> root;

    public PathMap(char pathSeparator) {
        this('/', SortOrder.KEEP);
    }

    public PathMap(char pathSeparator, SortOrder order) {
        this(pathSeparator, order, null);
    }

    public PathMap(char pathSeparator, SortOrder order, PathMapNode<T> root) {
        this.order = order;
        this.pathSeparator = pathSeparator;
        if (root != null)
            this.root = root;
        else
            this.root = new PathMapNode<>(this);
    }

    <V> Map<String, V> newMap() {
        return order.newMap();
    }

    PathMapNode<T> resolveNode(String path, boolean create) {
        PathMapNode<T> node = root;
        while (path != null && !path.isEmpty()) {
            int pos = path.indexOf(pathSeparator);
            String head = path;
            if (pos == -1)
                path = null;
            else {
                head = path.substring(0, pos);
                path = path.substring(pos + 1);
            }
            if (create)
                node = node.getOrCreateChild(head);
            else {
                node = node.getChild(head);
                if (node == null)
                    break;
            }
        }
        return node;
    }

    public T getAttribute(String path) {
        if (path == null)
            throw new NullPointerException("path");
        PathMapNode<T> node = root;
        int pos = path.lastIndexOf(pathSeparator);
        if (pos != -1) {
            String dir = path.substring(0, pos);
            path = path.substring(pos + 1);
            node = resolveNode(dir, false);
            if (node == null)
                return null;
        }
        return node.attributes.get(path);
    }

    public synchronized T setAttribute(String path, T newValue) {
        PathMapNode<T> node = root;
        int pos = path.lastIndexOf(pathSeparator);
        if (pos != -1) {
            String dir = path.substring(0, pos);
            path = path.substring(pos + 1);
            node = resolveNode(dir, true);
        }
        return node.attributes.put(path, newValue);
    }

    public synchronized T removeAttribute(String path, boolean purgeEmptyChild) {
        PathMapNode<T> node = root;
        int pos = path.lastIndexOf(pathSeparator);
        String dir = null;
        String base = path;
        if (pos != -1) {
            dir = path.substring(0, pos);
            base = path.substring(pos + 1);
            node = resolveNode(dir, false);
            if (node == null)
                return null;
        }
        T value = node.attributes.remove(base);
        if (purgeEmptyChild) {
            while (dir != null) {
                if (!node.attributes.isEmpty())
                    break;
                if (!node.children.isEmpty())
                    break;
                PathMapNode<T> parent = null;
                pos = dir.lastIndexOf(pathSeparator);
                if (pos != -1) {
                    base = dir.substring(pos + 1);
                    dir = dir.substring(0, pos);
                    parent = resolveNode(dir, false);
                } else {
                    base = dir;
                    dir = null;
                    parent = root;
                }
                parent.children.remove(base);
                node = parent;
            }
        }
        return value;
    }

    public PathMapNode<T> getRoot() {
        return root;
    }

    public PathMap<T> select(String path) {
        PathMapNode<T> node = resolveNode(path, true);
        if (node == root)
            return this;
        PathMap<T> subMap = new PathMap<>(pathSeparator, order, node);
        return subMap;
    }

}
