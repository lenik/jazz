package net.bodz.bas.types;

import java.util.TreeMap;

public class IndexMap<V> extends TreeMap<Integer, V> {

    private static final long serialVersionUID = -3711088749059166963L;

    private int               size;
    private int               nextFree;

    // List

    public IndexMap() {
    }

    public IndexMap(int size) {
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    public V get(int index) {
        return super.get(index);
    }

    public V set(int index, V element) {
        if (index >= size)
            size = index + 1;
        if (index == nextFree)
            alloc();
        return super.put(index, element);
    }

    int alloc() {
        int last = nextFree;
        while (++nextFree <= size)
            if (!contains(nextFree))
                break;
        return last;
    }

    public boolean contains(int index) {
        return containsKey(index);
    }

    public boolean add(V e) {
        throw new UnsupportedOperationException("see addFree");
    }

    public int addFree(V e) {
        int index = alloc();
        set(index, e);
        return index;
    }

    public V remove(int index) {
        if (index < nextFree)
            nextFree = index;
        return remove(index);
    }

}
