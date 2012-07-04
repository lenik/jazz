package net.bodz.bas.collection.map;

import java.util.*;

public class TwoColumnMap<K, V>
        implements Map<K, V> {

    private final List<K> keyColumnList;
    private final List<V> valueColumnList;

    /**
     * @throws NullPointerException
     *             if either <code>keys</code> or <code>values</code> is <code>null</code>.
     */
    public TwoColumnMap(List<K> keyColumnList, List<V> valueColumnList) {
        if (keyColumnList == null)
            throw new NullPointerException("keyColumnList");
        if (valueColumnList == null)
            throw new NullPointerException("valueColumnList");
        this.keyColumnList = keyColumnList;
        this.valueColumnList = valueColumnList;
    }

    @Override
    public void clear() {
        keyColumnList.clear();
        valueColumnList.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return keyColumnList.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return valueColumnList.contains(value);
    }

    @Override
    public V get(Object key) {
        int index = keyColumnList.indexOf(key);
        if (index == -1)
            return null;
        return valueColumnList.get(index);
    }

    @Override
    public boolean isEmpty() {
        return keyColumnList.isEmpty();
    }

    @Override
    public V put(K key, V value) {
        int index = keyColumnList.indexOf(key);
        if (index == -1) {
            keyColumnList.add(key);
            valueColumnList.add(value);
            return null;
        }
        keyColumnList.set(index, key);
        return valueColumnList.set(index, value);
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
        int index = keyColumnList.indexOf(key);
        if (index == -1)
            return null;
        @SuppressWarnings("unused")
        K prevKey = keyColumnList.remove(index);
        V prevValue = valueColumnList.remove(index);
        return prevValue;
    }

    @Override
    public int size() {
        return keyColumnList.size();
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<K>(keyColumnList);
    }

    @Override
    public Collection<V> values() {
        return valueColumnList; // remove, retain, clear... do cascade!!!
    }

    public static class ArrayListMap<K, V>
            extends TwoColumnMap<K, V> {

        public ArrayListMap(int initialCapacity) {
            super(new ArrayList<K>(initialCapacity), //
                    new ArrayList<V>(initialCapacity));
        }

        public ArrayListMap() {
            this(10);
        }

    }

    public static class LinkedListMap<K, V>
            extends TwoColumnMap<K, V> {

        public LinkedListMap(List<K> keys, List<V> values) {
            super(new LinkedList<K>(), //
                    new LinkedList<V>());
        }

    }

}
