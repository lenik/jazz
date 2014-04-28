package net.bodz.bas.c.type;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TypeMath {

    public static final int DEFAULT_NULL_DISTANCE = 1000;

    /**
     * @return -1 If actualClass isn't subclass of declClass.
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     * @throws IllegalArgumentException
     *             If <code>declClass</code> is interface.
     */
    public static int getClassExtendsCount(Class<?> declClass, Class<?> actualClass) {
        if (declClass.isInterface())
            throw new IllegalArgumentException("declClass is interface: " + declClass);

        int dist = -1;
        while (declClass.isAssignableFrom(actualClass)) {
            dist++;
            actualClass = actualClass.getSuperclass();
            if (actualClass == null)
                break;
        }
        return dist;
    }

    /**
     * @return -1 If actualClass doesn't implements declInterface.
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     * @throws IllegalArgumentException
     *             If <code>declInterface</code> isn't interface.
     */
    public static int getMinInterfaceExtendsCount(Class<?> declInterface, Class<?> actualClass) {
        if (actualClass == null)
            throw new NullPointerException("actualClass");
        if (!declInterface.isInterface())
            throw new IllegalArgumentException("declInterface isn't interface: " + declInterface);
        int min = Integer.MAX_VALUE;
        int classExtends = -1;
        while (actualClass != null) {
            Class<?>[] actualInterfaces = actualClass.getInterfaces();
            actualClass = actualClass.getSuperclass();
            classExtends++;
            if (actualInterfaces.length == 0)
                continue;
            int _dist = getMinInterfaceExtendsCount(declInterface, actualInterfaces);
            if (_dist == -1)
                continue;
            int dist = classExtends + _dist + 1;
            min = Math.min(min, dist);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * @return -1 If none of <code>actualInterfaces</code> implements <code>declInterface</code>.
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     * @throws IllegalArgumentException
     *             If <code>declInterface</code> isn't interface.
     */
    public static int getMinInterfaceExtendsCount(Class<?> declInterface, Class<?>[] actualInterfaces) {
        if (declInterface == null)
            throw new NullPointerException("declInterface");
        if (!declInterface.isInterface())
            throw new IllegalArgumentException("declInterface isn't interface: " + declInterface);
        if (actualInterfaces.length == 0)
            return -1; // throw new IllegalArgumentException("No actual interface specified");
        int min = Integer.MAX_VALUE;
        for (Class<?> actualInterface : actualInterfaces) {
            if (declInterface.isAssignableFrom(actualInterface)) {
                int dist = 0;
                Class<?>[] superInterfaces = actualInterface.getInterfaces();
                if (superInterfaces.length != 0) {
                    int superDist = getMinInterfaceExtendsCount(declInterface, superInterfaces);
                    if (superDist != -1)
                        dist += superDist + 1;
                }
                min = Math.min(min, dist);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * Get the inheritance-distance from <code>declType</code> to <code>actualType</code>.
     * <p>
     * The distance is directed, i.e., <b>dist(a, b) != decl(b, a) with exception of a==b</b>.
     *
     * @param declType
     *            The "base" type
     * @param actualType
     *            The "child" type
     * @return -1 If declType isn't assignable from actualType.
     * @throws NullPointerException
     *             If <code>declType</code> or <code>actualType</code> is <code>null</code>.
     */
    public static int dist(Class<?> declType, Class<?> actualType) {
        if (declType.isInterface())
            return getMinInterfaceExtendsCount(declType, actualType);
        else
            return getClassExtendsCount(declType, actualType);
    }

    /**
     * Calculate distance from one base type vector to another derived type vector.
     *
     * The same to {@link #dist(Class[], Class[], int)} with <code>nullDistance</code> set to
     * {@value #DEFAULT_NULL_DISTANCE}.
     *
     * @return -1 If <code>actualTypes</code> is not compatible with <code>declTypes</code>. I.e.,
     *         they have different lengths, or any decl-type is not assignable from corresponding
     *         actual-type.
     * @throws NullPointerException
     *             If <code>declTypes</code> or <code>actualTypes</code> is <code>null</code>. Or,
     *             if <code>declTypes</code> contains <code>null</code> element.
     */
    public static int dist(Class<?>[] declTypes, Class<?>[] actualTypes) {
        return dist(declTypes, actualTypes, DEFAULT_NULL_DISTANCE);
    }

    /**
     * Calculate distance from one base type vector to another derived type vector.
     *
     * @param nullDistance
     *            The fallback distance from declType to <code>null</code>.
     * @return -1 If <code>actualTypes</code> is not compatible with <code>declTypes</code>. I.e.,
     *         they have different lengths, or any decl-type is not assignable from corresponding
     *         actual-type.
     * @throws NullPointerException
     *             If <code>declTypes</code> or <code>actualTypes</code> is <code>null</code>. Or,
     *             if <code>declTypes</code> contains <code>null</code> element.
     */
    public static int dist(Class<?>[] declTypes, Class<?>[] actualTypes, int nullDistance) {
        if (declTypes == null)
            throw new NullPointerException("declTypes");
        if (actualTypes == null)
            throw new NullPointerException("actualTypes");
        if (declTypes.length != actualTypes.length)
            return -1;

        int totalDistance = 0;

        for (int i = 0; i < declTypes.length; i++) {
            Class<?> declType = declTypes[i];
            Class<?> actualType = actualTypes[i];

            if (actualType == null) {
                totalDistance += nullDistance;
                continue;
            }

            int distance = dist(declType, actualType);
            if (distance == -1)
                return -1;

            totalDistance += distance;
        }
        return totalDistance;
    }

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
