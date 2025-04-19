package net.bodz.bas.t.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;

public class MapMap<K, EK, EV>
        implements IMapMap<K, EK, EV> {

    SortOrder order;
    Map<K, Map<EK, EV>> map;

    public MapMap() {
        this(SortOrder.NONE);
    }

    public MapMap(SortOrder order) {
        this.order = order;
        this.map = createMap();
    }

    public SortOrder getOrder() {
        return order;
    }

    protected <key_t, value_t> Map<key_t, value_t> createMap() {
        return order.newMap();
    }

    protected <key_t, value_t> Map<key_t, value_t> createSubMap() {
        return new LinkedHashMap<>();
    }

    @NotNull
    @Override
    public synchronized Map<EK, EV> makeMap(K keyToMap) {
        Map<EK, EV> map = this.map.get(keyToMap);
        if (map == null) {
            map = createSubMap();
            this.map.put(keyToMap, map);
        }
        return map;
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
