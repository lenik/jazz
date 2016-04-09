package net.bodz.bas.ctx.util;

/**
 * Always define local variable when the name is defined in parent scope.
 */
public class StrictFramedMap<K, V>
        extends AbstractFramedMap<K, V> {

    public StrictFramedMap() {
        super();
    }

    public StrictFramedMap(Boolean order) {
        super(order);
    }

    @Override
    public V put(K key, V value) {
        return define(key, value, true);
    }

}
