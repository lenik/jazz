package net.bodz.bas.t.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;

public class ListMap<K, E>
        implements IListMap<K, E> {

    SortOrder order;
    Map<K, List<E>> map;

    public ListMap() {
        this(SortOrder.NONE);
    }

    public ListMap(SortOrder order) {
        this.order = order;
        this.map = createMap();
    }

    public SortOrder getOrder() {
        return order;
    }

    protected <key_t, value_t> Map<key_t, value_t> createMap() {
        return order.newMap();
    }

    protected <value_t> List<value_t> createList() {
        return new ArrayList<>();
    }

    @NotNull
    @Override
    public synchronized List<E> makeList(K keyToList) {
        List<E> list = map.get(keyToList);
        if (list == null) {
            list = createList();
            map.put(keyToList, list);
        }
        return list;
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
    public List<E> get(Object key) {
        return map.get(key);
    }

    @Override
    public List<E> put(K key, List<E> value) {
        return map.put(key, value);
    }

    @Override
    public List<E> remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends List<E>> m) {
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
    public Collection<List<E>> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<K, List<E>>> entrySet() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        return map.toString();
    }

}
