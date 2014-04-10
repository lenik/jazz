package net.bodz.bas.t.map;

import java.util.Map;

import net.bodz.bas.proxy.java.util.DecoratedMap;

public abstract class AbstractParsedMap<K, V>
        extends DecoratedMap<K, V>
        implements IParsedMap<K, V> {

    private static final long serialVersionUID = 1L;

    public AbstractParsedMap(Map<K, V> _orig) {
        super(_orig);
    }

}
