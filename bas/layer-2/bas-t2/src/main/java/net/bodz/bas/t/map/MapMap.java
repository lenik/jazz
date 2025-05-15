package net.bodz.bas.t.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;

public class MapMap<K, EK, EV>
        implements IMapMap<K, EK, EV> {

    private final Map<K, Map<EK, EV>> map;
    private final Supplier<Map<EK, EV>> factory;

    public MapMap() {
        this(SortOrder.NONE, SortOrder.NONE);
    }

    public MapMap(@NotNull SortOrder order) {
        this(order, order);
    }

    public MapMap(@NotNull Supplier<Map<EK, EV>> factory) {
        this(SortOrder.NONE, factory);
    }

    public MapMap(@NotNull SortOrder order, @NotNull SortOrder setOrder) {
        this(order.newMapDefault(), order::newMapDefault);
    }

    public MapMap(@NotNull SortOrder order, @NotNull Supplier<Map<EK, EV>> factory) {
        this(order.newMapDefault(), factory);
    }

    public MapMap(@NotNull Map<K, Map<EK, EV>> map) {
        this(map, SortOrder.NONE);
    }

    public MapMap(@NotNull Map<K, Map<EK, EV>> map, @NotNull SortOrder setOrder) {
        this(map, setOrder::newMapDefault);
    }

    public MapMap(@NotNull Map<K, Map<EK, EV>> map, @NotNull Supplier<Map<EK, EV>> factory) {
        this.map = map;
        this.factory = factory;
    }

    @NotNull
    @Override
    public synchronized Map<EK, EV> makeMap(K keyToMap) {
        Map<EK, EV> map = this.map.get(keyToMap);
        if (map == null) {
            map = factory.get();
            this.map.put(keyToMap, map);
        }
        return map;
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
    public Map<EK, EV> get(Object key) {
        return map.get(key);
    }

    @Override
    public Map<EK, EV> put(K key, Map<EK, EV> value) {
        return map.put(key, value);
    }

    @Override
    public Map<EK, EV> remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends Map<EK, EV>> m) {
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
    public Collection<Map<EK, EV>> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<K, Map<EK, EV>>> entrySet() {
        return map.entrySet();
    }

}
