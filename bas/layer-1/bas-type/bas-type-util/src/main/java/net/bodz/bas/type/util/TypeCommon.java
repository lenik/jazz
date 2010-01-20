package net.bodz.bas.type.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.collection.comparator.TypeComparator;

public class TypeCommon {

    public static Class<?> getCommonSuperclass(Class<?> a, Class<?> b) {
        while (a != null && !a.isAssignableFrom(b))
            a = a.getSuperclass();
        return a;
    }

    public static Class<?> getCommonSuperclass(Collection<Class<?>> classes) {
        Class<?> base = null;
        for (Class<?> clazz : classes) {
            if (base == null)
                base = clazz;
            else
                base = getCommonSuperclass(base, clazz);
        }
        return base;
    }

    static class CommonInterfaces {

        private Set<Class<?>> trueSet;
        private Set<Class<?>> falseSet;

        public CommonInterfaces(boolean sort) {
            if (sort) {
                trueSet = new TreeSet<Class<?>>(TypeComparator.getInstance());
            } else
                trueSet = new HashSet<Class<?>>();
            falseSet = new HashSet<Class<?>>();
        }

        public void _solve(Class<?> a, Class<?> b) {
            assert !a.isAssignableFrom(b) : "illegal usage";
            for (Class<?> iface : a.getInterfaces()) {
                if (iface.isAssignableFrom(b))
                    trueSet.add(iface);
                else {
                    if (falseSet.add(iface)) {
                        trueSet.remove(iface);
                        _solve(iface, b);
                    }
                }
            }
            Class<?> _super = a.getSuperclass();
            if (_super != null)
                _solve(_super, b);
        }

        public Class<?>[] solve(Class<?> a, Class<?> b) {
            _solve(a, b);
            return trueSet.toArray(new Class<?>[0]);
        }

    }

    public static Class<?>[] getCommonInterfaces(Class<?> a, Class<?> b, boolean sort) {
        Class<?> gcd = getCommonSuperclass(a, b);
        if (gcd != null)
            return new Class<?>[] { gcd };
        CommonInterfaces igcd = new CommonInterfaces(sort);
        return igcd.solve(a, b);
    }

    public static Class<?>[] getCommonInterfaces(Class<?> a, Class<?> b) {
        return getCommonInterfaces(a, b, false);
    }

    /** under dev */
    @Deprecated
    public static Class<?>[] getCommonInterfaces(Collection<Class<?>> types, boolean sort) {
        CommonInterfaces igcd = new CommonInterfaces(sort);
        Class<?> prev = null;
        for (Class<?> clazz : types) {
            if (prev != null)
                igcd._solve(prev, clazz);
            prev = clazz;
        }
        return igcd.trueSet.toArray(new Class<?>[0]);
    }

    public static Class<?>[] getCommonInterfaces(Collection<Class<?>> types) {
        return getCommonInterfaces(types, false);

    }

}
