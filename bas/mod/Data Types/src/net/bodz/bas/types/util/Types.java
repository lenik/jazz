package net.bodz.bas.types.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.null_class;
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

    private static Map<Class<?>, Class<?>> p2w;
    private static Map<Class<?>, Class<?>> w2p;
    static {
        p2w = new HashMap<Class<?>, Class<?>>();
        p2w.put(void.class, Void.class);
        p2w.put(byte.class, Byte.class);
        p2w.put(short.class, Short.class);
        p2w.put(int.class, Integer.class);
        p2w.put(long.class, Long.class);
        p2w.put(float.class, Float.class);
        p2w.put(double.class, Double.class);
        p2w.put(char.class, Character.class);
        p2w.put(boolean.class, Boolean.class);
        w2p = new HashMap<Class<?>, Class<?>>();
        w2p.put(Void.class, void.class);
        w2p.put(Byte.class, byte.class);
        w2p.put(Short.class, short.class);
        w2p.put(Integer.class, int.class);
        w2p.put(Long.class, long.class);
        w2p.put(Float.class, float.class);
        w2p.put(Double.class, double.class);
        w2p.put(Character.class, char.class);
        w2p.put(Boolean.class, boolean.class);
    }

    public static Class<?> box(Class<?> type) {
        return type.isPrimitive() ? p2w.get(type) : type;
    }

    public static Class<?> unbox(Class<?> type) {
        Class<?> primitive = w2p.get(type);
        return primitive == null ? type : primitive;
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

    public static Class<?>[] getTypes(Object... objects) {
        if (objects == null || objects.length == 0)
            return Empty.Classes;

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
        return b == null ? "" : b.toString(); //$NON-NLS-1$
    }

    public static String joinNames(String delim, Class<?>... types) {
        return joinNames(delim, false, types);
    }

    public static String joinNames(Class<?>... types) {
        return joinNames(", ", types); //$NON-NLS-1$
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
            assert !a.isAssignableFrom(b) : "illegal usage"; //$NON-NLS-1$
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

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new ReflectException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new ReflectException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getClassInstance(Class<T> clazz, Object... args)
            throws CreateException {
        if (clazz == null)
            return null;
        if (clazz.isInterface())
            throw new OutOfDomainException("clazz", clazz, "interface"); //$NON-NLS-1$ //$NON-NLS-2$
        if (null_class.class.isAssignableFrom(clazz))
            return null;
        Class<?>[] argTypes = Types.getTypes(args);
        try {
            Method method = clazz.getMethod("getInstance", argTypes); //$NON-NLS-1$
            return (T) Control.invoke(method, null, args);
        } catch (NoSuchMethodException e) {
            return newInstance(clazz, argTypes, args);
        } catch (IllegalAccessException e) {
            throw new CreateException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new CreateException(e.getMessage(), e);
        }
    }

}
