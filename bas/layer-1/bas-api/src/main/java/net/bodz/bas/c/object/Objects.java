package net.bodz.bas.c.object;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Objects {

    /**
     * @see ArrayOps.Objects#equals(Object[], Object[])
     */
    @Deprecated
    public static boolean equals(byte[] pattern, byte[] bytes, int off) {
        assert pattern != null;
        assert bytes != null;
        assert off >= 0 && bytes.length >= pattern.length + off;
        for (int i = 0; i < pattern.length; i++) {
            if (bytes[off + i] != pattern[i])
                return false;
        }
        return true;
    }

    /**
     * @see net.bodz.bas.c1.collection.util.ArrayOps.Bytes#equalsWithWrap(byte[], byte[])
     */
    @Deprecated
    public static boolean equalsWithWrap(byte[] pattern, byte[] bytes, int off) {
        for (int i = 0; i < pattern.length; i++) {
            if (off >= bytes.length)
                off = 0;
            if (bytes[off] != pattern[i])
                return false;
            off++;
        }
        return true;
    }

    @Deprecated
    public static boolean equals(Object[] pattern, Object[] objs, int off) {
        assert pattern != null;
        assert objs != null;
        assert off >= 0 && objs.length >= pattern.length + off;
        for (int i = 0; i < pattern.length; i++) {
            if (!Nullables.equals(objs[off + i], pattern[i]))
                return false;
        }
        return true;
    }

    @Deprecated
    public static boolean equalsWithWrap(Object[] pattern, Object[] objs, int off) {
        for (int i = 0; i < pattern.length; i++) {
            if (off >= objs.length)
                off = 0;
            if (!Nullables.equals(pattern[i], objs[off]))
                return false;
            off++;
        }
        return true;
    }

    public static Object cloneByConstructor(Object object, Class<?> copyClass)
            throws CloneNotSupportedException {
        if (object == null)
            return null;
        Class<?> clazz = object.getClass();
        Constructor<?> ctor;
        Object cloned;
        try {
            ctor = clazz.getConstructor(copyClass);
            cloned = ctor.newInstance(object);
            return cloned;
        } catch (NoSuchMethodException e) {
            throw new CloneNotSupportedException("No copy constructor defined in class " + clazz.getName());
        } catch (IllegalAccessException e) {
            throw new CloneNotSupportedException("Cannot access copy constructor in class: " + clazz.getName());
        } catch (InvocationTargetException e) {
            throw new CloneNotSupportedException("Invocation target exception when cloning: " + e.getMessage());
        } catch (InstantiationException e) {
            throw new CloneNotSupportedException("Instantiation exception when cloning: " + e.getMessage());
        }
    }

    public static Object cloneByPersistency(Serializable object)
            throws CloneNotSupportedException {
        return null;
    }

}
