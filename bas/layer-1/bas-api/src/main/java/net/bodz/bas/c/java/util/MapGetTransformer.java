package net.bodz.bas.c.java.util;

import java.util.Map;

import net.bodz.bas.err.TransformException;
import net.bodz.bas.fn.ITransformer;

public class MapGetTransformer<K, V>
        implements ITransformer<K, V> {

    private final Map<?, ? extends V> map;

    public MapGetTransformer(Map<?, ? extends V> map) {
        if (map == null)
            throw new NullPointerException("map");
        this.map = map;
    }

    @Override
    public V transform(K input)
            throws TransformException {
        V value = map.get(input);
        return value;
    }

}
