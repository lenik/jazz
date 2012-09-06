package net.bodz.bas.util.variant;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.model.IWrapper;

public class Map2VariantMap<K>
        extends AbstractVariantMap<K>
        implements IWrapper<Map<K, Object>> {

    private Map<K, Object> _orig;

    public Map2VariantMap(Map<K, Object> orig) {
        if (orig == null)
            throw new NullPointerException("orig");
        this._orig = orig;
    }

    @Override
    public Map<K, Object> getWrapped() {
        return _orig;
    }

    public void setWrapped(Map<K, Object> _orig) {
        if (_orig == null)
            throw new NullPointerException("_orig");
        this._orig = _orig;
    }

    @Override
    public boolean containsKey(Object key) {
        return _orig.containsKey(key);
    }

    @Override
    public Object get(Object key) {
        return _orig.get(key);
    }

    @Override
    public Set<K> keySet() {
        return _orig.keySet();
    }

    // Map implementation.

    @Override
    public int size() {
        return _orig.size();
    }

    @Override
    public boolean isEmpty() {
        return _orig.isEmpty();
    }

    @Override
    public boolean containsValue(Object value) {
        return _orig.containsValue(value);
    }

    @Override
    public Object put(K key, Object value) {
        return _orig.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return _orig.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends Object> m) {
        _orig.putAll(m);
    }

    @Override
    public void clear() {
        _orig.clear();
    }

    @Override
    public Collection<Object> values() {
        return _orig.values();
    }

    @Override
    public Set<java.util.Map.Entry<K, Object>> entrySet() {
        return _orig.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return _orig.equals(o);
    }

    @Override
    public int hashCode() {
        return _orig.hashCode();
    }

}
