package net.bodz.bas.t.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;

public class SetMap<K, E>
        implements ISetMap<K, E> {

    Map<K, Set<E>> map;
    Supplier<Set<E>> factory;

    public SetMap() {
        this(SortOrder.NONE, SortOrder.NONE);
    }

    public SetMap(@NotNull SortOrder order) {
        this(order, order);
    }

    public SetMap(@NotNull Supplier<Set<E>> factory) {
        this(SortOrder.NONE, factory);
    }

    public SetMap(@NotNull SortOrder order, @NotNull SortOrder setOrder) {
        this(order.newMapDefault(), order::newSetDefault);
    }

    public SetMap(@NotNull SortOrder order, @NotNull Supplier<Set<E>> factory) {
        this(order.newMapDefault(), factory);
    }

    public SetMap(@NotNull Map<K, Set<E>> map) {
        this(map, SortOrder.NONE);
    }

    public SetMap(@NotNull Map<K, Set<E>> map, @NotNull SortOrder setOrder) {
        this(map, setOrder::newSetDefault);
    }

    public SetMap(@NotNull Map<K, Set<E>> map, @NotNull Supplier<Set<E>> factory) {
        this.map = map;
        this.factory = factory;
    }

    @NotNull
    @Override
    public synchronized Set<E> makeSet(K keyToSet) {
        Set<E> set = map.get(keyToSet);
        if (set == null) {
            set = factory.get();
            map.put(keyToSet, set);
        }
        return set;
    }

    /**
     * ⇱ Implementation Of {@link Map}.
     */
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
