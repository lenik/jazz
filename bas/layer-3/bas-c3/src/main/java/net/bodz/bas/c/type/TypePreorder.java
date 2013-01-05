package net.bodz.bas.c.type;

import net.bodz.bas.t.preorder.AbstractPreorder;

public class TypePreorder
        extends AbstractPreorder<Class<?>> {

    @Override
    public int compare2(Class<?> o1, Class<?> o2) {
        String name1 = o1.getName();
        String name2 = o2.getName();
        return name1.compareTo(name2);
    }

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

    static final TypePreorder instance = new TypePreorder();

    public static TypePreorder getInstance() {
        return instance;
    }

}
