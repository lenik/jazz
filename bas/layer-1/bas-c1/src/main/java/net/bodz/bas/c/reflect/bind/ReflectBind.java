package net.bodz.bas.c.reflect.bind;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.openbeans.BeanInfo;
import com.googlecode.openbeans.IntrospectionException;
import com.googlecode.openbeans.Introspector;
import com.googlecode.openbeans.PropertyDescriptor;

public class ReflectBind {

    static Class<?> ifvoid(Class<?> c, Class<?> t) {
        return c == void.class ? t : c;
    }

    public static void _setFieldForce(Object o, Field field, Object value) {
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

    /**
     * @throws ReflectBindTargetException
     */
    public static Object invoke(Object instance, Method method, Object... args) {
        try {
            return method.invoke(instance, args);
        } catch (ReflectiveOperationException e) {
            throw new ReflectBindTargetException(e.getMessage(), e);
        }
    }

    /**
     * @throws ReflectBindTargetException
     */
    public static Object get(Object obj, Field field)
            throws ReflectBindTargetException {
        try {
            return field.get(obj);
        } catch (ReflectiveOperationException e) {
            throw new ReflectBindTargetException(e.getMessage(), e);
        }
    }

    /**
     * @throws ReflectBindTargetException
     */
    public static void set(Object obj, Field field, Object value)
            throws ReflectBindTargetException {
        try {
            field.set(obj, value);
        } catch (ReflectiveOperationException e) {
            throw new ReflectBindTargetException(e.getMessage(), e);
        }
    }

    /**
     * @throws ReflectBindTargetException
     */
    public static int getInt(Object obj, Field field)
            throws ReflectBindTargetException {
        try {
            return field.getInt(obj);
        } catch (ReflectiveOperationException e) {
            throw new ReflectBindTargetException(e.getMessage(), e);
        }
    }

    /**
     * @throws ReflectBindTargetException
     */
    public static void setInt(Object obj, Field field, int value)
            throws ReflectBindTargetException {
        try {
            field.setInt(obj, value);
        } catch (ReflectiveOperationException e) {
            throw new ReflectBindTargetException(e.getMessage(), e);
        }
    }

    /**
     * @throws ReflectBindTargetException
     */
    public static boolean getBoolean(Object obj, Field field)
            throws ReflectBindTargetException {
        try {
            return field.getBoolean(obj);
        } catch (ReflectiveOperationException e) {
            throw new ReflectBindTargetException(e.getMessage(), e);
        }
    }

    /**
     * @throws ReflectBindTargetException
     */
    public static void setBoolean(Object obj, Field field, boolean value)
            throws ReflectBindTargetException {
        try {
            field.setBoolean(obj, value);
        } catch (ReflectiveOperationException e) {
            throw new ReflectBindTargetException(e.getMessage(), e);
        }
    }

}
