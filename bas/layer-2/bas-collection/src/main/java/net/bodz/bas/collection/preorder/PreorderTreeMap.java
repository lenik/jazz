package net.bodz.bas.collection.preorder;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.bodz.bas.util.iter.PrefetchedIterator;

public class PreorderTreeMap<K, V>
        extends TreeMap<K, V>
        implements IPreorderMap<K, V>, Map<K, V> {

    private static final long serialVersionUID = 1L;

    private final IPreorder<K> preorder;

    public PreorderTreeMap(IPreorder<K> preorder) {
        super(preorder);
        if (preorder == null)
            throw new NullPointerException("preorder");
        this.preorder = preorder;
    }

    @Override
    public Entry<K, V> meetEntry(K key) {
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

    @Override
    public K meetKey(K key) {
        Entry<K, V> entry = reduceToMeetEntry(key);
        if (entry == null)
            return null;
        else
            return entry.getKey();
    }

    @Override
    public V meet(K key) {
        Entry<K, V> entry = reduceToMeetEntry(key);
        if (entry == null)
            return null;
        else
            return entry.getValue();
    }

    // @Override
    public Entry<K, V> reduceToMeetEntry(K key) {
        K meetKey = meetKey(key);
        if (meetKey == null)
            return null;
        return super.floorEntry(meetKey);
    }

    // @Override
    public K reduceToMeetKey(K key) {
        while (!containsKey(key)) {
            key = preorder.getPreceding(key);
            if (key == null)
                return null;
        }
        return key;
    }

    // @Override
    public V reduceToMeet(K key) {
        Entry<K, V> entry = meetEntry(key);
        if (entry == null)
            return null;
        else
            return entry.getValue();
    }

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    public Entry<K, V> joinEntry_fast(K key) {
        Entry<K, V> ceil = ceilingEntry(key);
        if (ceil == null)
            return null;
        if (preorder.isGreaterOrEquals(ceil.getKey(), key))
            return ceil;
        return null;
    }

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    public K joinKey_fast(K key) {
        Entry<K, V> entry = joinEntry_fast(key);
        if (entry == null)
            return null;
        else
            return entry.getKey();
    }

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    public V join_fast(K key) {
        Entry<K, V> entry = joinEntry_fast(key);
        if (entry == null)
            return null;
        else
            return entry.getValue();
    }

    @Override
    public Iterable<Entry<K, V>> joinEntries(final K key) {
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
    @Override
    public Iterable<K> joinKeys(final K key) {
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

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    @Override
    public Iterable<V> join(final K key) {
        final Entry<K, V> start = ceilingEntry(key);

        class HigherIter
                extends PrefetchedIterator<V> {

            private Entry<K, V> next;

            public HigherIter(Entry<K, V> next) {
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
                return new HigherIter(start);
            }
        };
    }

}