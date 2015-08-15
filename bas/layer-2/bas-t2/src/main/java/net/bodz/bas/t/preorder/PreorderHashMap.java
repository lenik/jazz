package net.bodz.bas.t.preorder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.fn.IFilter;
import net.bodz.bas.t.iterator.Iterables;
import net.bodz.bas.t.pojo.Pair;

/**
 * The {@link IPreorder#compare(Object, Object)} is not used.
 */
public class PreorderHashMap<K, V>
        extends HashMap<K, V>
        implements IPreorderMap<K, V> {

    private static final long serialVersionUID = 1L;

    private final IPreorder<K> preorder;

    public PreorderHashMap(IPreorder<K> preorder) {
        if (preorder == null)
            throw new NullPointerException("preorder");
        this.preorder = preorder;
    }

    @Override
    public IPreorder<K> getPreorder() {
        return preorder;
    }

    @Override
    public Map.Entry<K, V> meetEntry(K key) {
        K meetKey = meetKey(key);
        if (meetKey == null)
            return null;
        V meetValue = get(meetKey);
        return new Pair<K, V>(meetKey, meetValue);
    }

    @Override
    public K meetKey(K key) {
        while (!containsKey(key)) {
            key = preorder.getPreceding(key);
            if (key == null)
                return null;
        }
        return key;
    }

    @Override
    public V meet(K key) {
        key = meetKey(key);
        return get(key);
    }

    class JoinEntryFilter
            implements IFilter<Map.Entry<K, V>> {

        final K minKey;

        public JoinEntryFilter(K minKey) {
            this.minKey = minKey;
        }

        @Override
        public boolean accept(Map.Entry<K, V> entry)
                throws RuntimeException {
            K key = entry.getKey();
            return preorder.isLessOrEquals(minKey, key);
        }

    }

    @Override
    public Iterable<Map.Entry<K, V>> joinEntries(K key) {
        return Iterables.filter(entrySet(), new JoinEntryFilter(key));
    }

    @Override
    public Iterable<K> joinKeys(K key) {
        joinEntries(key);
        return null;
    }

    @Override
    public Iterable<V> join(K key) {
        joinEntries(key);
        return null;
    }

    @Override
    public Map<K, V> joinMap(K key) {
        Map<K, V> map = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : joinEntries(key)) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    @Override
    public Set<K> joinKeySet(K key) {
        Set<K> set = new HashSet<K>();
        for (K k : joinKeys(key))
            set.add(k);
        return set;
    }

    @Override
    public Set<V> joinValueSet(K key) {
        Set<V> list = new HashSet<V>();
        for (V val : join(key))
            list.add(val);
        return list;
    }

}
