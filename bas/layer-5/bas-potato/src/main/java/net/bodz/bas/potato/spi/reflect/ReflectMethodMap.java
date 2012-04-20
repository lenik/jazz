package net.bodz.bas.potato.spi.reflect;

import java.lang.reflect.Method;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.potato.traits.AbstractMethodMap;

public class ReflectMethodMap
        extends AbstractMethodMap {

    private static final long serialVersionUID = 1L;

    public ReflectMethodMap(Class<?> clazz) {
        this(clazz.getMethods());
    }

    public ReflectMethodMap(Method... methods) {
        for (Method method : methods) {
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            MethodSignature signature = new MethodSignature(name, parameterTypes);
            ReflectMethod reflectMethod = new ReflectMethod(method);
            put(signature, reflectMethod);
        }
    }

}
