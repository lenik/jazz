package net.bodz.bas.t.variant;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.t.model.IWrapper;

public class VariantMap<K>
        extends AbstractTmVariantLookupMap<K>
        implements IWrapper<Map<K, Object>>, Map<K, Object>, Serializable {

    private static final long serialVersionUID = 1L;

    private Map<K, Object> map;

    /**
     * Set the wrapped map later.
     */
    public VariantMap() {
        this.map = Collections.emptyMap();
    }

    public VariantMap(Map<K, Object> map) {
        if (map == null)
            throw new NullPointerException("map");
        this.map = map;
    }

    @Override
    public Map<K, Object> getWrapped() {
        return map;
    }

    public void setWrapped(Map<K, Object> map) {
        if (map == null)
            throw new NullPointerException("map");
        this.map = map;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    /** ⇱ Implementaton Of {@link Map}. */
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
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Object put(K key, Object value) {
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends Object> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public Set<java.util.Map.Entry<K, Object>> entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return map.equals(o);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Entry<K, Object> entry : entrySet()) {
            sb.append(entry.getKey());
            sb.append(" = ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

}
