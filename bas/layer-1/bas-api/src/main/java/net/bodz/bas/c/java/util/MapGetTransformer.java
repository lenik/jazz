package net.bodz.bas.c.java.util;

import java.util.Map;
import java.util.function.Function;

public class MapGetTransformer<K, V>
        implements
            Function<K, V> {

    private final Map<?, ? extends V> map;

    public MapGetTransformer(Map<?, ? extends V> map) {
        if (map == null)
            throw new NullPointerException("map");
        this.map = map;
    }

    @Override
    public V apply(K input) {
        V value = map.get(input);
        return value;
    }

}
