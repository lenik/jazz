package net.bodz.bas.commons.nls_2;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.commons.hint.Proxy;

public class Map2VariantLookupMap<K, V>
        extends VariantLookupMap<K>
        implements Map<K, V>, Proxy<Map<K, V>> {

    private final Map<K, V> proxy;

    public Map2VariantLookupMap(Map<K, V> proxy) {
        if (proxy == null)
            throw new NullPointerException("proxy");
        this.proxy = proxy;
    }

    // interface Proxy

    @Override
    public Map<K, V> getProxyTarget() {
        return proxy;
    }

    // interface Queryable

    @Override
    public Object getObject(K key) {
        return proxy.get(key);
    }

    // interface Map<K, V>

    @Override
    public void clear() {
        proxy.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return proxy.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return proxy.containsValue(value);
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return proxy.entrySet();
    }

    @Override
    public V get(Object key) {
        return proxy.get(key);
    }

    @Override
    public boolean isEmpty() {
        return proxy.isEmpty();
    }

    @Override
    public Set<K> keySet() {
        return proxy.keySet();
    }

    @Override
    public V put(K key, V value) {
        return proxy.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        proxy.putAll(m);
    }

    @Override
    public V remove(Object key) {
        return proxy.remove(key);
    }

    @Override
    public int size() {
        return proxy.size();
    }

    @Override
    public Collection<V> values() {
        return proxy.values();
    }

    @Override
    public boolean equals(Object o) {
        return proxy.equals(o);
    }

    @Override
    public int hashCode() {
        return proxy.hashCode();
    }

}
