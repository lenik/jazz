package net.bodz.bas.lang.script;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.bodz.bas.lang.Predicate;
import net.bodz.bas.types.util.CompatMethods;
import net.bodz.bas.types.util.Types;

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
        boolean eqnam = false;
        if (name == msig.name)
            eqnam = true;
        else if (name == null || msig.name == null)
            eqnam = false;
        else
            eqnam = name.equals(msig.name);
        if (!eqnam)
            return false;
        return Arrays.equals(types, msig.types);
    }

    public boolean matches(Method method) {
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

    private class methodp implements Predicate<Method> {
        @Override
        public boolean eval(Method method) {
            return matches(method);
        }
    }

    private class ctorp implements Predicate<Constructor<?>> {
        @Override
        public boolean eval(Constructor<?> ctor) {
            return matches(ctor);
        }
    }

    public Iterable<Method> getMethods(Class<?> clazz) {
        return Types.findMethods(clazz, name, new methodp());
    }

    public Iterable<Method> getAllMethods(Class<?> clazz) {
        return Types.findMethodsAllTree(clazz, name, new methodp());
    }

    public Iterable<Constructor<?>> getConstructors(Class<?> clazz) {
        return Types.findConstructors(clazz, new ctorp());
    }

    public Iterable<Constructor<?>> getAllConstructors(Class<?> clazz) {
        return Types.findDeclaredConstructors(clazz, new ctorp());
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
