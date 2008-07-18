package net.bodz.bas.lang.script;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Scripts {

    private static Map<Class<?>, _ScriptClass<?>> classes;
    static {
        classes = new HashMap<Class<?>, _ScriptClass<?>>();
    }

    @SuppressWarnings("unchecked")
    public static <T> ScriptClass<T> convertClass(Class<?> clazz,
            boolean forceAccess) throws ScriptException {
        _ScriptClass<T> sclass = (_ScriptClass<T>) classes.get(clazz);
        if (sclass == null) {
            sclass = new _ScriptClass<T>((Class<T>) clazz, forceAccess);
            classes.put(clazz, sclass);
        }
        return sclass;
    }

    public static <T> ScriptClass<T> convertClass(Class<?> clazz)
            throws ScriptException {
        return convertClass(clazz, false);
    }

    @SuppressWarnings("unchecked")
    public static <T> ScriptField<T> convertField(Class<?> clazz,
            final String memberName, boolean forceAccess)
            throws ScriptException {
        ScriptClass<?> sclass = convertClass(clazz, forceAccess);
        return (ScriptField<T>) sclass.getField(memberName);
    }

    public static <T> ScriptField<T> convertField(Class<?> clazz,
            final String memberName) throws ScriptException {
        return convertField(clazz, memberName, false);
    }

    public static <T> ScriptField<T> convertField(Field field,
            boolean forceAccess) throws ScriptException {
        Class<?> clazz = field.getDeclaringClass();
        return convertField(clazz, field.getName(), forceAccess);
    }

    public static <T> ScriptField<T> convertField(Field field)
            throws ScriptException {
        return convertField(field, false);
    }

    @SuppressWarnings("unchecked")
    public static <T> ScriptMethod<T> convertMethod(Class<?> clazz,
            String memberName, boolean forceAccess) throws ScriptException {
        ScriptClass<?> sclass = convertClass(clazz, forceAccess);
        return (ScriptMethod<T>) sclass.getMethod(memberName);
    }

    public static <T> ScriptMethod<T> convertMethod(Class<?> clazz,
            final String memberName) throws ScriptException {
        return convertMethod(clazz, memberName, false);
    }

    public static <T> ScriptMethod<T> convertMethod(Method method,
            boolean forceAccess) throws ScriptException {
        Class<?> clazz = method.getDeclaringClass();
        return convertMethod(clazz, method.getName(), forceAccess);
    }

    public static <T> ScriptMethod<T> convertMethod(Method method)
            throws ScriptException {
        return convertMethod(method, false);
    }

}
