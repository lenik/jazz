package net.bodz.bas.t.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.pojo.Pair;

public class K2Map<K, V>
        implements
            Map<K, Map<K, V>> {

    SortOrder order;
    Map<K, Map<K, V>> map;

    public K2Map() {
        this(SortOrder.NONE);
    }

    public K2Map(SortOrder order) {
        this.order = order;
        this.map = createMap();
    }

    public SortOrder getOrder() {
        return order;
    }

    protected <value_t> Map<K, value_t> createMap() {
        return order.newMap();
    }

    public long size2() {
        long sum = 0;
        for (Map<K, V> map2 : map.values())
            sum += map2.size();
        return sum;
    }

    public boolean isEmpty2() {
        for (Map<K, V> map2 : map.values())
            if (!map2.isEmpty())
                return false;
        return true;
    }

    public boolean containsKey2(Object k1, Object k2) {
        Map<K, V> map2 = map.get(k1);
        if (map2 == null)
            return false;
        return map2.containsKey(k2);
    }

    public boolean containsValue2(Object value) {
        for (Map<K, V> map2 : map.values())
            if (map2.containsValue(value))
                return true;
        return false;
    }

    public synchronized Map<K, V> getOrCreate(K k1) {
        Map<K, V> map2 = map.get(k1);
        if (map2 == null) {
            map2 = createMap();
            map.put(k1, map2);
        }
        return map2;
    }

    public V get2(Object k1, Object k2) {
        Map<K, V> map2 = get(k1);
        if (map2 == null)
            return null;
        else
            return map2.get(k2);
    }

    public V put2(K k1, K k2, V value) {
        Map<K, V> map2 = getOrCreate(k1);
        return map2.put(k2, value);
    }

    public V remove2(Object k1, Object k2) {
        Map<K, V> map2 = get(k1);
        if (map2 == null)
            return null;
        return map2.remove(k2);
    }

    public void putAll2(Map<? extends K, ? extends Map<K, V>> m) {
        for (K k1 : m.keySet()) {
            Map<K, V> map2 = getOrCreate(k1);
            map2.putAll(m.get(k1));
        }
    }

    public void clear2() {
        for (Map<K, V> map2 : map.values())
            map2.clear();
    }

    public Set<Entry<K, K>> keySet2() {
        Set<Entry<K, K>> set2 = new HashSet<>();
        for (K k1 : keySet()) {
            Map<K, V> map2 = get(k1);
            for (K k2 : map2.keySet()) {
                Pair<K, K> pair = Pair.of(k1, k2);
                set2.add(pair);
            }
        }
        return set2;
    }

    public Collection<V> values2() {
        List<V> list = new ArrayList<>();
        for (Map<K, V> map2 : values())
            list.addAll(map2.values());
        return list;
    }

    public Set<Entry<Entry<K, K>, V>> entrySet2() {
        Set<Entry<Entry<K, K>, V>> set2 = new HashSet<>();
        for (K k1 : keySet()) {
            Map<K, V> map2 = get(k1);
            for (K k2 : map2.keySet()) {
                Pair<K, K> pair = Pair.of(k1, k2);
                V v = map2.get(k2);
                set2.add(Pair.of(pair, v));
            }
        }
        return set2;
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
    public Map<K, V> get(Object key) {
        return map.get(key);
    }

    @Override
    public Map<K, V> put(K key, Map<K, V> value) {
        return map.put(key, value);
    }

    @Override
    public Map<K, V> remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends Map<K, V>> m) {
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
    public Collection<Map<K, V>> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, Map<K, V>>> entrySet() {
        return map.entrySet();
    }

}
