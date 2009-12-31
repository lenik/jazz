package net.bodz.bas.collection.preorder;

public class ClassInheritancePreorder
        extends AbstractPreorder<Class<?>> {

    /**
     * @throws NullPointerException
     *             if <code>clazz</code> is <code>null</code>.
     */
    @Override
    public Class<?> getPreceding(Class<?> clazz) {
        return clazz.getSuperclass();
    }

    @Override
    public int precompare(Class<?> o1, Class<?> o2) {
        if (o1.equals(o2))
            return EQUALS;
        if (o1.isAssignableFrom(o2))
            return LESS_THAN;
        if (o2.isAssignableFrom(o1))
            return GREATER_THAN;
        return UNKNOWN;
    }

    static final ClassInheritancePreorder instance = new ClassInheritancePreorder();

    public static ClassInheritancePreorder getInstance() {
        return instance;
    }

}
