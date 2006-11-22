package net.bodz.six.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChainedMap<K, V> implements Map<K, V> {

    LinkedList<Map<K, V>> chain = new LinkedList<Map<K, V>>();

    boolean onlytop = true;

    public Map<K, V> findKey(Object key) {
        if (chain.isEmpty())
            return null;
        Iterator<Map<K, V>> it = chain.iterator();
        while (it.hasNext()) {
            Map<K, V> map = it.next();
            if (map.containsKey(key))
                return map;
        }
        return null;
    }

    public void clear() {
        if (chain.isEmpty())
            return;
        if (onlytop)
            chain.getFirst().clear();
        else {
            Iterator<Map<K, V>> it = chain.iterator();
            while (it.hasNext()) {
                it.next().clear();
            }
        }
    }

    public boolean containsKey(Object key) {
        return findKey(key) != null;
    }

    public boolean containsValue(Object value) {
        if (chain.isEmpty())
            return false;
        Iterator<Map<K, V>> it = chain.iterator();
        while (it.hasNext()) {
            Map<K, V> map = it.next();
            if (map.containsValue(value))
                return true;
        }
        return false;
    }

    public Set<java.util.Map.Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = null;
        if (!chain.isEmpty()) {
            if (onlytop)
                set = chain.getFirst().entrySet();
            else {
                set = new HashSet<Entry<K, V>>();
                Iterator<Map<K, V>> it = chain.iterator();
                while (it.hasNext()) {
                    Map<K, V> map = it.next();
                    set.addAll(map.entrySet());
                }
            }
        }
        if (set == null)
            set = new HashSet<Entry<K, V>>();
        return set;
    }

    public V get(Object key) {
        if (chain.isEmpty())
            return null;
        Iterator<Map<K, V>> it = chain.iterator();
        while (it.hasNext()) {
            Map<K, V> map = it.next();
            V value = map.get(key); // optimize
            if (value != null)
                return value;
            if (map.containsKey(key))
                return null; // value = 'null'
        }
        return null; // key isn't existed.
    }

    public boolean isEmpty() {
        if (chain.isEmpty())
            return true;
        if (onlytop)
            return chain.getFirst().isEmpty();
        else {
            Iterator<Map<K, V>> it = chain.iterator();
            while (it.hasNext()) {
                Map<K, V> map = it.next();
                if (!map.isEmpty())
                    return false;
            }
        }
        return true;
    }

    public Set<K> keySet() {
        Set<K> set = null;
        if (!chain.isEmpty()) {
            if (onlytop)
                set = chain.getFirst().keySet();
            else {
                set = new HashSet<K>();
                Iterator<Map<K, V>> it = chain.iterator();
                while (it.hasNext()) {
                    Map<K, V> map = it.next();
                    set.addAll(map.keySet());
                }
            }
        }
        if (set == null)
            set = new HashSet<K>();
        return set;
    }

    public V put(K key, V value) {
        assert !chain.isEmpty() : "Chain isn't ready to modify";
        Map<K, V> map = findKey(key);
        if (map == null)
            map = chain.getFirst();
        return map.put(key, value); 
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        assert !chain.isEmpty() : "Chain isn't ready to modify";
        Iterator<? extends Map.Entry<? extends K, ? extends V>> it = m.entrySet().iterator();
        while (it.hasNext()) {
            Entry<? extends K, ? extends V> e = it.next();
            put(e.getKey(), e.getValue());
        }
    }

    public V remove(Object key) {
        if (chain.isEmpty())
            return null;
        Map<K, V> map = findKey(key);
        if (map == null)
            return null;
        return map.remove(key);
    }

    public int size() {
        if (chain.isEmpty())
            return 0;
        if (onlytop)
            return chain.getFirst().size();
        int count = 0;
        Iterator<Map<K, V>> it = chain.iterator();
        while (it.hasNext()) {
            Map<K, V> map = it.next();
            count += map.size();
        }
        return count;
    }

    public Collection<V> values() {
        if (chain.isEmpty())
            return null;
        if (onlytop)
            return chain.getFirst().values();
        List<V> values = new ArrayList<V>(); 
        Iterator<Map<K, V>> it = chain.iterator();
        while (it.hasNext()) {
            Map<K, V> map = it.next();
            values.addAll(map.values());
        }
        return values;
    }

}
