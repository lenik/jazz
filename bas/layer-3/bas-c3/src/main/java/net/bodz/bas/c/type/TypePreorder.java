package net.bodz.bas.c.type;

import net.bodz.bas.t.preorder.AbstractPreorder;

public class TypePreorder
        extends AbstractPreorder<Class<?>> {

    @Override
    public int compare2(Class<?> o1, Class<?> o2) {
        if (o1.equals(o2))
            return EQUALS;

        Class<?>[] v1 = TypeChain.listSuperFirst(o1);
        Class<?>[] v2 = TypeChain.listSuperFirst(o2);
        int min = Math.min(v1.length, v2.length);
        for (int i = 0; i < min; i++) {
            Class<?> a = v1[i];
            Class<?> b = v2[i];
            if (a == b)
                continue;

            String an = a.getName();
            String bn = b.getName();
            int c = an.compareTo(bn);
            if (c < 0)
                return LESS_THAN;
            if (c > 0)
                return GREATER_THAN;
        }

        if (v1.length < min)
            return LESS_THAN;
        else
            return GREATER_THAN;
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
