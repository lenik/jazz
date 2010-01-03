package net.bodz.bas.commons.scripting.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.bodz.bas.exceptions.IdentifiedException;

public class SimpleJavaCall {

    /**
     * @return null for null at correspond index.
     */
    public static Class<?>[] getClasses(Object[] objects) {
        Class<?>[] protos = new Class[objects.length];
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null)
                protos[i] = objects[i].getClass();
        }
        return protos;
    }

    /**
     * Error if arguments contains null.
     */
    @Deprecated
    public static Object call(Object object, String methodName, Object[] arguments) {
        try {
            Class<?>[] protos = getClasses(arguments);
            Class<?> clazz = object.getClass();
            Method method = clazz.getMethod(methodName, protos);
            Object ret = method.invoke(object, arguments);
            return ret;
        } catch (NoSuchMethodException e) {
            throw new IdentifiedException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new IdentifiedException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
    }

    /**
     * Error if arguments contains null.
     */
    @Deprecated
    public static Object call(Class<?> clazz, String methodName, Object[] arguments) {
        try {
            Object ret;
            Class<?>[] protos = getClasses(arguments);
            if (methodName.equals("<cinit>")) { //$NON-NLS-1$
                Constructor<?> cstr = clazz.getConstructor(protos);
                ret = cstr.newInstance(arguments);
            } else {
                Method method = clazz.getMethod(methodName, protos);
                int modi = method.getModifiers();
                if (Modifier.isStatic(modi)) {
                    ret = method.invoke(null, arguments);
                } else {
                    Object obj = clazz.newInstance();
                    ret = method.invoke(obj, arguments);
                }
            }
            return ret;
        } catch (InstantiationException e) {
            throw new IdentifiedException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new IdentifiedException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new IdentifiedException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
    }

    @Deprecated
    public static Object call(Object object, String methodName, Object[] arguments, Object defaultValue) {
        try {
            return call(object, methodName, arguments);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Deprecated
    public static Object call(Class<?> cls, String methodName, Object[] arguments, Object defaultValue) {
        try {
            return call(cls, methodName, arguments);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Deprecated
    public static Object call(String className, String methodName, Object[] arguments) {
        try {
            Object newInstance = Class.forName(className).newInstance();
            return call(newInstance, methodName, arguments);
        } catch (ClassNotFoundException e) {
            throw new IdentifiedException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new IdentifiedException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
    }

    @Deprecated
    public static Object call(String className, String methodName, Object[] arguments, Object defaultValue) {
        try {
            return call(className, methodName, arguments);
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
