package net.bodz.bas.t.map;

import java.util.Map;

import net.bodz.bas.proxy.java.util.DecoratedMap;

public class DecoratedParsedMap<K, V>
        extends DecoratedMap<K, V>
        implements IParsedMap<K, V> {

    private static final long serialVersionUID = 1L;

    public DecoratedParsedMap(Map<K, V> _orig) {
        super(_orig);
    }

}
