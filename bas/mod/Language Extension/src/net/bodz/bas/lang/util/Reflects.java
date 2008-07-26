package net.bodz.bas.lang.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.lang.annotations.ReflectField;
import net.bodz.bas.lang.annotations.ReflectMethod;
import net.bodz.bas.lang.annotations.ReflectProperty;

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

    public static void bind(Class<?> declareClass, Object defineObject) {
        assert defineObject != null;
        Class<?> defineClass = defineObject.getClass();
        Map<String, PropertyDescriptor> properties = null;

        for (Field declareField : declareClass.getDeclaredFields()) {
            Object def = null;
            ReflectField f = declareField.getAnnotation(ReflectField.class);
            if (f != null) {
                if (!Field.class.isAssignableFrom(declareField.getType()))
                    throw new Error(ReflectField.class
                            + " must be declared on Field field");
                String name = f.value();
                if (name.isEmpty())
                    name = declareField.getName();
                if (f.secure()) {
                    try {
                        def = defineClass.getField(name);
                    } catch (SecurityException e) {
                        throw new Error(e.getMessage(), e);
                    } catch (NoSuchFieldException e) {
                    }
                } else {
                    do {
                        try {
                            Field defField = defineClass.getDeclaredField(name);
                            defField.setAccessible(true);
                            def = defField;
                            break;
                        } catch (SecurityException e) {
                            throw new Error(e.getMessage(), e);
                        } catch (NoSuchFieldException e) {
                        }
                        defineClass = defineClass.getSuperclass();
                    } while (defineClass != null);
                }
                if (def == null)
                    throw new NoSuchFieldError(name);
                setField(defineObject, declareField, def);
                continue;
            }

            ReflectMethod m = declareField.getAnnotation(ReflectMethod.class);
            if (m != null) {
                String name = m.value();
                if (name.isEmpty())
                    name = declareField.getName();
                Class<?>[] parameters = m.parameters();
                if (m.secure()) {
                    try {
                        def = defineClass.getMethod(name, parameters);
                    } catch (SecurityException e) {
                        throw new Error(e.getMessage(), e);
                    } catch (NoSuchMethodException e) {
                    }
                } else {
                    do {
                        try {
                            Method defMethod = defineClass.getDeclaredMethod(
                                    name, parameters);
                            defMethod.setAccessible(true);
                            def = defMethod;
                            break;
                        } catch (SecurityException e) {
                            throw new Error(e.getMessage(), e);
                        } catch (NoSuchMethodException e) {
                        }
                        defineClass = defineClass.getSuperclass();
                    } while (defineClass != null);
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
                    BeanInfo beanInfo;
                    try {
                        beanInfo = Introspector.getBeanInfo(defineClass);
                    } catch (IntrospectionException e) {
                        throw new Error(e.getMessage(), e);
                    }
                    properties = new HashMap<String, PropertyDescriptor>();
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

    public static Object get(Object obj, Field field) {
        try {
            return field.get(obj);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void set(Object obj, Field field, Object value) {
        try {
            field.set(obj, value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static Object get(Object obj, PropertyDescriptor property) {
        Method read = property.getReadMethod();
        if (read == null)
            throw new RuntimeException("property " + property.getName()
                    + " isn't readable");
        return invoke(obj, read);
    }

    public static void set(Object obj, PropertyDescriptor property, Object value) {
        Method write = property.getWriteMethod();
        if (write == null)
            throw new RuntimeException("property " + property.getName()
                    + " isn't writable");
        invoke(obj, write, value);
    }

    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
