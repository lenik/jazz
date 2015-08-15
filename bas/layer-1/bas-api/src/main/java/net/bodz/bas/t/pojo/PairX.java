package net.bodz.bas.t.pojo;

import java.util.*;

public class PairX<K, V>
        extends Pair<K, V> {

    private static final long serialVersionUID = 1L;

    // Map Interface

    public void clear() {
        first = null;
    }

    public boolean containsKey(Object key) {
        if (key == null)
            throw new NullPointerException("null key");
        return key.equals(first);
    }

    public boolean containsValue(Object value) {
        if (first == null)
            return false;
        if (second == null)
            return second == value;
        return second.equals(value);
    }

    public Set<java.util.Map.Entry<K, V>> entrySet() {
        Set<java.util.Map.Entry<K, V>> set = new HashSet<java.util.Map.Entry<K, V>>();
        if (first != null)
            set.add(this);
        return set;
    }

    public V get(Object key) {
        if (key == null)
            throw new NullPointerException("null key");
        if (key.equals(first))
            return second;
        return null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Set<K> keySet() {
        Set<K> s = new HashSet<K>();
        if (first != null)
            s.add(first);
        return s;
    }

    public V put(K key, V value) {
        if (key == null)
            throw new NullPointerException("null key");
        first = key;
        second = value;
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> t) {
        // assert t != null;
        Iterator<? extends K> it = t.keySet().iterator();
        if (it.hasNext()) {
            first = it.next();
            second = t.get(first);
        }
    }

    public V remove(Object key) {
        if (key == null)
            throw new NullPointerException("Null key");
        if (key.equals(first)) {
            first = null;
            return second;
        }
        return null;
    }

    public int size() {
        if (first == null)
            return 0;
        return 1;
    }

    public Collection<V> values() {
        List<V> list = new ArrayList<V>();
        if (first != null)
            list.add(second);
        return list;
    }

}
