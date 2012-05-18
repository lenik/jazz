package net.bodz.bas.proxy.java.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.model.AbstractDecorator;

public class MapDecorator<K, V>
        extends AbstractDecorator<Map<K, V>>
        implements Map<K, V> {

    private static final long serialVersionUID = 1L;

    public MapDecorator(Map<K, V> _orig) {
        super(_orig);
    }

    @Override
    public void clear() {
        _orig.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return _orig.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return _orig.containsValue(value);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return _orig.entrySet();
    }

    @Override
    public V get(Object key) {
        return _orig.get(key);
    }

    @Override
    public boolean isEmpty() {
        return _orig.isEmpty();
    }

    @Override
    public Set<K> keySet() {
        return _orig.keySet();
    }

    @Override
    public V put(K key, V value) {
        return _orig.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        _orig.putAll(m);
    }

    @Override
    public V remove(Object key) {
        return _orig.remove(key);
    }

    @Override
    public int size() {
        return _orig.size();
    }

    @Override
    public Collection<V> values() {
        return _orig.values();
    }

}
