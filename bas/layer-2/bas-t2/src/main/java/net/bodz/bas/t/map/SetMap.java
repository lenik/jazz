package net.bodz.bas.t.map;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.set.ArraySet;

public class SetMap<K, E>
        implements Map<K, Set<E>> {

    SortOrder order;
    Map<K, Set<E>> map;

    public SetMap() {
        this(SortOrder.NONE);
    }

    public SetMap(SortOrder order) {
        this.order = order;
        this.map = createMap();
    }

    public SortOrder getOrder() {
        return order;
    }

    protected <key_t, value_t> Map<key_t, value_t> createMap() {
        return order.newMap();
    }

    protected <value_t> Set<value_t> createSet() {
        return new ArraySet<>();
    }

    public long sizeOfAllSets() {
        long sum = 0;
        for (Set<E> set : map.values())
            sum += set.size();
        return sum;
    }

    public boolean isAllSetsEmpty() {
        for (Set<E> set : map.values())
            if (!set.isEmpty())
                return false;
        return true;
    }

    public boolean isAnySetEmpty() {
        for (Set<E> set : map.values())
            if (set.isEmpty())
                return true;
        return false;
    }

    public boolean isAllSetsContains(E setElement) {
        for (Set<E> set : map.values())
            if (!set.contains(setElement))
                return false;
        return true;
    }

    public boolean isAnySetContains(E setElement) {
        for (Set<E> set : map.values())
            if (set.contains(setElement))
                return true;
        return false;
    }

    public synchronized Set<E> set(K keyToSet) {
        Set<E> set = map.get(keyToSet);
        if (set == null) {
            set = createSet();
            map.put(keyToSet, set);
        }
        return set;
    }

    public boolean addToSet(K keyToSet, E setElement) {
        Set<E> set = set(keyToSet);
        return set.add(setElement);
    }

    public boolean removeFromSet(Object keyToSet, E setElement) {
        Set<E> set = get(keyToSet);
        if (set == null)
            return false;
        return set.remove(setElement);
    }

    public void addAllToSets(Map<? extends K, ? extends Set<E>> m) {
        for (K keyToSet : m.keySet()) {
            Set<E> set = set(keyToSet);
            set.addAll(m.get(keyToSet));
        }
    }

    public void removeAllFromSets(Map<? extends K, ? extends Set<E>> m) {
        for (K keyToSet : m.keySet()) {
            Set<E> set = get(keyToSet);
            if (set != null)
                set.removeAll(m.get(keyToSet));
        }
    }

    public void clearSets() {
        for (Set<E> set : map.values())
            set.clear();
    }

    public Set<E> concatenateAllSets() {
        Set<E> concat = new ArraySet<>();
        for (Set<E> set : values())
            concat.addAll(set);
        return concat;
    }

    public int removeFromAllSets(E item) {
        int count = 0;
        for (Set<E> set : values())
            if (set.remove(item))
                count++;
        return count;
    }

    public boolean removeFromAnySets(E item) {
        for (Set<E> set : values())
            if (set.remove(item))
                return true;
        return false;
    }

    public K findFirstKeyToSetContains(E item) {
        for (Entry<K, Set<E>> entry : map.entrySet()) {
            Set<E> set = entry.getValue();
            if (set.contains(item)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Set<K> findKeysToSetsContain(E item) {
        Set<K> keys = new LinkedHashSet<>();
        for (Entry<K, Set<E>> entry : map.entrySet()) {
            Set<E> set = entry.getValue();
            if (set.contains(item)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    /** ⇱ Implementation Of {@link Map}. */
    /* _____________________________ */static section.iface __MAP__;

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Set<E> get(Object key) {
        return map.get(key);
    }

    @Override
    public Set<E> put(K key, Set<E> value) {
        return map.put(key, value);
    }

    @Override
    public Set<E> remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends Set<E>> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<Set<E>> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<K, Set<E>>> entrySet() {
        return map.entrySet();
    }

}
