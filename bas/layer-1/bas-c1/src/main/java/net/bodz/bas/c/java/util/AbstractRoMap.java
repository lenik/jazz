package net.bodz.bas.c.java.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.err.ReadOnlyException;

public abstract class AbstractRoMap<K, V>
        implements Map<K, V> {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet())
            put(entry.getKey(), entry.getValue());
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            for (K key : keySet())
                if (get(key) == null)
                    return true;
        } else {
            for (K key : keySet())
                if (value.equals(get(key)))
                    return true;
        }
        return false;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<V>();
        for (K key : keySet())
            values.add(get(key));
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("entrySet()");
    }

    @Override
    public V put(K key, V value) {
        throw new ReadOnlyException();
    }

    @Override
    public V remove(Object key) {
        throw new ReadOnlyException();
    }

    @Override
    public void clear() {
        throw new ReadOnlyException();
    }

}
