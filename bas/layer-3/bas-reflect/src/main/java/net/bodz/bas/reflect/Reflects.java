package net.bodz.bas.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.bodz.bas.jdk6compat.jdk7emul.IllegalAccessException;
import net.bodz.bas.jdk6compat.jdk7emul.InvocationTargetException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.NoSuchFieldException;

public class Reflects {

    public static Object get(Object obj, PropertyDescriptor property)
            throws IllegalAccessException, InvocationTargetException {
        Method read = property.getReadMethod();
        if (read == null)
            throw new IllegalArgumentException("property " + property.getName() + " isn\'t readable");
        return Jdk7Reflect.invoke(read, obj);
    }

    public static void set(Object obj, PropertyDescriptor property, Object value)
            throws IllegalAccessException, InvocationTargetException {
        Method write = property.getWriteMethod();
        if (write == null)
            throw new IllegalArgumentException("property " + property.getName() + " isn\'t writable");
        Jdk7Reflect.invoke(write, obj, value);
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
                Field outerField = Jdk7Reflect.getDeclaredField(innerClass, "this$" + i);
                outerField.setAccessible(true);
                return Jdk7Reflect.get(outerField, innerInstance);
            } catch (NoSuchFieldException e) {
                i++;
                continue;
            }
        }
    }

}
