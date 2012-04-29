package net.bodz.bas.disp.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class MethodLazyInjector {

    protected abstract Object require(Class<?> declType);

    public Object invoke(Object obj, Method method)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (method == null)
            throw new NullPointerException("method");

        Class<?>[] declTypes = method.getParameterTypes();
        Object[] parameters = new Class<?>[declTypes.length];

        for (int i = 0; i < declTypes.length; i++) {
            Class<?> declType = declTypes[i];
            Object parameter = require(declType);
            parameters[i] = parameter;
        }

        return method.invoke(obj, parameters);
    }

}
