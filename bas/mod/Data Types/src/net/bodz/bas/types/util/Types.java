package net.bodz.bas.types.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.lang.IVoid;
import net.bodz.bas.lang.Predicate;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.lang.err.ReflectException;
import net.bodz.bas.lang.err.UnexpectedException;

public class Types {

    public static void load(Class<?> clazz) {
        String name = clazz.getName();
        ClassLoader loader = clazz.getClassLoader();
        try {
            Class.forName(name, true, loader);
        } catch (ClassNotFoundException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

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

    public static String joinNames(String delim, boolean simpleNames,
            Class<?>... types) {
        StringBuffer b = null;
        for (Class<?> t : types) {
            if (b == null)
                b = new StringBuffer(types.length * 20);
            else
                b.append(delim);
            String n;
            if (simpleNames)
                // || t.getCanonicalName().startsWith("java.lang."))
                n = t.getSimpleName();
            else
                n = t.getName();
            b.append(n);
        }
        return b == null ? "" : b.toString();
    }

    public static String joinNames(String delim, Class<?>... types) {
        return joinNames(delim, false, types);
    }

    public static String joinNames(Class<?>... types) {
        return joinNames(", ", types);
    }

    public static Class<?> gcd(Class<?> a, Class<?> b) {
        while (a != null && !a.isAssignableFrom(b))
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

    static class Igcd {

        private Set<Class<?>> trueSet;
        private Set<Class<?>> falseSet;

        public Igcd(boolean sort) {
            if (sort) {
                trueSet = new TreeSet<Class<?>>(Comparators.TYPE);
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
            return trueSet.toArray(Empty.Classes);
        }

    }

    public static Class<?>[] igcd(Class<?> a, Class<?> b, boolean sort) {
        Class<?> gcd = gcd(a, b);
        if (gcd != null)
            return new Class<?>[] { gcd };
        Igcd igcd = new Igcd(sort);
        return igcd.solve(a, b);
    }

    public static Class<?>[] igcd(Class<?> a, Class<?> b) {
        return igcd(a, b, false);
    }

    /** under dev */
    @Deprecated
    public static Class<?>[] igcd(Collection<Class<?>> classes, boolean sort) {
        Igcd igcd = new Igcd(sort);
        Class<?> prev = null;
        for (Class<?> clazz : classes) {
            if (prev != null)
                igcd._solve(prev, clazz);
            prev = clazz;
        }
        return igcd.trueSet.toArray(Empty.Classes);
    }

    /** Get matching methods */
    public static Iterable<Method> findMethods(final Class<?> clazz,
            final String name, final Predicate<Method> pred) {
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

    public static Iterable<Method> findMethodsAllTree(final Class<?> clazz,
            final String name, final Predicate<Method> pred) {
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

    private static Iterable<Constructor<?>> findConstructors(
            final Constructor<?>[] ctors, final Predicate<Constructor<?>> pred) {
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
                        boolean matched = pred == null || pred.eval(ctor);
                        return matched ? ctor : fetch();
                    }
                };
            }
        };
    }

    public static Iterable<Method> getMethods(Class<?> clazz, String name) {
        return findMethods(clazz, name, null);
    }

    public static Iterable<Method> getMethods(Class<?> clazz) {
        return findMethods(clazz, null, null);
    }

    public static Iterable<Method> getMethodsAllTree(Class<?> clazz, String name) {
        return findMethodsAllTree(clazz, name, null);
    }

    public static Iterable<Method> getMethodsAllTree(Class<?> clazz) {
        return findMethodsAllTree(clazz, null, null);
    }

    public static Iterable<Constructor<?>> findConstructors(Class<?> clazz,
            Predicate<Constructor<?>> pred) {
        return findConstructors(clazz.getConstructors(), pred);
    }

    public static Iterable<Constructor<?>> getConstructors(Class<?> clazz) {
        return findConstructors(clazz.getConstructors(), null);
    }

    public static Iterable<Constructor<?>> findDeclaredConstructors(
            final Class<?> clazz, Predicate<Constructor<?>> pred) {
        return findConstructors(clazz.getDeclaredConstructors(), pred);
    }

    public static Iterable<Constructor<?>> getDeclaredConstructors(
            final Class<?> clazz) {
        return findConstructors(clazz.getDeclaredConstructors(), null);
    }

    public static <T> T newInstance(Class<T> clazz, Class<?>[] argtypes,
            Object... args) {
        try {
            Constructor<T> ctor = clazz.getConstructor(argtypes);
            return ctor.newInstance(args);
        } catch (InstantiationException e) {
            throw new ReflectException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new ReflectException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new ReflectException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new ReflectException(e.getMessage(), e);
        }
    }

    public static <T> T newInstance(Class<T> clazz, Object... args) {
        Class<?>[] argTypes = Types.getTypes(args);
        return newInstance(clazz, argTypes, args);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getClassInstance(Class<T> clazz, Object... args)
            throws CreateException {
        if (clazz == null)
            return null;
        if (clazz.isInterface())
            throw new OutOfDomainException("clazz", clazz, "interface");
        if (IVoid.class.isAssignableFrom(clazz))
            throw new OutOfDomainException("clazz", clazz, IVoid.class);
        Class<?>[] argTypes = Types.getTypes(args);
        try {
            Method method = clazz.getMethod("getInstance", argTypes);
            return (T) method.invoke(null, args);
        } catch (NoSuchMethodException e) {
            return newInstance(clazz, argTypes, args);
        } catch (IllegalAccessException e) {
            throw new CreateException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new CreateException(e.getMessage(), e);
        }
    }

}
