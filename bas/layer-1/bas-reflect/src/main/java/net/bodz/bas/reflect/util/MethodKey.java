package net.bodz.bas.reflect.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;

import net.bodz.bas.lang.Nullables;
import net.bodz.bas.reflect.util.Members.AllConstructors;
import net.bodz.bas.reflect.util.Members.AllMethods;
import net.bodz.bas.reflect.util.Members.PublicConstructors;
import net.bodz.bas.reflect.util.Members.PublicMethods;
import net.bodz.bas.type.util.TypeName;

public class MethodKey {

    private final String name;
    private final Class<?>[] types;

    private transient Integer hash;

    public MethodKey(String name, Class<?>... types) {
        assert types != null;
        this.name = name;
        this.types = types;
    }

    public MethodKey(Class<?>... types) {
        this(null, types);
    }

    public MethodKey(Method method) {
        this(method.getName(), method.getParameterTypes());
    }

    public MethodKey(Constructor<?> ctor) {
        this(ctor.getParameterTypes());
    }

    public String getName() {
        return name;
    }

    public Class<?>[] getParameterTypes() {
        return types;
    }

    @Override
    public String toString() {
        return "signature " + name + "(" + TypeName.join(types) + ")";
    }

    @Override
    public int hashCode() {
        if (this.hash == null) {
            int hash = 0;
            if (name != null)
                hash += name.hashCode();
            if (types != null)
                for (Class<?> type : types)
                    if (type != null)
                        hash += type.hashCode();
            this.hash = hash;
        }
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MethodKey))
            return false;
        return equals((MethodKey) o);
    }

    public boolean equals(MethodKey msig) {
        if (msig == this)
            return true;
        if (!Nullables.equals(name, msig.name))
            return false;
        return Arrays.equals(types, msig.types);
    }

    public boolean matches(Method method) {
        if (!Nullables.equals(name, method.getName()))
            return false;
        MethodKey msig = new MethodKey(method);
        return equals(msig);
    }

    public boolean matches(Constructor<?> ctor) {
        MethodKey msig = new MethodKey(ctor);
        return equals(msig);
    }

    public Method getMethod(Class<?> clazz) {
        try {
            return clazz.getMethod(name, types);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public Constructor<?> getConstructor(Class<?> clazz) {
        try {
            return clazz.getConstructor(types);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public Iterable<Method> getMethods(final Class<?> clazz) {
        return new Iterable<Method>() {
            @Override
            public Iterator<Method> iterator() {
                return new PublicMethods(clazz) {
                    @Override
                    protected boolean accept(Method m) {
                        return matches(m);
                    }
                };
            }
        };
    }

    public Iterable<Method> getAllMethods(final Class<?> clazz) {
        return new Iterable<Method>() {
            @Override
            public Iterator<Method> iterator() {
                return new AllMethods(clazz) {
                    @Override
                    protected boolean accept(Method m) {
                        return matches(m);
                    }
                };
            }
        };
    }

    public <T> Iterable<Constructor<T>> getConstructors(final Class<T> clazz) {
        return new Iterable<Constructor<T>>() {
            @Override
            public Iterator<Constructor<T>> iterator() {
                return new PublicConstructors<T>(clazz) {
                    @Override
                    protected boolean accept(Constructor<T> ctor) {
                        return matches(ctor);
                    }
                };
            }
        };
    }

    public <T> Iterable<Constructor<T>> getAllConstructors(final Class<T> clazz) {
        return new Iterable<Constructor<T>>() {
            @Override
            public Iterator<Constructor<T>> iterator() {
                return new AllConstructors<T>(clazz) {
                    @Override
                    protected boolean accept(Constructor<T> ctor) {
                        return matches(ctor);
                    }
                };
            }
        };
    }

    public Method getCompatMethod(Class<?> clazz, boolean all) {
        return CompatMethods.getMethod(clazz, name, types, all);
    }

    public Method getCompatMethod(Class<?> clazz) {
        return CompatMethods.getMethod(clazz, name, types);
    }

    public Constructor<?> getCompatConstructor(Class<?> clazz, boolean all) {
        return CompatMethods.getConstructor(clazz, types, all);
    }

    public Constructor<?> getCompatConstructor(Class<?> clazz) {
        return CompatMethods.getConstructor(clazz, types);
    }

}
