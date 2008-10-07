package net.bodz.bas.types;

import java.util.AbstractList;
import java.util.HashMap;

public class IndexMap<V> extends AbstractList<V> {

    private static final long   serialVersionUID = -3711088749059166963L;

    private HashMap<Integer, V> store;
    private int                 size;
    private int                 nextFree;

    public IndexMap() {
        store = new HashMap<Integer, V>();
    }

    public IndexMap(int size) {
        store = new HashMap<Integer, V>(); // size
        this.size = size;
    }

    // List

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public V get(int index) {
        return store.get(index);
    }

    @Override
    public V set(int index, V element) {
        if (index >= size)
            size = index + 1;
        if (index == nextFree)
            alloc();
        return store.put(index, element);
    }

    int alloc() {
        int last = nextFree;
        while (++nextFree <= size)
            if (!contains(nextFree))
                break;
        return last;
    }

    public boolean contains(int index) {
        return store.containsKey(index);
    }

    @Override
    public boolean add(V e) {
        throw new UnsupportedOperationException("see addFree");
    }

    public int addFree(V e) {
        int index = alloc();
        set(index, e);
        return index;
    }

    @Override
    public V remove(int index) {
        if (index < nextFree)
            nextFree = index;
        return store.remove(index);
    }

    public HashMap<Integer, V> getMap() {
        return store;
    }

}
