package net.bodz.bas.c.type;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.OutOfDomainException;

/**
 * @see CachedInstantiator
 */
public class SingletonUtil {

    public static <T> T getInstanceField(Class<T> clazz)
            throws LoadException {
        if (clazz == null)
            return null;
        if (clazz == void.class)
            return null;

        try {
            Field field = clazz.getField("INSTANCE");
            Object instance = field.get(null);
            return clazz.cast(instance);
        } catch (Exception e) {
            throw new LoadException(String.format(//
                    "Failed to get instance field from class %s: %s", clazz.getName(), e.getMessage()), e);
        }
    }

    public static <T> T callGetInstance(Class<T> clazz, Object... args)
            throws LoadException {
        if (clazz == null)
            return null;
        if (clazz == void.class)
            return null;

        if (clazz.isInterface())
            throw new OutOfDomainException("clazz", clazz, "interface");

        Class<?>[] argTypes = TypeArray.getClasses(null /* fallback */, args);
        try {
            Method method = clazz.getMethod("getInstance", argTypes);
            Object instance = method.invoke(null, args);
            return clazz.cast(instance);
        } catch (Exception e) {
            throw new LoadException("Failed to get instance: " + e.getMessage(), e);
        }
    }

    public static <T> T instantiateCached(Class<? extends T> clazz) {
        return CachedInstantiator.getInstance().instantiate(clazz);
    }

}
