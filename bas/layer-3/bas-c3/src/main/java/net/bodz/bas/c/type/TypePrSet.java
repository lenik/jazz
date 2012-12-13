package net.bodz.bas.c.type;

import net.bodz.bas.c.type.order.TypePreorder;
import net.bodz.bas.t.preorder.PreorderSet;

public class TypePrSet
        extends PreorderSet<Class<?>> {

    private static final long serialVersionUID = 1L;

    public TypePrSet() {
        super(TypePreorder.getInstance());
    }

}
