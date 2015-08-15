package net.bodz.bas.t.map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.err.OverflowError;
import net.bodz.bas.proxy.java.util.DecoratedMap;

public abstract class AbstractMapOst<K, V>
        extends DecoratedMap<K, Map<K, V>>
        implements IMapOst<K, V> {

    private static final long serialVersionUID = 1L;

    public AbstractMapOst(boolean sorted) {
        this(sorted ? new TreeMap<K, Map<K, V>>() : new HashMap<K, Map<K, V>>());
    }

    public AbstractMapOst(Map<K, Map<K, V>> _orig) {
        super(_orig);
    }

    protected abstract Map<K, V> create2();

    @Override
    public boolean containsKey(K key1, K key2) {
        Map<K, V> map2 = get(key1);
        if (map2 == null)
            return false;
        else
            return map2.containsKey(key2);
    }

    @Override
    public Map<K, V> getOrCreate(K key1) {
        Map<K, V> map2 = get(key1);
        if (map2 == null) {
            map2 = create2();
            super.put(key1, map2);
        }
        return map2;
    }

    @Override
    public V get(K key1, K key2) {
        Map<K, V> map2 = get(key1);
        if (map2 == null)
            return null;
        else
            return map2.get(key2);
    }

    @Override
    public V put(K key1, K key2, V value) {
        Map<K, V> map2 = getOrCreate(key1);
        return map2.put(key2, value);
    }

    @Override
    public V remove(K key1, K key2) {
        Map<K, V> map2 = get(key1);
        if (map2 == null)
            return null;
        else
            return map2.remove(key2);
    }

    @Override
    public int size2() {
        int size2 = 0;
        for (Map<K, V> map : values()) {
            int size = map.size();
            size2 += size;
            if (size2 < size)
                throw new OverflowError();
        }
        return size2;
    }

}
