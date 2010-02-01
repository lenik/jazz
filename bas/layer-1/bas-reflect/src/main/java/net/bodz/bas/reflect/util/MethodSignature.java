package net.bodz.bas.reflect.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.collection.iterator.RepeatIterable;
import net.bodz.bas.lang.Nullables;
import net.bodz.bas.reflect.members.FindMemberOptions;
import net.bodz.bas.type.util.TypeDistance;
import net.bodz.bas.type.util.TypeName;

public class MethodSignature {

    private final String name;
    private final Class<?>[] parameterTypes;
    private final Class<?> returnType;
    private Boolean hasNullParameterType;

    // public static final String CONSTRUCTOR = "~ctor";
    // public static final String DESTRUCTOR = "~dtor";

    /**
     * @param name
     *            If the constructor is referred, the method name is the same to the class name.
     */
    public MethodSignature(String name, Class<?>[] parameterTypes, Class<?> returnType) {
        if (name == null)
            throw new NullPointerException("name");
        if (parameterTypes == null)
            throw new NullPointerException("parameterTypes");
        this.name = name;
        this.parameterTypes = parameterTypes;
        this.returnType = returnType;
    }

    public MethodSignature(String name, Class<?>... parameterTypes) {
        this(name, parameterTypes, null);
    }

    public MethodSignature(Method method) {
        this(method.getName(), method.getParameterTypes(), method.getReturnType());
    }

    /**
     * The method name is set to the full qualified name of the declaring class of the constructor.
     */
    public MethodSignature(Constructor<?> ctor) {
        this(ctor.getName(), ctor.getParameterTypes());
    }

    public String getMethodName() {
        return name;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public boolean hasNullParameterType() {
        if (hasNullParameterType == null) {
            boolean hasNull = false;
            for (Class<?> t : parameterTypes)
                if (t == null) {
                    hasNull = true;
                    break;
                }
            hasNullParameterType = hasNull;
        }
        return hasNullParameterType();
    }

    private transient Integer hash;

    @Override
    public int hashCode() {
        if (this.hash == null) {
            int hash = 0xbae56896;
            hash += name.hashCode();
            hash += Arrays.hashCode(parameterTypes);
            if (returnType != null)
                hash += returnType.hashCode();
            this.hash = hash;
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("(" + TypeName.join(parameterTypes) + ")");
        if (returnType != null) {
            buf.append(" -> ");
            buf.append(returnType.getSimpleName());
        }
        return buf.toString();
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
        if (!Nullables.equals(name, o.name))
            return false;
        return Arrays.equals(parameterTypes, o.parameterTypes);
    }

    public boolean matches(Method method) {
        if (!Nullables.equals(name, method.getName()))
            return false;
        MethodSignature o = new MethodSignature(method);
        return equals(o);
    }

    public boolean matches(Constructor<?> ctor) {
        Class<?> declaringClass = ctor.getDeclaringClass();
        if (!name.equals(declaringClass.getName()))
            throw new IllegalArgumentException("Not a constructor method for " + declaringClass);
        MethodSignature o = new MethodSignature(ctor);
        return equals(o);
    }

    public Method getMatchedMethod(Class<?> clazz) {
        try {
            return clazz.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public Method getMatchedDeclaredMethod(Class<?> clazz) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public Constructor<?> getMatchedConstructor(Class<?> clazz) {
        try {
            return clazz.getConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public Constructor<?> getMatchedDeclaredConstructor(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    public Iterable<Method> findMethods(Class<?> javaClass, FindMemberOptions options) {
        if (javaClass == null)
            throw new NullPointerException("javaClass");
        if (options.isGetDeclaredMembers())
            return findDeclaredMethods(javaClass, options);
        if (this.hasNullParameterType()) {
            Method[] publicMethods = javaClass.getMethods();
            List<Method> list = new ArrayList<Method>(publicMethods.length);
            for (Method m : javaClass.getMethods()) {
                if (!name.equals(m.getName()))
                    continue;
                int dist = TypeDistance.dist(parameterTypes, m.getParameterTypes());
                if (dist == -1 || dist > options.getMaxDistance())
                    continue;
                list.add(m);
            }
            // if (options.isSortResults()) ;
            return list;
        } else {
            Method strictlyMatchedMethod = this.getMatchedMethod(javaClass);
            if (strictlyMatchedMethod == null)
                return Collections.emptyList();
            else
                return new RepeatIterable<Method>(strictlyMatchedMethod, 1);
        }
    }

    public Iterable<Method> findDeclaredMethods(Class<?> javaClass, FindMemberOptions options) {
        if (javaClass == null)
            throw new NullPointerException("javaClass");
        if (this.hasNullParameterType()) {
            
        }
    }

}
