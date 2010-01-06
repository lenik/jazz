package net.bodz.bas.commons.scripting;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @test {@link ReflectsTest}
 */
public class Reflects {

    private static void _setFieldForce(Object o, Field field, Object value) {
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

    public static void bind(Class<?> declareClass, Object defineObject) {
        assert defineObject != null;
        Class<?> defineClass = defineObject.getClass();
        bind(declareClass, defineClass, defineObject);
    }

    public static void bind(Class<?> declareClass, Class<?> defineClass, Object defineObject) {
        Map<String, PropertyDescriptor> properties = null;

        for (Field declareField : declareClass.getDeclaredFields()) {
            Object def = null;
            ReflectField f = declareField.getAnnotation(ReflectField.class);
            if (f != null) {
                if (!Field.class.isAssignableFrom(declareField.getType()))
                    throw new Error(ReflectField.class + " must be declared on Field field"); 
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
                _setFieldForce(defineObject, declareField, def);
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
                            Method defMethod = _class.getDeclaredMethod(name, parameters);
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
                _setFieldForce(defineObject, declareField, def);
                continue;
            }

            ReflectProperty p = declareField.getAnnotation(ReflectProperty.class);
            if (p != null) {
                if (properties == null) {
                    Class<?> _class = ifvoid(p._class(), defineClass);
                    BeanInfo beanInfo;
                    try {
                        beanInfo = Introspector.getBeanInfo(_class);
                    } catch (IntrospectionException e) {
                        throw new Error(e.getMessage(), e);
                    }
                    properties = new HashMap<String, PropertyDescriptor>();
                    for (PropertyDescriptor property : beanInfo.getPropertyDescriptors())
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
                _setFieldForce(defineObject, declareField, property);
                continue;
            }
        }
    }

    public static Object get(Object obj, PropertyDescriptor property)
            throws IllegalAccessException, InvocationTargetException {
        Method read = property.getReadMethod();
        if (read == null)
            throw new IllegalArgumentException("property " + property.getName() 
                    + " isn\'t readable"); 
        return read.invoke(obj);
    }

    public static void set(Object obj, PropertyDescriptor property, Object value)
            throws IllegalAccessException, InvocationTargetException {
        Method write = property.getWriteMethod();
        if (write == null)
            throw new IllegalArgumentException("property " + property.getName() 
                    + " isn\'t writable"); 
        write.invoke(obj, value);
    }

    public static Class<?> getNamedSuperclass(Class<?> start) {
        while (start.isAnonymousClass())
            start = start.getSuperclass();
        return start;
    }

    public static Object getEnclosingInstance(Object innerInstance)
            throws IllegalAccessException {
        Class<? extends Object> innerClass = innerInstance.getClass();
        int modifiers = innerClass.getModifiers();
        if (Modifier.isStatic(modifiers))
            return null;
        // Field[] fields = innerClass.getDeclaredFields();
        // for (Field field : fields)
        // System.out.println("Field: " + field.getName());
        int i = 0;
        while (true) {
            try {
                Field outerField = innerClass.getDeclaredField("this$" + i); 
                outerField.setAccessible(true);
                return outerField.get(innerInstance);
            } catch (NoSuchFieldException e) {
                i++;
                continue;
            }
        }
    }

}
