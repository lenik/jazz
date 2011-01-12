package net.bodz.bas.collection.preorder;

public class TypesHierSet
        extends PreorderSet<Class<?>[]> {

    private static final long serialVersionUID = 1L;

    public TypesHierSet() {
        super(TypeVectorInheritancePreorder.getInstance());
    }

}
