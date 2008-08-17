package net.bodz.bas.types;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import net.bodz.bas.lang.Predicate;
import net.bodz.bas.lang.Predicate2s;

public class HierMap<K, V> extends TreeMap<K, V> {

    private static final long serialVersionUID = 5702402360129338243L;

    private Predicate2s<K>    relpred;

    public HierMap(Predicate2s<K> relations) {
        super();
        setRelations(relations);
    }

    public HierMap(Predicate2s<K> relations, Comparator<? super K> comparator) {
        super(comparator);
        setRelations(relations);
    }

    public HierMap(Predicate2s<K> relations, Map<? extends K, ? extends V> m) {
        super(m);
        setRelations(relations);
    }

    public HierMap(Predicate2s<K> relations, SortedMap<K, ? extends V> m) {
        super(m);
        setRelations(relations);
    }

    public Predicate2s<K> getRelations() {
        return relpred;
    }

    public void setRelations(Predicate2s<K> relations) {
        assert relations != null : "null predicate";
        this.relpred = relations;
    }

    protected K nonexistKey() {
        // return null;
        throw new NoSuchElementException();
    }

    protected V nonexistValue() {
        return null;
    }

    protected Entry<K, V> nonexistEntry() {
        // return null;
        throw new NoSuchElementException();
    }

    public boolean hasParent(K childInclKey) {
        Entry<K, V> entry = floorEntry(childInclKey);
        if (entry == null)
            return false;
        K floorKey = entry.getKey();
        return relpred.eval(floorKey, childInclKey);
    }

    public V getParent(K childInclKey) {
        Entry<K, V> entry = floorEntry(childInclKey);
        if (entry == null)
            return nonexistValue();
        K floorKey = entry.getKey();
        if (relpred.eval(floorKey, childInclKey))
            return entry.getValue();
        return nonexistValue();
    }

    public void findChildren(K parentInclKey, Predicate<Entry<K, V>> callback) {
        Entry<K, V> entry = ceilingEntry(parentInclKey);
        while (entry != null) {
            K subKey = entry.getKey();
            if (!relpred.eval(parentInclKey, subKey))
                break;
            if (!callback.eval(entry))
                break;
            entry = higherEntry(subKey);
        }
    }

    public boolean hasChildren(K parentInclKey) {
        final boolean[] boolv = new boolean[1];
        findChildren(parentInclKey, new Predicate<Entry<K, V>>() {
            @Override
            public boolean eval(Entry<K, V> entry) {
                boolv[0] = true;
                return false;
            }
        });
        return boolv[0];
    }

    public List<Entry<K, V>> getChildrenEntries(K parentInclKey) {
        final List<Entry<K, V>> list = new ArrayList<Entry<K, V>>();
        findChildren(parentInclKey, new Predicate<Entry<K, V>>() {
            @Override
            public boolean eval(Entry<K, V> entry) {
                list.add(entry);
                return true;
            }
        });
        return list;
    }

    public List<K> getChildrenKeys(K parentInclKey) {
        final List<K> list = new ArrayList<K>();
        findChildren(parentInclKey, new Predicate<Entry<K, V>>() {
            @Override
            public boolean eval(Entry<K, V> entry) {
                list.add(entry.getKey());
                return true;
            }
        });
        return list;
    }

    public List<V> getChildrenValues(K parentInclKey) {
        final List<V> list = new ArrayList<V>();
        findChildren(parentInclKey, new Predicate<Entry<K, V>>() {
            @Override
            public boolean eval(Entry<K, V> entry) {
                list.add(entry.getValue());
                return true;
            }
        });
        return list;
    }

}
