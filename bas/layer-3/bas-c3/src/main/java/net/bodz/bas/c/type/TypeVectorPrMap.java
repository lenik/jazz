package net.bodz.bas.c.type;

import net.bodz.bas.c.type.order.TypeVectorPreorder;
import net.bodz.bas.t.preorder.PreorderTreeMap;

public class TypeVectorPrMap<V>
        extends PreorderTreeMap<Class<?>[], V> {

    private static final long serialVersionUID = 1L;

    public TypeVectorPrMap() {
        super(TypeVectorPreorder.getInstance());
    }

}
