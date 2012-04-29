package net.bodz.bas.disp.builtin;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ClassMap<K, V>
        extends HashMap<Class<?>, Map<K, V>> {

    private static final long serialVersionUID = 1L;

    protected Map<K, V> newSubMap() {
        return new HashMap<K, V>();
    }

    public boolean contains(Class<?> clazz, K key) {
        Map<K, V> map = get(clazz);
        if (map == null)
            return false;
        return map.containsKey(key);
    }

    public V get(Class<?> clazz, K key) {
        Map<K, V> map = get(clazz);
        if (map == null)
            return null;
        return map.get(key);
    }

    public synchronized V put(Class<?> clazz, K key, V value) {
        Map<K, V> map = get(clazz);
        if (map == null)
            put(clazz, map = newSubMap());
        return map.put(key, value);
    }

    public V remove(Class<?> clazz, K key) {
        Map<K, V> map = get(clazz);
        if (map == null)
            return null;
        return map.remove(key);
    }

    public Set<K> keySet(Class<?> clazz) {
        Map<K, V> map = get(clazz);
        if (map == null)
            return Collections.emptySet();
        return map.keySet();
    }

    public Collection<V> values(Class<?> clazz) {
        Map<K, V> map = get(clazz);
        if (map == null)
            return Collections.emptySet();
        return map.values();
    }

}
