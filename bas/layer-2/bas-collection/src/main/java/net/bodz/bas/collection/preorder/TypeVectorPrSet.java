package net.bodz.bas.collection.preorder;

public class TypeVectorPrSet
        extends PreorderSet<Class<?>[]> {

    private static final long serialVersionUID = 1L;

    public TypeVectorPrSet() {
        super(TypeVectorPreorder.getInstance());
    }

}
