package net.bodz.bas.valtype.util;

import java.lang.reflect.Method;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;

public class ClassInstance {

    public static <T> T getClassInstance(Class<T> clazz, Object... args)
            throws ReflectiveOperationException {
        if (clazz == null)
            return null;
        if (void.class.equals(clazz))
            return null;

        if (clazz.isInterface())
            throw new OutOfDomainException("clazz", clazz, "interface");

        Class<?>[] argTypes = TypeArray.getClasses(args);
        Method method = Jdk7Reflect.getMethod(clazz, "getInstance", argTypes);
        Object instance = Jdk7Reflect.invoke(method, null, args);
        return clazz.cast(instance);
    }

}
