package net.bodz.bas.collection.preorder;

public class TypeHierMap<V>
        extends PreorderMap<Class<?>, V> {

    private static final long serialVersionUID = 1L;

    public TypeHierMap() {
        super(ClassInheritancePreorder.getInstance());
    }

}
