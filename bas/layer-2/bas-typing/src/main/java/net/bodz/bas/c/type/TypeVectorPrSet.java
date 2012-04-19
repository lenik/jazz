package net.bodz.bas.c.type;

import net.bodz.bas.c.type.order.TypeVectorPreorder;
import net.bodz.bas.collection.preorder.PreorderSet;

public class TypeVectorPrSet
        extends PreorderSet<Class<?>[]> {

    private static final long serialVersionUID = 1L;

    public TypeVectorPrSet() {
        super(TypeVectorPreorder.getInstance());
    }

}
