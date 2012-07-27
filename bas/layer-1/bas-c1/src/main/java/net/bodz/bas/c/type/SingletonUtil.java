package net.bodz.bas.c.type;

import java.lang.reflect.Method;

import net.bodz.bas.err.OutOfDomainException;

public class SingletonUtil {

    public static <T> T getClassInstance(Class<T> clazz, Object... args)
            throws ReflectiveOperationException {
        if (clazz == null)
            return null;
        if (void.class.equals(clazz))
            return null;

        if (clazz.isInterface())
            throw new OutOfDomainException("clazz", clazz, "interface");

        Class<?>[] argTypes = TypeArray.getClasses(null /* fallback */, args);
        Method method = clazz.getMethod("getInstance", argTypes);
        Object instance = method.invoke(null, args);
        return clazz.cast(instance);
    }

}
