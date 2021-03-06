package net.bodz.bas.site.json;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class PathMap<T> {

    // String parentPath;
    Boolean order;
    char pathSeparator = '/';
    PathMapNode<T> root;
    boolean purgeEmptyChild;

    public PathMap(char pathSeparator) {
        this('/', Boolean.FALSE);
    }

    public PathMap(char pathSeparator, Boolean order) {
        this(pathSeparator, order, null);
    }

    public PathMap(char pathSeparator, Boolean order, PathMapNode<T> root) {
        this.order = order;
        this.pathSeparator = pathSeparator;
        if (root != null)
            this.root = root;
        else
            this.root = new PathMapNode<>(this);
    }

    <V> Map<String, V> newMap() {
        if (order == null)
            return new HashMap<>();
        if (order)
            return new TreeMap<>();
        else
            return new LinkedHashMap<>();
    }

    PathMapNode<T> resolveNode(String path, boolean create) {
        PathMapNode<T> start = root;
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
                start = start.getOrCreateChild(head);
            else {
                start = start.getChild(head);
                if (start == null)
                    break;
            }
        }
        return start;
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

    public synchronized T removeAttribute(String path) {
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
        subMap.purgeEmptyChild = this.purgeEmptyChild;
        return subMap;
    }

}
