package net.bodz.bas.c.type;

import net.bodz.bas.c.type.order.TypePreorder;
import net.bodz.bas.t.preorder.PreorderTreeMap;

public class TypePrMap<V>
        extends PreorderTreeMap<Class<?>, V> {

    private static final long serialVersionUID = 1L;

    public TypePrMap() {
        super(TypePreorder.getInstance());
    }

}
