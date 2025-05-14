package net.bodz.bas.t.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.set.ArraySet;

public class SetMap<K, E>
        implements ISetMap<K, E> {

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
        return order.newSet();
    }

    @NotNull
    @Override
    public synchronized Set<E> makeSet(K keyToSet) {
        Set<E> set = map.get(keyToSet);
        if (set == null) {
            set = createSet();
            map.put(keyToSet, set);
        }
        return set;
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


    @Override
    public String toString() {
        return map.toString();
    }

}
