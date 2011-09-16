package net.bodz.bas.util.type;

import net.bodz.bas.collection.preorder.PreorderSet;
import net.bodz.bas.util.type.order.TypePreorder;

public class TypePrSet
        extends PreorderSet<Class<?>> {

    private static final long serialVersionUID = 1L;

    public TypePrSet() {
        super(TypePreorder.getInstance());
    }

}
