package net.bodz.bas.reflect.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.NoSuchMethodException;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.type.util.TypeArray;
import net.bodz.bas.type.util.TypeDistance;
import net.bodz.bas.type.util.TypeName;

import org.apache.commons.lang.ArrayUtils;

public class CompatMethods {

    public static Method getMethod(Class<?> clazz, String name, Class<?>[] argtypes, boolean all) {
        int mindist = -1;
        Method min = null;
        for (Method method : Members.methods(clazz, name, all)) {
            Class<?>[] decltypes = method.getParameterTypes();
            int dist = TypeDistance.dist(decltypes, argtypes);
            if (dist == -1)
                continue;
            if (dist < mindist || mindist == -1) {
                mindist = dist;
                min = method;
            }
        }
        return min;
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>[] argtypes) {
        return getMethod(clazz, name, argtypes, false);
    }

    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>[] argtypes, boolean all) {
        int mindist = -1;
        Constructor<T> min = null;
        for (Constructor<T> ctor : Members.constructors(clazz, all)) {
            Class<?>[] decltypes = ctor.getParameterTypes();
            int dist = TypeDistance.dist(decltypes, argtypes);
            if (dist == -1)
                continue;
            if (dist < mindist || mindist == -1) {
                mindist = dist;
                min = ctor;
            }
        }
        return min;
    }

    public static Constructor<?> getConstructor(Class<?> clazz, Class<?>[] argtypes) {
        return getConstructor(clazz, argtypes, false);
    }

    public static <T> T newInstance(Class<T> clazz, Object... args)
            throws ReflectiveOperationException {
        Class<?>[] types = TypeArray.getClasses(args);
        Constructor<T> ctor = getConstructor(clazz, types, true);
        if (ctor == null) {
            throw new NoSuchMethodException(clazz.getName() + "(" + TypeName.join(types) + ")");
        }
        boolean prevState = ctor.isAccessible();
        if (!prevState)
            ctor.setAccessible(true);
        try {
            return Jdk7Reflect.newInstance(ctor, args);
        } finally {
            if (!prevState)
                ctor.setAccessible(false);
        }
    }

    public static <T> T newMemberInstance(Class<T> clazz, Object _this, Object... args)
            throws ReflectiveOperationException {
        Object[] concat = ArrayUtils.add(args, 0, _this);
        return newInstance(clazz, concat);
    }

    public static Object newInstance(String className, Object... args)
            throws ReflectiveOperationException {
        Class<?> clazz = Jdk7Reflect.forName(className);
        return newInstance(clazz, args);
    }

    public static Object invoke(Class<?> clazz, Object obj, String methodName, Object... args)
            throws ReflectiveOperationException {
        Class<?>[] types = TypeArray.getClasses(args);
        Method method = getMethod(clazz, methodName, types);
        if (method == null)
            throw new NoSuchMethodException(clazz.getName() + "." + methodName + "(" + TypeName.join(types) + ")");
        return Jdk7Reflect.invoke(method, obj, args);
    }

    public static Object invoke(Class<?> clazz, String methodName, Object... args)
            throws ReflectiveOperationException {
        return invoke(clazz, null, methodName, args);
    }

    public static Object invoke(Object obj, String methodName, Object... args)
            throws ReflectiveOperationException {
        return invoke(obj.getClass(), obj, methodName, args);
    }

}
