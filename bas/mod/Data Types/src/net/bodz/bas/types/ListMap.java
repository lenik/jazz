package net.bodz.bas.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListMap<K, V> implements Map<K, V> {

    private final List<K> keys;
    private final List<V> values;

    /**
     * @throws NullPointerException
     *             if either <code>keys</code> or <code>values</code> is
     *             <code>null</code>.
     */
    public ListMap(List<K> keys, List<V> values) {
        if (keys == null)
            throw new NullPointerException("keys"); //$NON-NLS-1$
        if (values == null)
            throw new NullPointerException("keys"); //$NON-NLS-1$
        this.keys = keys;
        this.values = values;
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.contains(value);
    }

    @Override
    public V get(Object key) {
        int index = keys.indexOf(key);
        if (index == -1)
            return null;
        return values.get(index);
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public V put(K key, V value) {
        int index = keys.indexOf(key);
        if (index == -1) {
            keys.add(key);
            values.add(value);
            return null;
        }
        keys.set(index, key);
        return values.set(index, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            K k = e.getKey();
            V v = e.getValue();
            put(k, v);
        }
    }

    @Override
    public V remove(Object key) {
        int index = keys.indexOf(key);
        if (index == -1)
            return null;
        @SuppressWarnings("unused")
        K prevKey = keys.remove(index);
        V prevValue = values.remove(index);
        return prevValue;
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<K>(keys);
    }

    @Override
    public Collection<V> values() {
        return values; // remove, retain, clear... do cascade!!!
    }

    public static class ArrayListMap<K, V> extends ListMap<K, V> {

        public ArrayListMap(int initialCapacity) {
            super(new ArrayList<K>(initialCapacity), //
                    new ArrayList<V>(initialCapacity));
        }

        public ArrayListMap() {
            this(10);
        }

    }

    public static class LinkedListMap<K, V> extends ListMap<K, V> {

        public LinkedListMap(List<K> keys, List<V> values) {
            super(new LinkedList<K>(), //
                    new LinkedList<V>());
        }

    }

}
