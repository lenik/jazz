package net.bodz.bas.types;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;

import net.bodz.bas.types.util.Comparators;

public class TypesHierSet extends HierSet<Class<?>[]> {

    private static final long serialVersionUID = -8867244757204813499L;

    @Deprecated
    public TypesHierSet(Collection<? extends Class<?>[]> c) {
        super(c);
    }

    @Deprecated
    public TypesHierSet(Comparator<? super Class<?>[]> comparator) {
        super(comparator);
    }

    @Deprecated
    public TypesHierSet(SortedSet<Class<?>[]> s) {
        super(s);
    }

    public TypesHierSet() {
        super(Comparators.TYPES_HIER);
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
