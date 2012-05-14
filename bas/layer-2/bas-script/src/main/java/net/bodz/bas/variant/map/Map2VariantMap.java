package net.bodz.bas.variant.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.model.IDecorator;

public class Map2VariantMap<K>
        extends AbstractVariantMap<K>
        implements IDecorator<Map<K, Object>> {

    private Map<K, Object> impl;

    public Map2VariantMap(Map<K, Object> impl) {
        if (impl == null)
            throw new NullPointerException("impl");
        this.impl = impl;
    }

    @Override
    public Map<K, Object> getImplementation() {
        return impl;
    }

    public void setImplementation(Map<K, Object> impl) {
        this.impl = impl;
    }

    @Override
    public boolean containsKey(Object key) {
        return impl.containsKey(key);
    }

    @Override
    public Object get(Object key) {
        return impl.get(key);
    }

    @Override
    public Set<K> keySet() {
        return impl.keySet();
    }

    // Map implementation.

    @Override
    public int size() {
        return impl.size();
    }

    @Override
    public boolean isEmpty() {
        return impl.isEmpty();
    }

    @Override
    public boolean containsValue(Object value) {
        return impl.containsValue(value);
    }

    @Override
    public Object put(K key, Object value) {
        return impl.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return impl.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends Object> m) {
        impl.putAll(m);
    }

    @Override
    public void clear() {
        impl.clear();
    }

    @Override
    public Collection<Object> values() {
        return impl.values();
    }

    @Override
    public Set<java.util.Map.Entry<K, Object>> entrySet() {
        return impl.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return impl.equals(o);
    }

    @Override
    public int hashCode() {
        return impl.hashCode();
    }

}
