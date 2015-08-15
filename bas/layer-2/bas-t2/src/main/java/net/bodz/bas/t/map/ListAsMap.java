package net.bodz.bas.t.map;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.t.range.IntRange;

public class ListAsMap<V>
        implements Map<Integer, V> {

    private final List<V> list;

    public ListAsMap(List<V> list) {
        if (list == null)
            throw new NullPointerException("list");
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof Integer))
            return false;
        int index = (Integer) key;
        return index >= 0 && index < list.size();
    }

    @Override
    public boolean containsValue(Object value) {
        return list.contains(value);
    }

    @Override
    public Set<Integer> keySet() {
        return new IntRange(0, list.size());
    }

    @Override
    public Collection<V> values() {
        return list;
    }

    @Override
    public Set<Entry<Integer, V>> entrySet() {
        return new AbstractSet<Entry<Integer, V>>() {

            @Override
            public Iterator<Entry<Integer, V>> iterator() {
                return new Iterator<Entry<Integer, V>>() {

                    int i = 0;

                    @Override
                    public boolean hasNext() {
                        return i < list.size();
                    }

                    @Override
                    public Entry<Integer, V> next() {
                        if (i >= list.size())
                            return null;
                        final int idx = i++;
                        return new Entry<Integer, V>() {

                            @Override
                            public Integer getKey() {
                                return idx;
                            }

                            @Override
                            public V getValue() {
                                return list.get(idx);
                            }

                            @Override
                            public V setValue(V value) {
                                return list.set(idx, value);
                            }

                        };
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }

                };
            }

            @Override
            public int size() {
                return list.size();
            }

        };
    }

    @Override
    public V get(Object key) {
        if (!(key instanceof Integer))
            return null;
        int index = (Integer) key;
        if (index >= 0 && index < list.size())
            return list.get(index);
        return null;
    }

    @Override
    public V put(Integer key, V value) {
        return list.set(key, value);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends V> m) {
        for (Integer k : m.keySet())
            put(k, m.get(k));
    }

    /**
     * all entries with index > _index will be re-ordered.
     */
    @Override
    public V remove(Object _index) {
        if (!(_index instanceof Integer))
            return null;
        int index = (Integer) _index;
        return list.remove(index);
    }

    @Override
    public void clear() {
        list.clear();
    }

}
