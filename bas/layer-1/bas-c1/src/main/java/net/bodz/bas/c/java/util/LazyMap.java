package net.bodz.bas.c.java.util;

import java.util.Map;

import net.bodz.bas.proxy.java.util.MapDecorator;

/**
 * Entries are created/initialized when {@link #get(Object) get()} method is called.
 * <p>
 * The {@link Map#size() size}, {@link Map#entrySet() entry-set}, {@link Map#keySet() key-set},
 * {@link Map#values() values} of a lazy map only returns initialized ones.
 */
public abstract class LazyMap<K, V>
        extends MapDecorator<K, V> {

    private static final long serialVersionUID = 1L;

    private final IMapEntryLoader<K, V> entryLoader;

    public LazyMap(IMapEntryLoader<K, V> entryLoader, Map<K, V> _orig) {
        super(_orig);
        this.entryLoader = entryLoader;
    }

    @Override
    public V get(Object key) {
        V value = super.get(key);
        if (value == null) {
            synchronized (this) {
                if (containsKey(key))
                    // return value; -- thread-unsafe.
                    value = super.get(key);
                else {
                    @SuppressWarnings("unchecked")
                    K _key = (K) key;
                    value = entryLoader.loadEntry(_key);
                    put(_key, value);
                }
            }
        }
        return value;
    }

}
