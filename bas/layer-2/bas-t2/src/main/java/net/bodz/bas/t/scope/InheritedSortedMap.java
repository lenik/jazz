package net.bodz.bas.t.scope;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import net.bodz.bas.t.iterator.WeaveIterator;
import net.bodz.bas.t.order.EntryKeyComparator;

public abstract class InheritedSortedMap<K, V>
        extends InheritedMap<K, V>
        implements SortedMap<K, V> {

    private static final long serialVersionUID = -4142737182766452892L;

    private SortedMap<K, V> psm;
    private SortedMap<K, V> qsm;

    public InheritedSortedMap(SortedMap<K, V> pMap) {
        super(pMap);
        this.qsm = (SortedMap<K, V>) qMap;
    }

    @Override
    public SortedMap<K, V> getParent() {
        return psm;
    }

    @Override
    protected abstract SortedMap<K, V> createMap();

    @Override
    public Comparator<? super K> comparator() {
        return psm.comparator();
    }

    @Override
    public K firstKey() {
        if (psm.isEmpty())
            return qsm.firstKey();
        K p0 = psm.firstKey();
        if (qsm.isEmpty())
            return p0;
        K q0 = qsm.firstKey();
        if (comparator().compare(p0, q0) <= 0)
            return p0;
        return q0;
    }

    @Override
    public K lastKey() {
        if (psm.isEmpty())
            return qsm.lastKey();
        K pE = psm.lastKey();
        if (qsm.isEmpty())
            return pE;
        K qE = qsm.lastKey();
        if (comparator().compare(pE, qE) > 0)
            return pE;
        return qE;
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return new TreeMap<K, V>(this).headMap(toKey);
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return new TreeMap<K, V>(this).subMap(fromKey, toKey);
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return new TreeMap<K, V>(this).tailMap(fromKey);
    }

    class SortedEntrySet
            extends EntrySet {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return WeaveIterator.from(new EntryKeyComparator<K>(comparator()), //
                    new PIterator(), //
                    qsm.entrySet().iterator());
        }

    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return new SortedEntrySet();
    }

}
