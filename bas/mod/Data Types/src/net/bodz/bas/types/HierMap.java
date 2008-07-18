package net.bodz.bas.types;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import net.bodz.bas.lang.Predicate;
import net.bodz.bas.lang.Predicate2;
import net.bodz.bas.lang.ref.Ref;
import net.bodz.bas.lang.ref.SimpleRef;

public class HierMap<K, V> extends TreeMap<K, V> {

    private static final long serialVersionUID = 5702402360129338243L;

    private Predicate2<K, K>  branchp;

    public HierMap(Predicate2<K, K> branchp) {
        super();
        setBranchPredicate(branchp);
    }

    public HierMap(Predicate2<K, K> branchp, Comparator<? super K> comparator) {
        super(comparator);
        setBranchPredicate(branchp);
    }

    public HierMap(Predicate2<K, K> branchp, Map<? extends K, ? extends V> m) {
        super(m);
        setBranchPredicate(branchp);
    }

    public HierMap(Predicate2<K, K> branchp, SortedMap<K, ? extends V> m) {
        super(m);
        setBranchPredicate(branchp);
    }

    public Predicate2<K, K> getBranchPredicate() {
        return branchp;
    }

    public void setBranchPredicate(Predicate2<K, K> branchp) {
        assert branchp != null : "null predicate";
        this.branchp = branchp;
    }

    public boolean hasBase(K key) {
        Entry<K, V> entry = floorEntry(key);
        if (entry == null)
            return false;
        K floorKey = entry.getKey();
        return branchp.eval(floorKey, key);
    }

    public V getBase(K key) {
        Entry<K, V> entry = floorEntry(key);
        if (entry == null)
            return null;
        K floorKey = entry.getKey();
        if (branchp.eval(floorKey, key))
            return entry.getValue();
        return null;
    }

    public void findSub(K base, Predicate<Entry<K, V>> c) {
        Entry<K, V> entry = ceilingEntry(base);
        while (entry != null) {
            K subKey = entry.getKey();
            if (!branchp.eval(base, subKey))
                break;
            if (!c.eval(entry))
                break;
            entry = higherEntry(subKey);
        }
    }

    public boolean hasSub(K base) {
        final Ref<Boolean> bool = new SimpleRef<Boolean>(false);
        findSub(base, new Predicate<Entry<K, V>>() {
            @Override
            public boolean eval(Entry<K, V> entry) {
                bool.set(true);
                return false;
            }
        });
        return bool.get();
    }

    public List<Entry<K, V>> getSubEntries(K base) {
        final List<Entry<K, V>> list = new ArrayList<Entry<K, V>>();
        findSub(base, new Predicate<Entry<K, V>>() {
            @Override
            public boolean eval(Entry<K, V> entry) {
                list.add(entry);
                return true;
            }
        });
        return list;
    }

    public List<K> getSubKeys(K base) {
        final List<K> list = new ArrayList<K>();
        findSub(base, new Predicate<Entry<K, V>>() {
            @Override
            public boolean eval(Entry<K, V> entry) {
                list.add(entry.getKey());
                return true;
            }
        });
        return list;
    }

    public List<V> getSubValues(K base) {
        final List<V> list = new ArrayList<V>();
        findSub(base, new Predicate<Entry<K, V>>() {
            @Override
            public boolean eval(Entry<K, V> entry) {
                list.add(entry.getValue());
                return true;
            }
        });
        return list;
    }

}
