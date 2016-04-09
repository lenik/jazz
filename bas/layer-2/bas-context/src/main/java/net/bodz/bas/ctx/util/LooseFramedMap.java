package net.bodz.bas.ctx.util;

/**
 * Define local variable only if the name isn't defined.
 */
public class LooseFramedMap<K, V>
        extends AbstractFramedMap<K, V> {

    public LooseFramedMap() {
        super();
    }

    public LooseFramedMap(Boolean order) {
        super(order);
    }

    @Override
    public V put(K key, V value) {
        return define(key, value, false);
    }

}
