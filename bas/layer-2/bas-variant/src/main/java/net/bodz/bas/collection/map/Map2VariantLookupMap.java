package net.bodz.bas.collection.map;

import java.util.Map;
import java.util.Set;

public class Map2VariantLookupMap<K, V>
        extends AbstractVariantLookupMap<K> {

    private final Map<K, V> proxy;

    public Map2VariantLookupMap(Map<K, V> proxy) {
        if (proxy == null)
            throw new NullPointerException("proxy");
        this.proxy = proxy;
    }

    @Override
    public boolean containsKey(Object key) {
        return proxy.containsKey(key);
    }

    @Override
    public V get(Object key) {
        return proxy.get(key);
    }

    @Override
    public Set<K> keySet() {
        return proxy.keySet();
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
