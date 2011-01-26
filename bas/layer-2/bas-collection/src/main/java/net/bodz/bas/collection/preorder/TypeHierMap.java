package net.bodz.bas.collection.preorder;

public class TypeHierMap<V>
        extends PreorderTreeMap<Class<?>, V> {

    private static final long serialVersionUID = 1L;

    public TypeHierMap() {
        super(ClassInheritancePreorder.getInstance());
    }

}
