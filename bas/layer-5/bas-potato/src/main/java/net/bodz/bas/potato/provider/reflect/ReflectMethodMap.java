package net.bodz.bas.potato.provider.reflect;

import java.lang.reflect.Method;

import net.bodz.bas.potato.traits.AbstractMethodMap;
import net.bodz.bas.potato.traits.MethodKey;

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
            MethodKey methodKey = new MethodKey(name, parameterTypes);
            ReflectMethod reflectMethod = new ReflectMethod(method);
            put(methodKey, reflectMethod);
        }
    }

}
