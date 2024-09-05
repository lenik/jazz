package net.bodz.bas.c.type;

import net.bodz.bas.t.preorder.PreorderTreeMap;

public class TypePoMap<V>
        extends PreorderTreeMap<Class<?>, V> {

    private static final long serialVersionUID = 1L;

    public TypePoMap() {
        super(TypePreorder.getInstance());
    }

}
