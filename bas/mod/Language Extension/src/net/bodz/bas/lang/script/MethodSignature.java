package net.bodz.bas.lang.script;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;

import net.bodz.bas.lang.util.Members.AllConstructors;
import net.bodz.bas.lang.util.Members.AllMethods;
import net.bodz.bas.lang.util.Members.PublicConstructors;
import net.bodz.bas.lang.util.Members.PublicMethods;
import net.bodz.bas.types.util.CompatMethods;
import net.bodz.bas.types.util.Objects;

public class MethodSignature {

    private final String     name;
    private final Class<?>[] types;

    public MethodSignature(String name, Class<?>... types) {
        assert types != null;
        this.name = name;
        this.types = types;
    }

    public MethodSignature(Class<?>... types) {
        this(null, types);
    }

    public MethodSignature(Method method) {
        this(method.getName(), method.getParameterTypes());
    }

    public MethodSignature(Constructor<?> ctor) {
        this(ctor.getParameterTypes());
    }

    public String getName() {
        return name;
    }

    public Class<?>[] getParameterTypes() {
        return types;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        if (name != null)
            hash += name.hashCode();
        if (types != null)
            hash += types.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MethodSignature))
            return false;
        return equals((MethodSignature) o);
    }

    public boolean equals(MethodSignature msig) {
        if (msig == this)
            return true;
        if (!Objects.equals(name, msig.name))
            return false;
        return Arrays.equals(types, msig.types);
    }

    public boolean matches(Method method) {
        if (!Objects.equals(name, method.getName()))
            return false;
        MethodSignature msig = new MethodSignature(method);
        return equals(msig);
    }

    public boolean matches(Constructor<?> ctor) {
        MethodSignature msig = new MethodSignature(ctor);
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

    public Iterable<Constructor<?>> getConstructors(final Class<?> clazz) {
        return new Iterable<Constructor<?>>() {
            @Override
            public Iterator<Constructor<?>> iterator() {
                return new PublicConstructors(clazz) {
                    @Override
                    protected boolean accept(Constructor<?> ctor) {
                        return matches(ctor);
                    }
                };
            }
        };
    }

    public Iterable<Constructor<?>> getAllConstructors(final Class<?> clazz) {
        return new Iterable<Constructor<?>>() {
            @Override
            public Iterator<Constructor<?>> iterator() {
                return new AllConstructors(clazz) {
                    @Override
                    protected boolean accept(Constructor<?> ctor) {
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
