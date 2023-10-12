package net.bodz.bas.t.conv;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.repr.form.SortOrder;

public abstract class ConvKeyMap<K, K_actual, V>
        implements
            Map<K, V> {

    Map<K_actual, V> actualMap;

    public ConvKeyMap() {
        this(SortOrder.NONE);
    }

    public ConvKeyMap(SortOrder sortOrder) {
        this(sortOrder.newMap());
    }

    public ConvKeyMap(Map<K_actual, V> actualMap) {
        if (actualMap == null)
            throw new NullPointerException("actualMap");
        this.actualMap = actualMap;
    }

    protected abstract IConverter<K, K_actual> getKeyConverter();

    @Override
    public int size() {
        return actualMap.size();
    }

    @Override
    public boolean isEmpty() {
        return actualMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        K_actual actualKey = getKeyConverter()._convert(key);
        return actualMap.containsKey(actualKey);
    }

    @Override
    public boolean containsValue(Object value) {
        return actualMap.containsValue(value);
    }

    @Override
    public V get(Object key) {
        K_actual actualKey = getKeyConverter()._convert(key);
        return actualMap.get(actualKey);
    }

    @Override
    public V put(K key, V value) {
        K_actual actualKey = getKeyConverter().convert(key);
        return actualMap.put(actualKey, value);
    }

    @Override
    public V remove(Object key) {
        K_actual actualKey = getKeyConverter()._convert(key);
        return actualMap.remove(actualKey);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            K key = entry.getKey();
            K_actual actualKey = getKeyConverter()._convert(key);
            V value = entry.getValue();
            actualMap.put(actualKey, value);
        }
    }

    @Override
    public void clear() {
        actualMap.clear();
    }

    @Override
    public Set<K> keySet() {
        Set<K_actual> actualKeys = actualMap.keySet();
        return new ConvSet<>(getKeyConverter(), actualKeys);
    }

    @Override
    public Collection<V> values() {
        return actualMap.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K_actual, V>> aSet = actualMap.entrySet();
        return new ConvSet<>(new EntryConverter<>(getKeyConverter()), aSet);
    }

    @Override
    public boolean equals(Object o) {
        return actualMap.equals(o);
    }

    @Override
    public int hashCode() {
        return actualMap.hashCode();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        K_actual actualKey = getKeyConverter()._convert(key);
        return actualMap.getOrDefault(actualKey, defaultValue);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        K_actual actualKey = getKeyConverter().convert(key);
        return actualMap.putIfAbsent(actualKey, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        K_actual actualKey = getKeyConverter()._convert(key);
        return actualMap.remove(actualKey, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        K_actual actualKey = getKeyConverter().convert(key);
        return actualMap.replace(actualKey, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        K_actual actualKey = getKeyConverter().convert(key);
        return actualMap.replace(actualKey, value);
    }

}
