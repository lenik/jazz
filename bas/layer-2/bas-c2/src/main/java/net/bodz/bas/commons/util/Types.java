package net.bodz.bas.commons.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.annotations.null_class;
import net.bodz.bas.collection.comparator.TypeComparator;
import net.bodz.bas.exceptions.CreateException;
import net.bodz.bas.exceptions.OutOfDomainException;
import net.bodz.bas.exceptions.UnexpectedException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;

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

    public static Class<?>[] getTypes(Object... objects) {
        if (objects == null || objects.length == 0)
            return new Class<?>[0];

        Class<?>[] classes = new Class[objects.length];
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null)
                classes[i] = Object.class;
            else
                classes[i] = objects[i].getClass();
        }
        return classes;
    }

    public static String joinNames(String delim, boolean simpleNames, Class<?>... types) {
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
        return igcd.trueSet.toArray(new Class<?>[0]);
    }

    public static <T> T newInstance(Class<T> clazz, Class<?>[] argTypes, Object... args) {
        try {
            Constructor<T> ctor = Jdk7Reflect.getConstructor(clazz, argTypes);
            return Jdk7Reflect.newInstance(ctor, args);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T newInstance(Class<T> clazz, Object... args) {
        Class<?>[] argTypes = Types.getTypes(args);
        return newInstance(clazz, argTypes, args);
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return Jdk7Reflect.newInstance(clazz);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getClassInstance(Class<T> clazz, Object... args)
            throws CreateException {
        if (clazz == null)
            return null;
        if (clazz.isInterface())
            throw new OutOfDomainException("clazz", clazz, "interface");
        if (null_class.class.isAssignableFrom(clazz))
            return null;
        Class<?>[] argTypes = Types.getTypes(args);
        try {
            Method method = Jdk7Reflect.getMethod(clazz, "getInstance", argTypes);
            return (T) Jdk7Reflect.invoke(method, null, args);
        } catch (ReflectiveOperationException e) {
            throw new CreateException(e);
        }
    }

}
