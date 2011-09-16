package net.bodz.bas.collection.preorder;

public class TypePrSet
        extends PreorderSet<Class<?>> {

    private static final long serialVersionUID = 1L;

    public TypePrSet() {
        super(TypePreorder.getInstance());
    }

}
