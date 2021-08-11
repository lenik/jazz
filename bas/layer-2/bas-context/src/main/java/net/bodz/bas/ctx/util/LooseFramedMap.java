package net.bodz.bas.ctx.util;

import net.bodz.bas.repr.form.SortOrder;

/**
 * Define local variable only if the name isn't defined.
 */
public class LooseFramedMap<K, V>
        extends AbstractFramedMap<K, V> {

    public LooseFramedMap() {
        super();
    }

    public LooseFramedMap(SortOrder order) {
        super(order);
    }

    @Override
    public V put(K key, V value) {
        return define(key, value, false);
    }

}
