package net.bodz.bas.types.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.cli.CLIError;
import net.bodz.bas.lang.IVoid;
import net.bodz.bas.lang.Predicate;

public class Types {

    public static Class<?>[] getTypeChain(Class<?> clazz) {
        return getTypeChain(clazz, false);
    }

    public static Class<?>[] getTypeChain(Class<?> clazz, boolean rootFirst) {
        List<Class<?>> list = new ArrayList<Class<?>>();
        while (clazz != null) {
            list.add(clazz);
            clazz = clazz.getSuperclass();
        }
        if (rootFirst)
            Collections.reverse(list);
        return list.toArray(new Class<?>[0]);
    }

    static final Class<?>[] EMPTY_CLASSES = {};

    public static Class<?>[] getTypes(Object... objects) {
        if (objects == null || objects.length == 0)
            return EMPTY_CLASSES;

        Class<?>[] classes = new Class[objects.length];
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null)
                classes[i] = Object.class;
            else
                classes[i] = objects[i].getClass();
        }
        return classes;
    }

    public static String joinNames(String delim, Class<?>... types) {
        StringBuffer b = null;
        for (Class<?> t : types) {
            if (b == null)
                b = new StringBuffer(types.length * 20);
            else
                b.append(delim);
            String n = t.getName();
            if (n.startsWith("java.lang."))
                n = t.getSimpleName();
            b.append(n);
        }
        return b == null ? "" : b.toString();
    }

    public static String joinNames(Class<?>... types) {
        return joinNames(", ", types);
    }

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

    public static Class<?> gcd(Class<?> a, Class<?> b) {
        while (!a.isAssignableFrom(b))
            a = a.getSuperclass();
        return a;
    }

    public static Class<?> gcd(Collection<Class<?>> classes) {
        Class<?> base = null;
        for (Class<?> clazz : classes) {
            if (base == null)
                base = clazz;
            else
                base = gcd(base, clazz);
        }
        return base;
    }

    public static Iterable<Method> getMethods(final Class<?> clazz,
            final String name, final Predicate pred) {
        return new Iterable<Method>() {
            @Override
            public Iterator<Method> iterator() {
                return new PrefetchedIterator<Method>() {
                    private Method[] methods;
                    private int      i;

                    @Override
                    protected Object fetch() {
                        if (methods == null)
                            methods = clazz.getDeclaredMethods();
                        if (i >= methods.length)
                            return END;
                        Method method = methods[i++];
                        boolean matched = name == null
                                || name.equals(method.getName());
                        matched = matched
                                && (pred == null || pred.eval(method));
                        return matched ? method : fetch();
                    }
                };
            }
        };
    }

    public static Iterable<Method> getAllMethods(final Class<?> clazz,
            final String name, final Predicate pred) {
        return new Iterable<Method>() {
            @Override
            public Iterator<Method> iterator() {
                return new PrefetchedIterator<Method>() {
                    private Class<?> c = clazz;
                    private Method[] methods;
                    private int      i;

                    @Override
                    protected Object fetch() {
                        if (c == null)
                            return END;
                        if (methods == null) {
                            methods = c.getDeclaredMethods();
                            i = 0;
                        }
                        if (i >= methods.length) {
                            c = c.getSuperclass();
                            methods = null;
                            return fetch();
                        }
                        Method method = methods[i++];
                        boolean matched = name == null
                                || name.equals(method.getName());
                        matched = matched
                                && (pred == null || pred.eval(method));
                        return matched ? method : fetch();
                    }
                };
            }
        };
    }

    private static Iterable<Constructor<?>> getConstructors(
            final Constructor<?>[] ctors, final Predicate pred) {
        return new Iterable<Constructor<?>>() {
            @Override
            public Iterator<Constructor<?>> iterator() {
                return new PrefetchedIterator<Constructor<?>>() {
                    private int i;

                    @Override
                    protected Object fetch() {
                        if (i >= ctors.length)
                            return END;
                        Constructor<?> ctor = ctors[i++];
                        boolean matched = pred == null || pred.eval(ctors);
                        return matched ? ctor : fetch();
                    }
                };
            }
        };
    }

    public static Iterable<Method> getMethods(Class<?> clazz, String name) {
        return getMethods(clazz, name, null);
    }

    public static Iterable<Method> getMethods(Class<?> clazz) {
        return getMethods(clazz, null, null);
    }

    public static Iterable<Method> getAllMethods(Class<?> clazz, String name) {
        return getAllMethods(clazz, name, null);
    }

    public static Iterable<Method> getAllMethods(Class<?> clazz) {
        return getAllMethods(clazz, null, null);
    }

    public static Iterable<Constructor<?>> getConstructors(Class<?> clazz,
            Predicate pred) {
        return getConstructors(clazz.getConstructors(), pred);
    }

    public static Iterable<Constructor<?>> getConstructors(Class<?> clazz) {
        return getConstructors(clazz.getConstructors(), null);
    }

    public static Iterable<Constructor<?>> getAllConstructors(
            final Class<?> clazz, Predicate pred) {
        return getConstructors(clazz.getDeclaredConstructors(), pred);
    }

    public static Iterable<Constructor<?>> getAllConstructors(
            final Class<?> clazz) {
        return getConstructors(clazz.getDeclaredConstructors(), null);
    }

    public static Method getCompatMethod(Class<?> clazz, String name,
            Class<?>[] argtypes, boolean all) {
        Iterable<Method> methods = all ? getAllMethods(clazz, name)
                : getMethods(clazz, name);
        int mindist = -1;
        Method min = null;
        for (Method method : methods) {
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

    public static Method getCompatMethod(Class<?> clazz, String name,
            Class<?>[] argtypes) {
        return getCompatMethod(clazz, name, argtypes, false);
    }

    public static Constructor<?> getCompatConstructor(Class<?> clazz,
            Class<?>[] argtypes, boolean all) {
        Iterable<Constructor<?>> ctors = all ? getAllConstructors(clazz)
                : getConstructors(clazz);
        int mindist = -1;
        Constructor<?> min = null;
        for (Constructor<?> ctor : ctors) {
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

    public static Constructor<?> getCompatConstructor(Class<?> clazz,
            Class<?>[] argtypes) {
        return getCompatConstructor(clazz, argtypes, false);
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> clazz, Object... args)
            throws NoSuchMethodException, IllegalArgumentException,
            InstantiationException, IllegalAccessException,
            InvocationTargetException {
        Class<?>[] types = Types.getTypes(args);
        Constructor<?> ctor = Types.getCompatConstructor(clazz, types);
        if (ctor == null)
            throw new NoSuchMethodException(clazz.getName() + "("
                    + joinNames(types) + ")");
        return (T) ctor.newInstance(args);
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className, Object... args)
            throws ClassNotFoundException, IllegalArgumentException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        Class<T> clazz = (Class<T>) Class.forName(className);
        return newInstance(clazz, args);
    }

    @SuppressWarnings("unchecked")
    public static <T> T invoke(Class<?> clazz, Object obj, String methodName,
            Object... args) throws NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        Class<?>[] types = Types.getTypes(args);
        Method method = Types.getCompatMethod(clazz, methodName, types);
        if (method == null)
            throw new NoSuchMethodException(clazz.getName() + "." + methodName
                    + "(" + joinNames(types) + ")");
        return (T) method.invoke(obj, args);
    }

    public static <T> T invoke(Class<?> clazz, String methodName,
            Object... args) throws NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        return invoke(clazz, null, methodName, args);
    }

    public static <T> T invoke(Object obj, String methodName, Object... args)
            throws NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        return invoke(obj.getClass(), obj, methodName, args);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getClassInstance(Class<T> clazz, Object... args) {
        if (clazz == null)
            return null;
        if (clazz.isInterface())
            return null;
        if (IVoid.class.isAssignableFrom(clazz))
            return null;
        Class<?>[] argtypes = Types.getTypes(args);
        try {
            Method method = clazz.getMethod("getInstance", argtypes);
            return (T) method.invoke(null, args);
        } catch (NoSuchMethodException e1) {
            try {
                return clazz.getConstructor(argtypes).newInstance(args);
            } catch (Exception e) {
                throw new CLIError(e.getMessage(), e);
            }
        } catch (Exception e) {
            throw new CLIError(e.getMessage(), e);
        }
    }

}
