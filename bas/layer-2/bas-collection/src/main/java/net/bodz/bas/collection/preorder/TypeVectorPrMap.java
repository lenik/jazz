package net.bodz.bas.collection.preorder;

public class TypeVectorPrMap<V>
        extends PreorderTreeMap<Class<?>[], V> {

    private static final long serialVersionUID = 1L;

    public TypeVectorPrMap() {
        super(TypeVectorPreorder.getInstance());
    }

}
