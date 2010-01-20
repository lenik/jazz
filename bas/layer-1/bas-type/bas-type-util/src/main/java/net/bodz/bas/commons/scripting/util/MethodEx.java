package net.bodz.bas.commons.scripting.util;

import java.lang.reflect.Method;

import net.bodz.bas.collection.preorder.TypeHierMap;
import net.bodz.bas.exceptions.OutOfDomainException;
import net.bodz.bas.jdk6compat.jdk7emul.IllegalAccessException;
import net.bodz.bas.jdk6compat.jdk7emul.InvocationTargetException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.primitive.Boxing;

/**
 * Method `extends'
 * 
 * The method must have and only have 1 parameter.
 * 
 * `type-dispatch' method
 * 
 * interface isn't supported, yet.
 */
@Deprecated
public class MethodEx {

    private TypeHierMap<Method> typeMap;

    public MethodEx(Iterable<Method> methods, Class<?> extendsExcl) {
        typeMap = new TypeHierMap<Method>();
        for (Method m : methods) {
            Class<?>[] argTypes = m.getParameterTypes();
            if (argTypes.length != 1)
                continue;
            Class<?> argType = argTypes[0];
            // assert extendsExcl.isAssignableFrom(argType);
            if (extendsExcl != null)
                if (argType.isAssignableFrom(extendsExcl))
                    continue;
            if (argType.isPrimitive())
                typeMap.put(Boxing.box(argType), m);
            else
                // the primitive type will never be used.
                typeMap.put(argType, m);
        }
    }

    /**
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see #invokeOnUnknownType(Object, Class, Object)
     */
    public Object invoke(Object obj, Object arg)
            throws IllegalAccessException, InvocationTargetException, IllegalArgumentException {
        Class<?> argType = Object.class;
        if (arg != null)
            argType = arg.getClass();
        Method method = typeMap.floor(argType);
        if (method == null)
            return invokeOnUnknownType(obj, argType, arg);
        method.setAccessible(true); // scoping..?
        return Jdk7Reflect.invoke(method, obj, arg);
    }

    protected Object invokeOnUnknownType(Object obj, Class<?> argType, Object arg) {
        throw new OutOfDomainException("argType", argType);
    }

    public boolean isEmpty() {
        return typeMap.isEmpty();
    }

    public boolean hasMethodFor(Class<?> childInclKey) {
        return typeMap.floorKey(childInclKey) != null;
    }

    public Class<?> getDeclaredType(Class<?> childInclKey) {
        return typeMap.floorKey(childInclKey);
    }

    public Method getMethodFor(Class<?> childInclKey) {
        return typeMap.floor(childInclKey);
    }

    public Method removeDeclaredType(Object key) {
        return typeMap.remove(key);
    }

}
