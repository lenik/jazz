package net.bodz.bas.jdk6compat.jdk7emul;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * This will convert all (J2SE 1.7) {@link java.lang.ReflectiveOperationException} to
 * {@link ReflectiveOperationException}
 */
public class Jdk7Reflect {

    /**
     * @see Class#forName(String)
     */
    public static Class<?> forName(String className)
            throws ClassNotFoundException {
        try {
            return Class.forName(className);
        } catch (java.lang.ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }

    /**
     * @see Class#forName(String, boolean, ClassLoader)
     */
    public static Class<?> forName(String name, boolean initialize, ClassLoader loader)
            throws ClassNotFoundException {
        try {
            return Class.forName(name, initialize, loader);
        } catch (java.lang.ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }

    /**
     * @see Class#newInstance()
     */
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

    /**
     * @see Constructor#newInstance(Object...)
     */
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

    /**
     * @see Class#getConstructor(Class...)
     */
    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        try {
            return clazz.getConstructor(parameterTypes);
        } catch (java.lang.NoSuchMethodException e) {
            throw new NoSuchMethodException(e.getMessage(), e);
        }
    }

    /**
     * @see Class#getDeclaredConstructor(Class...)
     */
    public static <T> Constructor<T> getDeclaredConstructor(Class<T> clazz, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
        } catch (java.lang.NoSuchMethodException e) {
            throw new NoSuchMethodException(e.getMessage(), e);
        }
    }

    /**
     * @see Class#getDeclaredField(String)
     */
    public static Field getDeclaredField(Class<?> clazz, String name)
            throws NoSuchFieldException, SecurityException {
        try {
            return clazz.getDeclaredField(name);
        } catch (java.lang.NoSuchFieldException e) {
            throw new NoSuchFieldException(e.getMessage(), e);
        }
    }

    /**
     * @see Class#getDeclaredMethod(String, Class...)
     */
    public static Method getDeclaredMethod(Class<?> clazz, String name, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (java.lang.NoSuchMethodException e) {
            throw new NoSuchMethodException(e.getMessage(), e);
        }
    }

    /**
     * @see Class#getField(String)
     */
    public static Field getField(Class<?> clazz, String name)
            throws NoSuchFieldException, SecurityException {
        try {
            return clazz.getField(name);
        } catch (java.lang.NoSuchFieldException e) {
            throw new NoSuchFieldException(e.getMessage(), e);
        }
    }

    /**
     * @see Class#getMethod(String, Class...)
     */
    public static Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        try {
            return clazz.getMethod(name, parameterTypes);
        } catch (java.lang.NoSuchMethodException e) {
            throw new NoSuchMethodException(e.getMessage(), e);
        }
    }

    /**
     * @see Field#get(Object)
     */
    public static Object get(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.get(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#getBoolean(Object)
     */
    public static boolean getBoolean(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getBoolean(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#getByte(Object)
     */
    public static byte getByte(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getByte(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#getChar(Object)
     */
    public static char getChar(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getChar(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#getDouble(Object)
     */
    public static double getDouble(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getDouble(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#getFloat(Object)
     */
    public static float getFloat(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getFloat(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#getInt(Object)
     */
    public static int getInt(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getInt(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#getLong(Object)
     */
    public static long getLong(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getLong(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#getShort(Object)
     */
    public static short getShort(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            return field.getShort(obj);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#set(Object, Object)
     */
    public static void set(Field field, Object obj, Object value)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.set(obj, value);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#setBoolean(Object, boolean)
     */
    public static void setBoolean(Field field, Object obj, boolean z)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setBoolean(obj, z);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#setByte(Object, byte)
     */
    public static void setByte(Field field, Object obj, byte b)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setByte(obj, b);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#setChar(Object, char)
     */
    public static void setChar(Field field, Object obj, char c)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setChar(obj, c);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#setDouble(Object, double)
     */
    public static void setDouble(Field field, Object obj, double d)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setDouble(obj, d);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#setFloat(Object, float)
     */
    public static void setFloat(Field field, Object obj, float f)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setFloat(obj, f);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#setInt(Object, int)
     */
    public static void setInt(Field field, Object obj, int i)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setInt(obj, i);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#setLong(Object, long)
     */
    public static void setLong(Field field, Object obj, long l)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setLong(obj, l);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Field#setShort(Object, short)
     */
    public static void setShort(Field field, Object obj, short s)
            throws IllegalArgumentException, IllegalAccessException {
        try {
            field.setShort(obj, s);
        } catch (java.lang.IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see Method#invoke(Object, Object...)
     */
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
