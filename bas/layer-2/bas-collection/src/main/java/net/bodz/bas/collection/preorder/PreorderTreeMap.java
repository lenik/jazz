package net.bodz.bas.collection.preorder;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.bodz.bas.util.iter.PrefetchedIterator;

public class PreorderTreeMap<K, V>
        extends TreeMap<K, V> {

    private static final long serialVersionUID = 1L;

    private final IPreorder<K> preorder;

    public PreorderTreeMap(IPreorder<K> preorder) {
        super(preorder);
        if (preorder == null)
            throw new NullPointerException("preorder");
        this.preorder = preorder;
    }

    @Override
    public K floorKey(K key) {
        while (!containsKey(key)) {
            key = preorder.getPreceding(key);
            if (key == null)
                return null;
        }
        return key;
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        K floorKey = floorKey(key);
        if (floorKey == null)
            return null;
        Entry<K, V> floorEntry = super.floorEntry(floorKey);
        return floorEntry;
    }

    /**
     * Using Preorder-Preceding to get the lessor key.
     */
    public K _floorKey_fast(K key) {
        K floor = super.floorKey(key);
        while (floor != null) {
            if (preorder.isLessOrEquals(floor, key))
                return floor;
            key = preorder.getPreceding(key);
            if (key == null)
                break;
            floor = super.floorKey(key);
        }
        return null;
    }

    /**
     * Using Preorder-Preceding to get the lessor key.
     */
    public Entry<K, V> _floorEntry_fast(K key) {
        Entry<K, V> floo = super.floorEntry(key);
        while (floo != null) {
            if (preorder.isLessOrEquals(floo.getKey(), key))
                return floo;
            key = preorder.getPreceding(key);
            if (key == null)
                break;
            floo = super.floorEntry(key);
        }
        return null;
    }

    /**
     * Using Preorder-Preceding to get the lessor key.
     */
    public final V floor(K key) {
        Entry<K, V> floo = floorEntry(key);
        if (floo == null)
            return null;
        return floo.getValue();
    }

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    @Override
    public K ceilingKey(K key) {
        K ceil = super.ceilingKey(key);
        if (ceil == null)
            return null;
        if (preorder.isGreaterOrEquals(ceil, key))
            return ceil;
        return null;
    }

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    @Override
    public Entry<K, V> ceilingEntry(K key) {
        Entry<K, V> ceil = super.ceilingEntry(key);
        if (ceil == null)
            return null;
        if (preorder.isGreaterOrEquals(ceil.getKey(), key))
            return ceil;
        return null;
    }

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    public V ceiling(K key) {
        Entry<K, V> ceil = ceilingEntry(key);
        if (ceil == null)
            return null;
        if (preorder.isGreaterOrEquals(ceil.getKey(), key))
            return ceil.getValue();
        return null;
    }

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    public Iterable<K> ceilingKeys(final K key) {
        final K start = ceilingKey(key);

        class Iter
                extends PrefetchedIterator<K> {
            private K next;

            public Iter(K next) {
                this.next = next;
            }

            @Override
            protected K fetch() {
                if (next == null)
                    return end();
                K ret = next;
                next = higherKey(next);
                if (next != null && !preorder.isLessOrEquals(key, next))
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

        class Iter
                extends PrefetchedIterator<Entry<K, V>> {
            private Entry<K, V> next;

            public Iter(Entry<K, V> next) {
                this.next = next;
            }

            @Override
            protected Entry<K, V> fetch() {
                if (next == null)
                    return end();
                Entry<K, V> ret = next;
                next = higherEntry(next.getKey());
                if (next != null && !preorder.isLessOrEquals(key, next.getKey()))
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

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    public Iterable<V> ceilings(final K key) {
        final Entry<K, V> start = ceilingEntry(key);

        class Iter
                extends PrefetchedIterator<V> {
            private Entry<K, V> next;

            public Iter(Entry<K, V> next) {
                this.next = next;
            }

            @Override
            protected V fetch() {
                if (next == null)
                    return end();
                V ret = next.getValue();
                next = higherEntry(next.getKey());
                if (next != null && !preorder.isLessOrEquals(key, next.getKey()))
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