package net.bodz.bas.collection.hierarchical;

import net.bodz.bas.collection.comparator.TypeVectorComparator;

public class TypesHierSet
        extends HierSet<Class<?>[]> {

    private static final long serialVersionUID = 1L;

    public TypesHierSet() {
        super(TypeVectorComparator.getInstance());
    }

    static boolean _derives(Class<?>[] av, Class<?>[] bv) {
        if (av == bv)
            return true;
        if (av.length != bv.length)
            return false;
        for (int i = 0; i < av.length; i++) {
            Class<?> a = av[i];
            Class<?> b = bv[i];
            if (!a.isAssignableFrom(b))
                return false;
        }
        return true;
    }

    static Class<?>[] _shrink(Class<?>[] v) {
        for (int i = v.length - 1; i >= 0; i--) {
            assert v[i] != null;
            if (v[i].isInterface())
                continue;
            if (v[i] != Object.class) {
                v[i] = v[i].getSuperclass();
                return v;
            }
        }
        return null;
    }

    @Override
    public boolean derives(Class<?>[] sup, Class<?>[] sub) {
        return _derives(sup, sub);
    }

    @Override
    public Class<?>[] shrink(Class<?>[] e) {
        // copy
        return _shrink(e);
    }

}
