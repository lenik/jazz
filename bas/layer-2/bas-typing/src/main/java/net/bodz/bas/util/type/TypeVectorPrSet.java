package net.bodz.bas.util.type;

import net.bodz.bas.collection.preorder.PreorderSet;
import net.bodz.bas.util.type.order.TypeVectorPreorder;

public class TypeVectorPrSet
        extends PreorderSet<Class<?>[]> {

    private static final long serialVersionUID = 1L;

    public TypeVectorPrSet() {
        super(TypeVectorPreorder.getInstance());
    }

}
