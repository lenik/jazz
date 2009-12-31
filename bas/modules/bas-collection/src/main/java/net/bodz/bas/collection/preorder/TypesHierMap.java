package net.bodz.bas.collection.preorder;

public class TypesHierMap<V>
        extends PreorderMap<Class<?>[], V> {

    private static final long serialVersionUID = 1L;

    public TypesHierMap() {
        super(TypeVectorInheritancePreorder.getInstance());
    }

}
