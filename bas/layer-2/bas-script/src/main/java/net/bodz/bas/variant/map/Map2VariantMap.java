package net.bodz.bas.variant.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Map2VariantMap<K>
        extends AbstractVariantMap<K> {

    private final Map<K, Object> proxy;

    public Map2VariantMap(Map<K, Object> proxy) {
        if (proxy == null)
            throw new NullPointerException("proxy");
        this.proxy = proxy;
    }

    @Override
    public boolean containsKey(Object key) {
        return proxy.containsKey(key);
    }

    @Override
    public Object get(Object key) {
        return proxy.get(key);
    }

    @Override
    public Set<K> keySet() {
        return proxy.keySet();
    }

    // Map implementation.

    @Override
    public int size() {
        return proxy.size();
    }

    @Override
    public boolean isEmpty() {
        return proxy.isEmpty();
    }

    @Override
    public boolean containsValue(Object value) {
        return proxy.containsValue(value);
    }

    @Override
    public Object put(K key, Object value) {
        return proxy.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return proxy.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends Object> m) {
        proxy.putAll(m);
    }

    @Override
    public void clear() {
        proxy.clear();
    }

    @Override
    public Collection<Object> values() {
        return proxy.values();
    }

    @Override
    public Set<java.util.Map.Entry<K, Object>> entrySet() {
        return proxy.entrySet();
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
