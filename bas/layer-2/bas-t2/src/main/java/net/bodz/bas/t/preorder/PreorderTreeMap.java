package net.bodz.bas.t.preorder;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.bodz.bas.t.iterator.PrefetchedIterator;
import net.bodz.bas.t.iterator.PrefetchSeed;

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
    public IPreorder<K> getPreorder() {
        return preorder;
    }

    /**
     * Get the exact key.
     * 
     * Use {@link #meet(Object)} instead to get a nearest node in the preorder map.
     * <p>
     * {@inheritDoc}
     * 
     * @see #meet(Object)
     * @see #meetKey(Object)
     * @see #meetEntry(Object)
     */
    @Override
    public V get(Object key) {
        return super.get(key);
    }

    @Override
    public Map.Entry<K, V> meetEntry(K key) {
        Map.Entry<K, V> floo = super.floorEntry(key);
        while (floo != null) {
            if (preorder.isLessOrEquals(floo.getKey(), key))
                return floo;

            // TODO: optim: test-key = super.higherEntry(floo.key).
            key = preorder.getPreceding(key);

            if (key == null)
                break;
            floo = super.floorEntry(key);
        }
        return null;
    }

    @Override
    public K meetKey(K key) {
        Map.Entry<K, V> entry = reduceToMeetEntry(key);
        if (entry == null)
            return null;
        else
            return entry.getKey();
    }

    @Override
    public V meet(K key) {
        Map.Entry<K, V> entry = reduceToMeetEntry(key);
        if (entry == null)
            return null;
        else
            return entry.getValue();
    }

    // @Override
    public Map.Entry<K, V> reduceToMeetEntry(K key) {
        K meetKey = reduceToMeetKey(key);
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
        Map.Entry<K, V> entry = meetEntry(key);
        if (entry == null)
            return null;
        else
            return entry.getValue();
    }

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    public Map.Entry<K, V> joinEntry_fast(K key) {
        Map.Entry<K, V> ceil = ceilingEntry(key);
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
        Map.Entry<K, V> entry = joinEntry_fast(key);
        if (entry == null)
            return null;
        else
            return entry.getKey();
    }

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    public V join_fast(K key) {
        Map.Entry<K, V> entry = joinEntry_fast(key);
        if (entry == null)
            return null;
        else
            return entry.getValue();
    }

    @Override
    public Map<K, V> joinMap(K key) {
        Map<K, V> map = new LinkedHashMap<K, V>();

        Map.Entry<K, V> entry = joinEntry_fast(key);

        while (entry != null) {
            K _key = entry.getKey();
            map.put(_key, entry.getValue());

            entry = higherEntry(_key);
            if (entry == null)
                break;

            if (!preorder.isLessOrEquals(_key, entry.getKey()))
                break;
        }

        return map;
    }

    @Override
    public Iterable<Map.Entry<K, V>> joinEntries(final K key) {
        final Map.Entry<K, V> start = joinEntry_fast(key);

        class Seed
                extends PrefetchSeed<Map.Entry<K, V>> {
            private Map.Entry<K, V> next;

            public Seed(Map.Entry<K, V> next) {
                this.next = next;
            }

            @Override
            public Seed clone() {
                Seed o = new Seed(next);
                o.init(this);
                return o;
            }

            @Override
            protected Map.Entry<K, V> fetch() {
                if (next == null)
                    return end();
                Map.Entry<K, V> ret = next;
                next = higherEntry(next.getKey());
                if (next != null && !preorder.isLessOrEquals(key, next.getKey()))
                    next = null;
                return ret;
            }
        }

        return new Seed(start);
    }

    @Override
    public Set<K> joinKeySet(K key) {
        Set<K> set = new HashSet<K>();
        for (K k : joinKeys(key))
            set.add(k);
        return set;
    }

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    @Override
    public Iterable<K> joinKeys(final K key) {
        final K start = joinKey_fast(key);

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

    @Override
    public Set<V> joinValueSet(K key) {
        Set<V> list = new HashSet<V>();
        for (V val : join(key))
            list.add(val);
        return list;
    }

    /**
     * Using Preorder-Preceding to get the greator key (only if available).
     */
    @Override
    public Iterable<V> join(final K key) {
        final Map.Entry<K, V> start = joinEntry_fast(key);

        class HigherIter
                extends PrefetchedIterator<V> {

            private Map.Entry<K, V> next;

            public HigherIter(Map.Entry<K, V> next) {
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