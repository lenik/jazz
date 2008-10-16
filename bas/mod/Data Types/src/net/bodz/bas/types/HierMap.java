package net.bodz.bas.types;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import net.bodz.bas.types.util.PrefetchedIterator;

public abstract class HierMap<K, V> extends TreeMap<K, V> implements Hier<K> {

    private static final long serialVersionUID = 5702402360129338243L;

    public HierMap() {
        super();
    }

    public HierMap(Comparator<? super K> comparator) {
        super(comparator);
    }

    public HierMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    public HierMap(SortedMap<K, ? extends V> m) {
        super(m);
    }

    /**
     * @throws <code>null</code> by default
     */
    protected K nonexistKey() {
        return null;
    }

    /**
     * @return <code>null</code> by default
     */
    protected V nonexistValue() {
        return null;
    }

    /**
     * @return <code>null</code> by default
     */
    protected Entry<K, V> nonexistEntry() {
        return null;
    }

    @Override
    public K floorKey(K key) {
        K floo = super.floorKey(key);
        while (floo != null) {
            if (derives(floo, key))
                return floo;
            key = shrink(key);
            if (key == null)
                break;
            floo = super.floorKey(key);
        }
        return nonexistKey();
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        Entry<K, V> floo = super.floorEntry(key);
        while (floo != null) {
            if (derives(floo.getKey(), key))
                return floo;
            key = shrink(key);
            if (key == null)
                break;
            floo = super.floorEntry(key);
        }
        return nonexistEntry();
    }

    public V floor(K key) {
        Entry<K, V> floo = floorEntry(key);
        if (floo == null)
            return nonexistValue();
        return floo.getValue();
    }

    @Override
    public K ceilingKey(K key) {
        K ceil = super.ceilingKey(key);
        if (ceil == null)
            return nonexistKey();
        if (derives(key, ceil))
            return ceil;
        return nonexistKey();
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        Entry<K, V> ceil = super.ceilingEntry(key);
        if (ceil == null)
            return nonexistEntry();
        if (derives(key, ceil.getKey()))
            return ceil;
        return nonexistEntry();
    }

    public V ceiling(K key) {
        Entry<K, V> ceil = ceilingEntry(key);
        if (ceil == null)
            return nonexistValue();
        if (derives(key, ceil.getKey()))
            return ceil.getValue();
        return nonexistValue();
    }

    public Iterable<K> ceilingKeys(final K key) {
        final K start = ceilingKey(key);

        class Iter extends PrefetchedIterator<K> {
            private K next;

            public Iter(K next) {
                this.next = next;
            }

            @Override
            protected Object fetch() {
                if (next == null)
                    return END;
                K ret = next;
                next = higherKey(next);
                if (next != null && !derives(key, next))
                    next = null;
                return ret;
            }
        }

        return new Iterable<K>() {
            @Override
            public Iterator<K> iterator() {
                return new Iter(start);
            }
        };
    }

    public Iterable<Entry<K, V>> ceilingEntries(final K key) {
        final Entry<K, V> start = ceilingEntry(key);

        class Iter extends PrefetchedIterator<Entry<K, V>> {
            private Entry<K, V> next;

            public Iter(Entry<K, V> next) {
                this.next = next;
            }

            @Override
            protected Object fetch() {
                if (next == null)
                    return END;
                Entry<K, V> ret = next;
                next = higherEntry(next.getKey());
                if (next != null && !derives(key, next.getKey()))
                    next = null;
                return ret;
            }
        }

        return new Iterable<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new Iter(start);
            }
        };
    }

    public Iterable<V> ceilings(final K key) {
        final Entry<K, V> start = ceilingEntry(key);

        class Iter extends PrefetchedIterator<V> {
            private Entry<K, V> next;

            public Iter(Entry<K, V> next) {
                this.next = next;
            }

            @Override
            protected Object fetch() {
                if (next == null)
                    return END;
                V ret = next.getValue();
                next = higherEntry(next.getKey());
                if (next != null && !derives(key, next.getKey()))
                    next = null;
                return ret;
            }
        }

        return new Iterable<V>() {
            @Override
            public Iterator<V> iterator() {
                return new Iter(start);
            }
        };
    }
}
