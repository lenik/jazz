package net.bodz.bas.collection.preorder;

/**
 * @test {@link TypeHierSetTest}
 */
@Deprecated
public class TypeHierSet
        extends PreorderSet<Class<?>> {

    private static final long serialVersionUID = 1L;

    public TypeHierSet() {
        super(ClassInheritancePreorder.getInstance());
    }

}
