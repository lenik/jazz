package net.bodz.bas.jvm.stack;

import java.lang.reflect.Method;

import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;

public class Caller {

    public static StackTraceElement stackTrace(int caller) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return stackTrace[caller + 1];
    }

    public static Class<?> getCallerImplementationClass(int caller) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return stackTrace[caller + 1].getClass();
    }

    static final Class<?> Reflection_class;
    static final Method Reflection_getCallerClass;
    static {
        try {
            Reflection_class = Jdk7Reflect.forName("sun.reflect.Reflection");
            Reflection_getCallerClass = Jdk7Reflect.getMethod(Reflection_class, "getCallerClass", int.class);
        } catch (ReflectiveOperationException e) {
            throw new UnsupportedOperationException("Get caller isn't supported. ", e);
        }
    }

    public static Class<?> getCallerClass(int caller) {
        try {
            return (Class<?>) Jdk7Reflect.invoke(Reflection_getCallerClass, null, caller + 2);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static ClassLoader getCallerClassLoader(int caller) {
        Class<?> callerClass = getCallerClass(caller + 1);
        if (callerClass == null) {
            for (int i = -5; i <= 5; i++) {
                System.out.printf("Caller[%d] = %s\n", i, getCallerClass(caller + 1 + i));
            }
            throw new Error("No caller class!");
        }
        return callerClass.getClassLoader();
    }

}
