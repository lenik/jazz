package net.bodz.bas.lang.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.a.ReflectField;
import net.bodz.bas.lang.a.ReflectMethod;
import net.bodz.bas.lang.a.ReflectProperty;
import net.bodz.bas.lang.err.ReflectException;
import net.bodz.bas.nls.LangNLS;
import net.bodz.bas.types.HashTextMap;
import net.bodz.bas.types.TextMap;

public class Reflects {

    private static void setField(Object o, Field field, Object value) {
        boolean old = field.isAccessible();
        if (!old)
            field.setAccessible(true);
        try {
            field.set(o, value);
        } catch (Exception e) {
            throw new Error(e.getMessage(), e);
        } finally {
            if (!old)
                field.setAccessible(false);
        }
    }

    private static Class<?> ifvoid(Class<?> c, Class<?> t) {
        return c == void.class ? t : c;
    }

    public static void bind(Class<?> declareClass, Object defineObject)
            throws ReflectException {
        assert defineObject != null;
        Class<?> defineClass = defineObject.getClass();
        bind(declareClass, defineClass, defineObject);
    }

    public static void bind(Class<?> declareClass, Class<?> defineClass,
            Object defineObject) throws ReflectException {
        TextMap<PropertyDescriptor> properties = null;

        for (Field declareField : declareClass.getDeclaredFields()) {
            Object def = null;
            ReflectField f = declareField.getAnnotation(ReflectField.class);
            if (f != null) {
                if (!Field.class.isAssignableFrom(declareField.getType()))
                    throw new Error(ReflectField.class
                            + LangNLS.getString("Reflects.notField")); //$NON-NLS-1$
                Class<?> _class = ifvoid(f._class(), defineClass);
                String name = f.value();
                if (name.isEmpty())
                    name = declareField.getName();
                if (f.secure()) {
                    try {
                        def = _class.getField(name);
                    } catch (SecurityException e) {
                        throw new Error(e.getMessage(), e);
                    } catch (NoSuchFieldException e) {
                    }
                } else {
                    do {
                        try {
                            Field defField = _class.getDeclaredField(name);
                            defField.setAccessible(true);
                            def = defField;
                            break;
                        } catch (SecurityException e) {
                            throw new Error(e.getMessage(), e);
                        } catch (NoSuchFieldException e) {
                        }
                        _class = _class.getSuperclass();
                    } while (_class != null);
                }
                if (def == null)
                    throw new NoSuchFieldError(name);
                setField(defineObject, declareField, def);
                continue;
            }

            ReflectMethod m = declareField.getAnnotation(ReflectMethod.class);
            if (m != null) {
                Class<?> _class = ifvoid(m._class(), defineClass);
                String name = m.value();
                if (name.isEmpty())
                    name = declareField.getName();
                Class<?>[] parameters = m.parameters();
                if (m.secure()) {
                    try {
                        def = _class.getMethod(name, parameters);
                    } catch (SecurityException e) {
                        throw new Error(e.getMessage(), e);
                    } catch (NoSuchMethodException e) {
                    }
                } else {
                    do {
                        try {
                            Method defMethod = _class.getDeclaredMethod(name,
                                    parameters);
                            defMethod.setAccessible(true);
                            def = defMethod;
                            break;
                        } catch (SecurityException e) {
                            throw new Error(e.getMessage(), e);
                        } catch (NoSuchMethodException e) {
                        }
                        _class = _class.getSuperclass();
                    } while (_class != null);
                }
                if (def == null)
                    throw new NoSuchMethodError(name);
                setField(defineObject, declareField, def);
                continue;
            }

            ReflectProperty p = declareField
                    .getAnnotation(ReflectProperty.class);
            if (p != null) {
                if (properties == null) {
                    Class<?> _class = ifvoid(p._class(), defineClass);
                    BeanInfo beanInfo;
                    try {
                        beanInfo = Introspector.getBeanInfo(_class);
                    } catch (IntrospectionException e) {
                        throw new Error(e.getMessage(), e);
                    }
                    properties = new HashTextMap<PropertyDescriptor>();
                    for (PropertyDescriptor property : beanInfo
                            .getPropertyDescriptors())
                        properties.put(property.getName(), property);
                }
                String name = p.value();
                if (name.isEmpty())
                    name = declareField.getName();
                PropertyDescriptor property = properties.get(name);
                if (property == null)
                    throw new NoSuchFieldError(name);
                if (p.secure()) {
                    Method readf = property.getReadMethod();
                    if (readf != null)
                        readf.setAccessible(true);
                    Method writef = property.getWriteMethod();
                    if (writef != null)
                        writef.setAccessible(true);
                }
                setField(defineObject, declareField, property);
                continue;
            }
        }
    }

    public static Field getField(Class<?> clazz, String fieldName)
            throws ReflectException {
        try {
            return clazz.getField(fieldName);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static Field getDeclaredField(Class<?> clazz, String fieldName)
            throws ReflectException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static Method getMethod(Class<?> clazz, String methodName,
            Class<?>... parameterTypes) throws ReflectException {
        try {
            return clazz.getMethod(methodName, parameterTypes);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static Method getDeclaredMethod(Class<?> clazz, String methodName,
            Class<?>... parameterTypes) throws ReflectException {
        try {
            return clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static Object get(Object obj, Field field) throws ReflectException {
        try {
            return field.get(obj);
        } catch (Exception e) {
            throw new ReflectException(e.getMessage(), e);
        }
    }

    public static void set(Object obj, Field field, Object value)
            throws ReflectException {
        try {
            field.set(obj, value);
        } catch (Exception e) {
            throw new ReflectException(e.getMessage(), e);
        }
    }

    public static Object get(Object obj, PropertyDescriptor property)
            throws ReflectException {
        Method read = property.getReadMethod();
        if (read == null)
            throw new IllegalArgumentException(LangNLS
                    .getString("Reflects.property") + property.getName() //$NON-NLS-1$
                    + LangNLS.getString("Reflects.noread")); //$NON-NLS-1$
        return invoke(obj, read);
    }

    public static void set(Object obj, PropertyDescriptor property, Object value) {
        Method write = property.getWriteMethod();
        if (write == null)
            throw new IllegalArgumentException(LangNLS
                    .getString("Reflects.property") + property.getName() //$NON-NLS-1$
                    + LangNLS.getString("Reflects.nowrite")); //$NON-NLS-1$
        invoke(obj, write, value);
    }

    public static <T> Constructor<T> getConstructor(Class<T> clazz,
            Class<?>... parameterTypes) {
        try {
            return clazz.getConstructor(parameterTypes);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static <T> Constructor<T> getDeclaredConstructor(Class<T> clazz,
            Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static <T> T newInstance(Class<T> clazz) throws ReflectException {
        try {
            return Control.newInstance(clazz);
        } catch (Exception e) {
            throw new ReflectException(e.getMessage(), e);
        }
    }

    public static <T> T newInstance(Constructor<T> ctor, Object... initargs)
            throws ReflectException {
        try {
            return Control.newInstance(ctor, initargs);
        } catch (Exception e) {
            throw new ReflectException(e.getMessage(), e);
        }
    }

    public static Object invoke(Object obj, Method method, Object... args)
            throws ReflectException {
        try {
            return Control.invoke(method, obj, args);
        } catch (Exception e) {
            throw new ReflectException(e.getMessage(), e);
        }
    }

}
