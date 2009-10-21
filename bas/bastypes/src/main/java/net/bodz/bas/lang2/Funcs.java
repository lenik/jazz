package net.bodz.bas.lang2;

import java.lang.reflect.Method;

import net.bodz.bas.lang.util.Reflects;

public class Funcs {

    public static <T> Func0<T> getFunc0(final Method method, final Object obj) {
        return new Func0<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public T eval() {
                return (T) Reflects.invoke(obj, method);
            }
        };
    }

    public static <T> Func0<T> getFunc0(Class<?> clazz, String methodName)
            throws NoSuchMethodException {
        assert clazz != null;
        Method method = clazz.getMethod(methodName);
        return getFunc0(method, (Object) null);
    }

    public static <T> Func0<T> getFunc0(Object obj, String methodName) throws NoSuchMethodException {
        if (obj == null)
            throw new NullPointerException("obj"); //$NON-NLS-1$
        Method method = obj.getClass().getMethod(methodName);
        return getFunc0(method, obj);
    }

}
