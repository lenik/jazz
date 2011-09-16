package net.bodz.bas.util.type;

import net.bodz.bas.collection.preorder.PreorderTreeMap;
import net.bodz.bas.util.type.order.TypeVectorPreorder;

public class TypeVectorPrMap<V>
        extends PreorderTreeMap<Class<?>[], V> {

    private static final long serialVersionUID = 1L;

    public TypeVectorPrMap() {
        super(TypeVectorPreorder.getInstance());
    }

}
