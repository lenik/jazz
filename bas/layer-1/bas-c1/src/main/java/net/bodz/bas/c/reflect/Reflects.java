package net.bodz.bas.c.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.googlecode.openbeans.PropertyDescriptor;

public class Reflects {

    public static Object get(Object obj, PropertyDescriptor property)
            throws IllegalAccessException, InvocationTargetException {
        Method readf = property.getReadMethod();
        if (readf == null)
            throw new IllegalArgumentException("property " + property.getName() + " isn\'t readable");
        return readf.invoke(obj);
    }

    public static void set(Object obj, PropertyDescriptor property, Object value)
            throws IllegalAccessException, InvocationTargetException {
        Method writef = property.getWriteMethod();
        if (writef == null)
            throw new IllegalArgumentException("property " + property.getName() + " isn\'t writable");
        writef.invoke(obj, value);
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
