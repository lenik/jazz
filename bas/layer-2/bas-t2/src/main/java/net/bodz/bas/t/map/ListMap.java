package net.bodz.bas.t.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.repr.form.SortOrder;

public class ListMap<K, V>
        implements
            Map<K, List<V>> {

    SortOrder order;
    Map<K, List<V>> map;

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

    public long size2() {
        long sum = 0;
        for (List<V> list : map.values())
            sum += list.size();
        return sum;
    }

    public boolean isEmpty2() {
        for (List<V> list : map.values())
            if (!list.isEmpty())
                return false;
        return true;
    }

    public boolean contains2(Object value) {
        for (List<V> list : map.values())
            if (list.contains(value))
                return true;
        return false;
    }

    public synchronized List<V> getOrCreate(K k1) {
        List<V> list = map.get(k1);
        if (list == null) {
            list = createList();
            map.put(k1, list);
        }
        return list;
    }

    public V get2(Object k1, int k2) {
        List<V> list = get(k1);
        if (list == null)
            return null;
        else
            return list.get(k2);
    }

    public V set2(K k1, int k2, V value) {
        List<V> list = getOrCreate(k1);
        return list.set(k2, value);
    }

    public boolean add2(K k1, V value) {
        List<V> list = getOrCreate(k1);
        return list.add(value);
    }

    public V remove2(Object k1, int k2) {
        List<V> list = get(k1);
        if (list == null)
            return null;
        return list.remove(k2);
    }

    public void addAll2(Map<? extends K, ? extends List<V>> m) {
        for (K k1 : m.keySet()) {
            List<V> list = getOrCreate(k1);
            list.addAll(m.get(k1));
        }
    }

    public void removeAll2(Map<? extends K, ? extends List<V>> m) {
        for (K k1 : m.keySet()) {
            List<V> list = get(k1);
            if (list != null)
                list.removeAll(m.get(k1));
        }
    }

    public void clear2() {
        for (List<V> list : map.values())
            list.clear();
    }

    public Collection<V> values2() {
        List<V> concat = new ArrayList<>();
        for (List<V> list : values())
            concat.addAll(list);
        return concat;
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
    public List<V> get(Object key) {
        return map.get(key);
    }

    @Override
    public List<V> put(K key, List<V> value) {
        return map.put(key, value);
    }

    @Override
    public List<V> remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends List<V>> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<List<V>> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, List<V>>> entrySet() {
        return map.entrySet();
    }

}
