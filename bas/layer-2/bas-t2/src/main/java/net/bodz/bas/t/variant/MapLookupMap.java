package net.bodz.bas.t.variant;

import java.util.Map;
import java.util.Set;

public class MapLookupMap<K, V>
        implements
            ILookupMap<K, V> {

    Map<K, V> map;

    public MapLookupMap(Map<K, V> map) {
        this.map = map;
    }

    public static <K, V> MapLookupMap<K, V> from(Map<K, V> map) {
        return new MapLookupMap<>(map);
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

}
