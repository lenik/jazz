package net.bodz.bas.c.java.util;

import java.util.HashMap;
import java.util.Map;

public class LazyHashMap<K, V>
        extends LazyMap<K, V> {

    private static final long serialVersionUID = 1L;

    public LazyHashMap(IMapEntryLoader<K, V> entryLoader) {
        super(entryLoader, new HashMap<K, V>());
    }

    public LazyHashMap(IMapEntryLoader<K, V> entryLoader, int initialCapacity) {
        super(entryLoader, new HashMap<K, V>(initialCapacity));
    }

    public LazyHashMap(IMapEntryLoader<K, V> entryLoader, Map<? extends K, ? extends V> m) {
        super(entryLoader, new HashMap<K, V>(m));
    }

}
