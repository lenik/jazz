package net.bodz.bas.collection.preorder;

public class TypePrMap<V>
        extends PreorderMap<Class<?>, V> {

    private static final long serialVersionUID = 1L;

    public TypePrMap() {
        super(TypePreorder.getInstance());
    }

}
