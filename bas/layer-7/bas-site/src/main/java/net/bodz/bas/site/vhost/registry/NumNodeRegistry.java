package net.bodz.bas.site.vhost.registry;

import net.bodz.bas.repr.form.SortOrder;

public class NumNodeRegistry<node_t extends NumNodeRegistry<node_t, value_t>, value_t>
        extends NumberRegistry<node_t> {

    value_t value;

    public NumNodeRegistry() {
        super();
    }

    public NumNodeRegistry(SortOrder order) {
        super(order);
    }

    public value_t getValue() {
        return value;
    }

    public void setValue(value_t value) {
        this.value = value;
    }

}
