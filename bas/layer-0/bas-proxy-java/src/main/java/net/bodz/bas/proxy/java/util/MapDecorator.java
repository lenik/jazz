package net.bodz.bas.proxy.java.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.model.AbstractDecorator;

public class MapDecorator<K, V>
        extends AbstractDecorator<Map<K, V>>
        implements Map<K, V> {

    public MapDecorator(Map<K, V> impl) {
        super(impl);
    }

    @Override
    public void clear() {
        impl.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return impl.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return impl.containsValue(value);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return impl.entrySet();
    }

    @Override
    public V get(Object key) {
        return impl.get(key);
    }

    @Override
    public boolean isEmpty() {
        return impl.isEmpty();
    }

    @Override
    public Set<K> keySet() {
        return impl.keySet();
    }

    @Override
    public V put(K key, V value) {
        return impl.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        impl.putAll(m);
    }

    @Override
    public V remove(Object key) {
        return impl.remove(key);
    }

    @Override
    public int size() {
        return impl.size();
    }

    @Override
    public Collection<V> values() {
        return impl.values();
    }

}
