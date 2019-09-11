package net.bodz.bas.t.map;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.t.pojo.Pair;

public class KeyMap<K>
        extends AbstractMap<K, K>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private Set<K> set;

    public KeyMap(Set<K> set) {
        if (set == null)
            throw new NullPointerException("set");
        this.set = set;
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return set.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return set.contains(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public K get(Object key) {
        if (set.contains(key))
            return (K) key;
        else
            return null;
    }

    @Override
    public K put(K key, K value) {
        if (set.contains(key))
            return key;
        set.add(key);
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public K remove(Object key) {
        if (set.remove(key))
            return (K) key;
        else
            return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends K> m) {
        set.addAll(m.keySet());
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public Set<K> keySet() {
        return set;
    }

    @Override
    public Collection<K> values() {
        return set;
    }

    @Override
    public Set<Map.Entry<K, K>> entrySet() {
        return new EntrySet();
    }

    class EntrySet
            extends AbstractSet<Entry<K, K>> {

        @Override
        public int size() {
            return set.size();
        }

        @Override
        public boolean isEmpty() {
            return set.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            if (!(o instanceof Entry<?, ?>))
                return false;
            @SuppressWarnings("unchecked")
            Entry<K, K> entry = (Map.Entry<K, K>) o;
            return set.contains(entry.getKey());
        }

        @Override
        public Iterator<Map.Entry<K, K>> iterator() {
            Iterator<K> iterator = set.iterator();
            return new EntryIterator<K>(iterator);
        }

        @Override
        public boolean add(Map.Entry<K, K> e) {
            return set.add(e.getKey());
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean remove(Object o) {
            if (!(o instanceof Entry<?, ?>))
                return false;
            Map.Entry<K, K> entry = (Map.Entry<K, K>) o;
            return set.remove(entry.getKey());
        }

        @Override
        public void clear() {
            set.clear();
        }

    }

    static class EntryIterator<T>
            implements Iterator<Entry<T, T>> {

        private final Iterator<T> iterator;

        public EntryIterator(Iterator<T> iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Entry<T, T> next() {
            T val = iterator.next();
            return Pair.of(val, val);
        }

        @Override
        public void remove() {
            iterator.remove();
        }

    }

}
