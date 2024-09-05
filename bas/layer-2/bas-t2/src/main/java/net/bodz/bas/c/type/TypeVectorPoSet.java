package net.bodz.bas.c.type;

import net.bodz.bas.t.preorder.PreorderSet;

public class TypeVectorPoSet
        extends PreorderSet<Class<?>[]> {

    private static final long serialVersionUID = 1L;

    public TypeVectorPoSet() {
        super(TypeVectorPreorder.getInstance());
    }

}
