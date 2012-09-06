package net.bodz.bas.util.variant;

import java.util.Map;
import java.util.Set;

public class Map2VariantLookupMap<K>
        extends AbstractVariantLookupMap<K> {

    private final Map<K, ?> proxy;

    public Map2VariantLookupMap(Map<K, ?> proxy) {
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

    @Override
    public boolean equals(Object o) {
        return proxy.equals(o);
    }

    @Override
    public int hashCode() {
        return proxy.hashCode();
    }

}
