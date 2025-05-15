package net.bodz.bas.t.specmap;

import java.util.Map;

import net.bodz.bas.repr.form.SortOrder;

public class DefaultSpecMap<key_t, val_t>
        extends AbstractSpecMap<key_t, val_t> {

    private final Map<key_t, val_t> map;

    public DefaultSpecMap() {
        this(SortOrder.NONE);
    }

    public DefaultSpecMap(SortOrder order) {
        this(order.newMapDefault());
    }

    public DefaultSpecMap(Map<key_t, val_t> map) {
        this.map = map;
    }

    @Override
    public Map<key_t, val_t> getMap() {
        return map;
    }

}
