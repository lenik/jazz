package net.bodz.bas.t.preorder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.err.OverflowError;
import net.bodz.bas.t.iterator.PrefetchedIterator;
import net.bodz.bas.t.map.IMapOst;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.bas.t.pojo.Triple;

public abstract class AbstractPreorderMapOst<K, V>
        extends DecoratedPreorderMap<K, IPreorderMap<K, V>>
        implements IPreorderMapOst<K, V> {

    private static final long serialVersionUID = 1L;

    public AbstractPreorderMapOst(IPreorderMap<K, IPreorderMap<K, V>> _orig) {
        super(_orig);
    }

    /** ⇱ Implementation Of {@link IMapOst}. */
    /* _____________________________ */static section.iface __MAP_OST__;

    protected abstract IPreorderMap<K, V> create2();

    @Override
    public boolean containsKey(K key1, K key2) {
        Map<K, V> map2 = get(key1);
        if (map2 == null)
            return false;
        else
            return map2.containsKey(key2);
    }

    @Override
    public Map<K, V> getOrCreate(K key1) {
        IPreorderMap<K, V> map2 = get(key1);
        if (map2 == null) {
            map2 = create2();
            put(key1, map2);
        }
        return map2;
    }

    @Override
    public V get(K key1, K key2) {
        Map<K, V> map2 = get(key1);
        if (map2 == null)
            return null;
        else
            return map2.get(key2);
    }

    @Override
    public V put(K key1, K key2, V value) {
        Map<K, V> map2 = getOrCreate(key1);
        return map2.put(key2, value);
    }

    @Override
    public V remove2(K key1, K key2) {
        Map<K, V> map2 = get(key1);
        if (map2 == null)
            return null;
        else
            return map2.remove(key2);
    }

    @Override
    public int size2() {
        int size2 = 0;
        for (Map<K, V> map : values()) {
            int size = map.size();
            size2 += size;
            if (size2 < size)
                throw new OverflowError();
        }
        return size2;
    }

    /** ⇱ Implementation Of {@link IPreorderMapOst}. */
    /* _____________________________ */static section.iface __PO_OST__;

    @Override
    public Triple<K, K, V> meetEntry(K key1, K key2) {
        IPreorder<K> preorder = getPreorder();

        do {
            Entry<K, IPreorderMap<K, V>> entry1 = meetEntry(key1);
            if (entry1 == null)
                return null;
            IPreorderMap<K, V> map2 = entry1.getValue();

            Entry<K, V> entry2 = map2.meetEntry(key2);
            if (entry2 != null)
                return Triple.of(key1, entry2.getKey(), entry2.getValue());

            key1 = entry1.getKey();
            key1 = preorder.getPreceding(key1);
        } while (key1 != null);
        return null;
    }

    @Override
    public Pair<K, K> meetKey(K key1, K key2) {
        Triple<K, K, V> triple = meetEntry(key1, key2);
        if (triple == null)
            return null;
        else
            return Pair.of(triple.first, triple.second);
    }

    @Override
    public V meet(K key1, K key2) {
        Triple<K, K, V> triple = meetEntry(key1, key2);
        if (triple == null)
            return null;
        else
            return triple.third;
    }

    @Override
    public Iterable<? extends Triple<K, K, V>> joinEntries(K key1, final K key2) {
        final Iterator<Entry<K, IPreorderMap<K, V>>> iterator1 = joinEntries(key1).iterator();

        class Seed
                extends PrefetchedIterator<Triple<K, K, V>> {

            K key1j;
            Iterator<Entry<K, V>> iterator2 = Collections.emptyIterator();

            @Override
            protected Triple<K, K, V> fetch() {
                while (!iterator2.hasNext()) {
                    if (!iterator1.hasNext())
                        return end();

                    Entry<K, IPreorderMap<K, V>> entry1 = iterator1.next();
                    key1j = entry1.getKey();
                    IPreorderMap<K, V> map2 = entry1.getValue();
                    iterator2 = map2.joinEntries(key2).iterator();
                }
                Entry<K, V> entry2 = iterator2.next();
                Triple<K, K, V> triple = Triple.of(key1j, entry2.getKey(), entry2.getValue());
                return triple;
            }

        }

        return new Iterable<Triple<K, K, V>>() {
            @Override
            public Iterator<Triple<K, K, V>> iterator() {
                return new Seed();
            }
        };
    }

    @Override
    public Iterable<? extends Pair<K, K>> joinKeys(K key1, K key2) {
        return joinEntries(key1, key2);
    }

    @Override
    public Iterable<V> join(K key1, K key2) {
        final Iterable<? extends Triple<K, K, V>> entries = joinEntries(key1, key2);
        return new Iterable<V>() {
            Iterator<? extends Triple<K, K, V>> iterator = entries.iterator();

            @Override
            public Iterator<V> iterator() {
                return new Iterator<V>() {
                    @Override
                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    @Override
                    public V next() {
                        Triple<K, K, V> triple = iterator.next();
                        return triple == null ? null : triple.third;
                    }

                    @Override
                    public void remove() {
                        iterator.remove();
                    }
                };
            }
        };
    }

    @Override
    public Map<Pair<K, K>, V> joinMap(K key1, K key2) {
        Map<K, IPreorderMap<K, V>> map1 = joinMap(key1);
        Map<Pair<K, K>, V> map = new LinkedHashMap<>();
        for (Entry<K, IPreorderMap<K, V>> entry1 : map1.entrySet()) {
            K key1j = entry1.getKey();
            for (Entry<K, V> entry2 : entry1.getValue().joinMap(key2).entrySet()) {
                K key2j = entry2.getKey();
                Pair<K, K> pair = Pair.of(key1j, key2j);
                map.put(pair, entry2.getValue());
            }
        }
        return map;
    }

    @Override
    public Set<Pair<K, K>> joinKeySet(K key1, K key2) {
        return joinMap(key1, key2).keySet();
    }

    @Override
    public Set<V> joinValueSet(K key1, K key2) {
        Set<V> set = new HashSet<>();
        for (IPreorderMap<K, V> map2 : joinValueSet(key1))
            for (V val : map2.joinMap(key2).values())
                set.add(val);
        return set;
    }

}
