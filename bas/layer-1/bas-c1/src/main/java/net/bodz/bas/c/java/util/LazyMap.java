package net.bodz.bas.c.java.util;

import java.util.Map;

import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.proxy.java.util.DecoratedMap;

/**
 * Entries are created/initialized when {@link #get(Object) get()} method is called.
 * <p>
 * The {@link Map#size() size}, {@link Map#entrySet() entry-set}, {@link Map#keySet() key-set},
 * {@link Map#values() values} of a lazy map only returns initialized ones.
 */
public abstract class LazyMap<K, V>
        extends DecoratedMap<K, V> {

    private static final long serialVersionUID = 1L;

    private final IMapEntryLoader<K, V> entryLoader;

    public LazyMap(IMapEntryLoader<K, V> entryLoader, Map<K, V> _orig) {
        super(_orig);
        this.entryLoader = entryLoader;
    }

    /**
     * Get or load the value of the entry with specified key.
     * 
     * If the entry isn't existed, load it first thru {@link IMapEntryLoader entry loader}.
     * 
     * @param key
     *            Which entry to get or load.
     * @return Value of the entry in the map. <code>null</code> if not existed. (however, the entry
     *         loader may also return <code>null</code> )
     * @throws LazyLoadException
     *             If the entry for the specific key can't be loaded.
     */
    public V getOrLoad(K key)
            throws LazyLoadException {
        V value = super.get(key);
        if (value == null) {
            synchronized (this) {
                if (containsKey(key))
                    // return value; -- thread-unsafe.
                    value = super.get(key);
                else {
                    value = entryLoader.loadValue(key);
                    put(key, value);
                }
            }
        }
        return value;
    }

}
