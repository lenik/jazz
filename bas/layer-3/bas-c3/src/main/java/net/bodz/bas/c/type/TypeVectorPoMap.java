package net.bodz.bas.c.type;

import net.bodz.bas.c.type.order.TypeVectorPreorder;
import net.bodz.bas.t.preorder.PreorderTreeMap;

public class TypeVectorPoMap<V>
        extends PreorderTreeMap<Class<?>[], V> {

    private static final long serialVersionUID = 1L;

    public TypeVectorPoMap() {
        super(TypeVectorPreorder.getInstance());
    }

}
