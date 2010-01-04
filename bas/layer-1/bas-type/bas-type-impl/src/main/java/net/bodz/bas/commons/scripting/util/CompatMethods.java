package net.bodz.bas.commons.scripting.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import net.bodz.bas.collection.array.Arrays2;
import net.bodz.bas.commons.util.Types;

public class CompatMethods {

    public static int dist(Class<?> root, Class<?> leaf) {
        assert root != null && leaf != null;
        int dist = -1;
        while (root.isAssignableFrom(leaf)) {
            dist++;
            leaf = leaf.getSuperclass();
            if (leaf == null)
                break;
        }
        return dist;
    }

    public static int dist(Class<?>[] roots, Class<?>[] leaves) {
        if (roots.length != leaves.length)
            return -1;
        int distsum = 0;
        for (int i = 0; i < roots.length; i++) {
            if (leaves[i] == null) // option?
                continue;
            int dist = dist(roots[i], leaves[i]);
            if (dist < 0)
                return -1;
            distsum += dist;
        }
        return distsum;
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>[] argtypes, boolean all) {
        int mindist = -1;
        Method min = null;
        for (Method method : methods(clazz, name, all)) {
            Class<?>[] decltypes = method.getParameterTypes();
            int dist = dist(decltypes, argtypes);
            if (dist == -1)
                continue;
            if (dist < mindist || mindist == -1) {
                mindist = dist;
                min = method;
            }
        }
        return min;
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>[] argtypes) {
        return getMethod(clazz, name, argtypes, false);
    }

    public static Constructor<?> getConstructor(Class<?> clazz, Class<?>[] argtypes, boolean all) {
        int mindist = -1;
        Constructor<?> min = null;
        for (Constructor<?> ctor : constructors(clazz, all)) {
            Class<?>[] decltypes = ctor.getParameterTypes();
            int dist = dist(decltypes, argtypes);
            if (dist == -1)
                continue;
            if (dist < mindist || mindist == -1) {
                mindist = dist;
                min = ctor;
            }
        }
        return min;
    }

    public static Constructor<?> getConstructor(Class<?> clazz, Class<?>[] argtypes) {
        return getConstructor(clazz, argtypes, false);
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> clazz, Object... args)
            throws ReflectiveOperationException {
        Class<?>[] types = Types.getTypes(args);
        Constructor<?> ctor = getConstructor(clazz, types, true);
        if (ctor == null)
            throw new NoSuchMethodException(clazz.getName() + "(" //$NON-NLS-1$
                    + Types.joinNames(types) + ")"); //$NON-NLS-1$
        boolean prevState = ctor.isAccessible();
        if (!prevState)
            ctor.setAccessible(true);
        try {
            return (T) ctor.newInstance(args);
        } finally {
            if (!prevState)
                ctor.setAccessible(false);
        }
    }

    public static <T> T newMemberInstance(Class<T> clazz, Object _this, Object... args)
            throws ReflectiveOperationException {
        Object[] concat = Arrays2.concatv(_this, args);
        return newInstance(clazz, concat);
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className, Object... args)
            throws ReflectiveOperationException {
        Class<T> clazz = (Class<T>) Class.forName(className);
        return newInstance(clazz, args);
    }

    @SuppressWarnings("unchecked")
    public static <T> T invoke(Class<?> clazz, Object obj, String methodName, Object... args)
            throws ReflectiveOperationException {
        Class<?>[] types = Types.getTypes(args);
        Method method = getMethod(clazz, methodName, types);
        if (method == null)
            throw new NoSuchMethodException(clazz.getName() + "." + methodName //$NON-NLS-1$
                    + "(" + Types.joinNames(types) + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        return (T) Control.invoke(method, obj, args);
    }

    public static <T> T invoke(Class<?> clazz, String methodName, Object... args)
            throws ReflectiveOperationException {
        return invoke(clazz, null, methodName, args);
    }

    public static <T> T invoke(Object obj, String methodName, Object... args)
            throws ReflectiveOperationException {
        return invoke(obj.getClass(), obj, methodName, args);
    }

}
