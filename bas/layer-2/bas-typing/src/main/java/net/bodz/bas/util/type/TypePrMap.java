package net.bodz.bas.util.type;

import net.bodz.bas.collection.preorder.PreorderTreeMap;
import net.bodz.bas.util.type.order.TypePreorder;

public class TypePrMap<V>
        extends PreorderTreeMap<Class<?>, V> {

    private static final long serialVersionUID = 1L;

    public TypePrMap() {
        super(TypePreorder.getInstance());
    }

}
