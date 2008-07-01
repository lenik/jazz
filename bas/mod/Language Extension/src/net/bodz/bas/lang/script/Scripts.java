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
    public static <T> ScriptClass<T> convertClass(Class<?> clazz)
            throws ScriptException {
        _ScriptClass<T> sclass = (_ScriptClass<T>) classes.get(clazz);
        if (sclass == null) {
            sclass = new _ScriptClass<T>((Class<T>) clazz);
            classes.put(clazz, sclass);
        }
        return sclass;
    }

    public static <T> ScriptField<T> convertField(final Field field)
            throws ScriptException {
        Class<?> clazz = field.getDeclaringClass();
        return convertField(clazz, field.getName());
    }

    @SuppressWarnings("unchecked")
    public static <T> ScriptField<T> convertField(final Class<?> clazz,
            final String propertyName) throws ScriptException {
        ScriptClass<?> sclass = convertClass(clazz);
        return (ScriptField<T>) sclass.getField(propertyName);
    }

    public static <T> ScriptMethod<T> convertMethod(final Method method)
            throws ScriptException {
        Class<?> clazz = method.getDeclaringClass();
        return convertMethod(clazz, method.getName());
    }

    @SuppressWarnings("unchecked")
    public static <T> ScriptMethod<T> convertMethod(final Class<?> clazz,
            final String methodName) throws ScriptException {
        ScriptClass<?> sclass = convertClass(clazz);
        return (ScriptMethod<T>) sclass.getMethod(methodName);
    }

}
