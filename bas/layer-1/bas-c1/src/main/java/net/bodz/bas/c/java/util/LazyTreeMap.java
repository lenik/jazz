package net.bodz.bas.c.java.util;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class LazyTreeMap<K, V>
        extends LazyMap<K, V> {

    private static final long serialVersionUID = 1L;

    public LazyTreeMap(IMapEntryLoader<K, V> entryLoader) {
        super(entryLoader, new TreeMap<K, V>());
    }

    public LazyTreeMap(IMapEntryLoader<K, V> entryLoader, Comparator<? super K> comparator) {
        super(entryLoader, new TreeMap<K, V>(comparator));
    }

    public LazyTreeMap(IMapEntryLoader<K, V> entryLoader, Map<? extends K, ? extends V> m) {
        super(entryLoader, new TreeMap<K, V>(m));
    }

    public LazyTreeMap(IMapEntryLoader<K, V> entryLoader, SortedMap<K, ? extends V> m) {
        super(entryLoader, new TreeMap<K, V>(m));
    }

}
