package net.bodz.bas.jdk6compat.jdk7emul;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * This will convert all (J2SE 1.7) {@link java.lang.ReflectiveOperationException} to
 * {@link ReflectiveOperationException}
 */
public class Jdk7Reflect {

    public static Object newInstance(Class<?> clazz)
            throws InstantiationException, IllegalAccessException {
        try {
            return clazz.newInstance();
        } catch (java.lang.InstantiationException e) {
            throw new InstantiationException(e.getMessage(), e);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static <T> T newInstance(Constructor<T> constructor, Object... initargs)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            return constructor.newInstance(initargs);
        } catch (java.lang.InstantiationException e) {
            throw new InstantiationException(e.getMessage(), e);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        } catch (java.lang.reflect.InvocationTargetException e) {
            throw new InvocationTargetException(e.getMessage(), e);
        }
    }

    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        try {
            return clazz.getConstructor(parameterTypes);
        } catch (java.lang.NoSuchMethodException e) {
            throw new NoSuchMethodException(e.getMessage(), e);
        }
    }

    public static <T> Constructor<T> getDeclaredConstructor(Class<T> clazz, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
        } catch (java.lang.NoSuchMethodException e) {
            throw new NoSuchMethodException(e.getMessage(), e);
        }
    }

    public static Field getDeclaredField(Class<?> clazz, String name)
            throws NoSuchFieldException, SecurityException {
        try {
            return clazz.getDeclaredField(name);
        } catch (java.lang.NoSuchFieldException e) {
            throw new NoSuchFieldException(e.getMessage(), e);
        }
    }

    public static Method getDeclaredMethod(Class<?> clazz, String name, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (java.lang.NoSuchMethodException e) {
            throw new NoSuchMethodException(e.getMessage(), e);
        }
    }

    public static Field getField(Class<?> clazz, String name)
            throws NoSuchFieldException, SecurityException {
        try {
            return clazz.getField(name);
        } catch (java.lang.NoSuchFieldException e) {
            throw new NoSuchFieldException(e.getMessage(), e);
        }
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        try {
            return clazz.getMethod(name, parameterTypes);
        } catch (java.lang.NoSuchMethodException e) {
            throw new NoSuchMethodException(e.getMessage(), e);
        }
    }

    public static Object get(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.get(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static boolean getBoolean(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getBoolean(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static byte getByte(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getByte(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static char getChar(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getChar(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static double getDouble(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getDouble(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static float getFloat(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getFloat(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static int getInt(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getInt(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static long getLong(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getLong(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static short getShort(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getShort(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static void set(Field field, Object obj, Object value)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.set(obj, value);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static void setBoolean(Field field, Object obj, boolean z)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setBoolean(obj, z);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static void setByte(Field field, Object obj, byte b)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setByte(obj, b);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static void setChar(Field field, Object obj, char c)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setChar(obj, c);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static void setDouble(Field field, Object obj, double d)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setDouble(obj, d);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static void setFloat(Field field, Object obj, float f)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setFloat(obj, f);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static void setInt(Field field, Object obj, int i)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setInt(obj, i);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static void setLong(Field field, Object obj, long l)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setLong(obj, l);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static void setShort(Field field, Object obj, short s)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setShort(obj, s);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    public static Object invoke(Method method, Object obj, Object... args)
            throws IllegalAccessException, InvocationTargetException, IllegalArgumentException {
        try {
            return method.invoke(obj, args);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        } catch (java.lang.reflect.InvocationTargetException e) {
            throw new InvocationTargetException(e.getMessage(), e);
        }
    }

}
