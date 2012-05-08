package net.bodz.bas.c.type;

import java.util.Map;

import net.bodz.bas.proxy.java.util.MapDecorator;

public class LazyEntryMap<K, V>
        extends MapDecorator<K, V> {

    private static final long serialVersionUID = 1L;

    IMapEntryBuilder<K, V> entryBuilder;

    public LazyEntryMap(Map<K, V> impl, IMapEntryBuilder<K, V> entryBuilder) {
        super(impl);
        this.entryBuilder = entryBuilder;
    }

    @Override
    public boolean containsKey(Object key) {
        return super.containsKey(key);
    }

    @Override
    public V get(Object key) {
        return super.get(key);
    }

}
