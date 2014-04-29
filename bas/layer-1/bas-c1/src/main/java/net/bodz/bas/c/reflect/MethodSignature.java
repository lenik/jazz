package net.bodz.bas.c.reflect;

import java.beans.MethodDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.type.TypeSpace;

public class MethodSignature {

    static TypeSpace typeSpace = TypeSpace.getDefault();

    protected final String name;
    protected final Class<?>[] parameterTypes;
    protected final Class<?> returnType;

    protected final boolean wild;

    // public static final String CONSTRUCTOR = "~ctor";
    // public static final String DESTRUCTOR = "~dtor";

    /**
     * @param returnType
     *            Return type of the method, <code>null</code> if we don't care about it. Otherwise,
     *            it can be void type for constructor and void methods.
     * @param name
     *            If it is a constructor, name could be 1. the simple class name, 2. null.
     * @param parameterTypes
     *            Non-<code>null</code> type array of the parameters.
     */
    public MethodSignature(Class<?> returnType, String name, Class<?>... parameterTypes) {
        if (parameterTypes == null)
            throw new NullPointerException("parameterTypes");
        this.name = name;
        this.parameterTypes = parameterTypes;
        this.returnType = returnType;

        boolean wild = false;
        for (Class<?> type : parameterTypes)
            if (type == null) {
                wild = true;
                break;
            }
        this.wild = wild;
    }

    public MethodSignature(String name, Class<?>... parameterTypes) {
        this(null, name, parameterTypes);
    }

    public MethodSignature(Method method) {
        this(method.getReturnType(), method.getName(), method.getParameterTypes());
    }

    /**
     * The method name is set to the full qualified name of the declaring class of the constructor.
     */
    public MethodSignature(Constructor<?> ctor) {
        this(ctor.getName(), ctor.getParameterTypes());
    }

    public String getName() {
        return name;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public boolean isWild() {
        return wild;
    }

    private transient Integer hash;

    @Override
    public int hashCode() {
        if (hash == null) {
            synchronized (this) {
                if (hash == null) {
                    hash = 0xbae56896;
                    hash += name.hashCode();
                    hash += Arrays.hashCode(parameterTypes);
                    if (returnType != null)
                        hash += returnType.hashCode();
                }
            }
        }
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MethodSignature))
            return false;
        return equals((MethodSignature) o);
    }

    public boolean equals(MethodSignature o) {
        if (o == this)
            return true;
        if (o == null)
            return false;
        if (!Nullables.equals(name, o.name))
            return false;
        if (!Nullables.equals(returnType, o.returnType))
            return false;
        if (!Arrays.equals(parameterTypes, o.parameterTypes))
            return false;
        return true;
    }

    public boolean matches(MethodDescriptor methodDescriptor) {
        Method method = methodDescriptor.getMethod();
        return matches(method);
    }

    public boolean matches(Method method) {
        if (!Nullables.equals(name, method.getName()))
            return false;

        MethodSignature other = new MethodSignature(method);
        return equals(other);
    }

    public boolean matches(Constructor<?> ctor) {
        Class<?> declaringClass = ctor.getDeclaringClass();
        if (name != null && !name.equals(declaringClass.getSimpleName()))
            throw new IllegalArgumentException("Not a constructor method for " + declaringClass);
        MethodSignature o = new MethodSignature(ctor);
        return equals(o);
    }

    public Method findMethod(Class<?> clazz) {
        try {
            return clazz.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public Method findDeclaredMethod(Class<?> clazz) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public Constructor<?> findConstructor(Class<?> clazz) {
        try {
            return clazz.getConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public Constructor<?> findDeclaredConstructor(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public Method findNearestMethod(Iterable<Method> methods) {
        int mindist = -1;
        Method finest = null;
        for (Method m : methods) {
            Class<?>[] o = m.getParameterTypes();
            int dist = typeSpace.dist(parameterTypes, o);
            if (dist != -1) {
                if (mindist == -1 || dist < mindist) {
                    mindist = dist;
                    finest = m;
                }
            }
        }
        return finest;
    }

    public Constructor<?> findNearestConstructor(Iterable<Constructor<?>> constructors) {
        int mindist = -1;
        Constructor<?> finest = null;
        for (Constructor<?> ctor : constructors) {
            Class<?>[] o = ctor.getParameterTypes();
            int dist = typeSpace.dist(parameterTypes, o);
            if (dist != -1) {
                if (mindist == -1 || dist < mindist) {
                    mindist = dist;
                    finest = ctor;
                }
            }
        }
        return finest;
    }

    protected String formatTypeName(Class<?> type) {
        return type.getCanonicalName();
    }

    protected String format(String wildChar) {
        StringBuilder buf = new StringBuilder(parameterTypes.length * 40);
        if (name != null)
            buf.append(name);
        buf.append('(');
        for (int i = 0; i < parameterTypes.length; i++) {
            if (i != 0)
                buf.append(", ");
            Class<?> parameterType = parameterTypes[i];
            if (parameterType == null)
                buf.append(wildChar);
            else
                buf.append(formatTypeName(parameterType));
        }
        buf.append(')');
        if (returnType != null) {
            buf.append(" -> ");
            buf.append(formatTypeName(returnType));
        }
        return buf.toString();
    }

    @Override
    public String toString() {
        return format("?");
    }

}
