package net.bodz.bas.disp.util;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.disp.naming.INamedNode;
import net.bodz.bas.disp.naming.NamedNode;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.util.Nullables;
import net.bodz.bas.util.iter.PrefetchedIterator;

public class OidTree<T>
        extends NamedNode
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private Class<T> dataType;

    private T data;
    private Map<Integer, OidTree<T>> children;

    public OidTree(Class<T> dataType, INamedNode parent) {
        super("(root)", dataType, parent);
        this.dataType = dataType;
    }

    public OidTree(String name, Class<T> dataType, INamedNode parent) {
        super(name, dataType, parent);
        this.dataType = dataType;
    }

    public T get() {
        return data;
    }

    public void set(T value) {
        this.data = value;
    }

    public synchronized boolean contains(int ord) {
        if (children == null)
            return false;
        return children.containsKey(ord);
    }

    public synchronized OidTree<T> get(int ord) {
        if (children == null)
            children = new HashMap<Integer, OidTree<T>>();

        OidTree<T> tree = children.get(ord);
        if (tree == null) {
            tree = new OidTree<T>(name, dataType, this);
            children.put(ord, tree);
        }
        return tree;
    }

    public synchronized void set(int ord, T value) {
        get(ord).set(value);
    }

    public void attach(int ord, OidTree<T> tree) {
        if (tree == null)
            children.remove(ord);
        else
            children.put(ord, tree);
    }

    public boolean contains(OidVector oid) {
        return contains(oid.vector);
    }

    public boolean contains(int... vector) {
        return _contains(vector, 0, vector.length);
    }

    protected boolean _contains(int[] vector, int start, int end) {
        OidTree<T> node = this;
        while (start < end) {
            int ord = vector[start++];
            if (!node.contains(ord))
                return false;
            node = node.get(ord);
        }
        return true;
    }

    public OidTree<T> get(OidVector oid) {
        return get(oid.vector);
    }

    public OidTree<T> get(int... vector) {
        return _get(vector, 0, vector.length);
    }

    protected OidTree<T> _get(int[] vector, int start, int end) {
        OidTree<T> node = this;
        while (start < end) {
            int ord = vector[start++];
            node = node.get(ord);
        }
        return node;
    }

    public void attach(int[] vector, OidTree<T> tree) {
        attach(vector, 0, vector.length, tree);
    }

    public void attach(int[] vector, int start, int end, OidTree<T> tree) {
        // Don't allow to replace the root.
        // OidTree<T> parent = getParent();
        OidTree<T> parent = null;

        OidTree<T> node = this;
        int leafOrd = -1;
        while (start < end) {
            leafOrd = vector[start++];
            parent = node;
            node = node.get(leafOrd);
        }

        if (parent == null)
            throw new IllegalUsageException("Can't replace the root of oid-tree");

        assert leafOrd != -1; // Notice: You can't use -1 as ord number.

        parent.attach(leafOrd, tree);
    }

    @Override
    public Object getChild(String childName) {
        String[] split = childName.split("/");
        int[] vector = new int[split.length];
        for (int i = 0; i < vector.length; i++) {
            String token = split[i];
            if (!StringPred.isNumber(token))
                return null;

            int num = Integer.parseInt(split[i]);
            vector[i] = num;
        }

        OidTree<T> childTree = get(vector);

        return childTree.data;
    }

    @Override
    public String findChild(Object obj) {
        Map<T, String> reverseMap = getReverseMap();
        return reverseMap.get(obj);
    }

    public Map<T, String> getReverseMap() {
        if (data == null && children == null)
            return Collections.emptyMap();

        Map<T, String> map = new IdentityHashMap<T, String>();
        dumpReverseMap(map, new StringBuilder());
        return map;
    }

    void dumpReverseMap(Map<T, String> map, StringBuilder prefix) {
        if (data != null)
            map.put(data, prefix.toString());

        if (children == null)
            return;

        if (prefix.length() != 0)
            prefix.append('/');

        int len = prefix.length();

        for (Entry<Integer, OidTree<T>> entry : children.entrySet()) {
            int ord = entry.getKey();
            OidTree<T> childTree = entry.getValue();

            prefix.setLength(len);
            prefix.append(ord);

            childTree.dumpReverseMap(map, prefix);
        }
    }

    @Override
    public Collection<String> getChildrenNames() {
        if (children == null || children.isEmpty())
            return Collections.emptyList();

        List<String> names = new ArrayList<String>();
        dumpChildNames(names, new StringBuilder());
        return names;
    }

    void dumpChildNames(List<String> list, StringBuilder prefix) {
        if (data != null)
            list.add(prefix.toString());

        if (children == null)
            return;

        if (prefix.length() != 0)
            prefix.append('/');

        int len = prefix.length();

        for (Entry<Integer, OidTree<T>> entry : children.entrySet()) {
            int ord = entry.getKey();
            OidTree<T> childTree = entry.getValue();

            prefix.setLength(len);
            prefix.append(ord);

            childTree.dumpChildNames(list, prefix);
        }
    }

    @Override
    public Collection<?> getChildren() {
        if (children == null || children.isEmpty())
            return Collections.emptyList();

        List<T> children = new ArrayList<T>();
        dumpChildren(children);

        return children;
    }

    public void dumpChildren(List<T> all) {
        if (data != null)
            all.add(data);

        if (children == null)
            return;

        for (OidTree<T> childTree : children.values())
            childTree.dumpChildren(all);
    }

    // XXX - Please rewrite this later.
    class ChildrenIterator
            extends PrefetchedIterator<Object> {

        final Iterator<Entry<Integer, OidTree<T>>> entries;
        Iterator<?> subIter;

        public ChildrenIterator() {
            entries = children.entrySet().iterator();
        }

        @Override
        protected Object fetch() {
            if (subIter != null) {
                if (subIter.hasNext())
                    return subIter.next();
                else
                    subIter = null;
            }

            while (entries.hasNext()) {
                Entry<Integer, OidTree<T>> entry = entries.next();
                OidTree<T> subnode = entry.getValue();

                if (subnode != null)
                    subIter = subnode.getChildren().iterator();

                if (subnode.data != null)
                    return subnode.data;

                return fetch();
            }

            return end();
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((children == null) ? 0 : children.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OidTree<?>))
            return false;

        OidTree<?> other = (OidTree<?>) obj;

        if (!Nullables.equals(children, other.children))
            return false;

        if (!Nullables.equals(data, other.data))
            return false;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + " => { ");

        for (String name : getChildrenNames()) {
            Object node = getChild(name);
            sb.append(name + " -> { " + node + " }\n");
        }

        sb.append(" }");
        return sb.toString();
    }
}