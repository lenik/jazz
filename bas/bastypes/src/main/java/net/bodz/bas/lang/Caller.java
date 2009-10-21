package net.bodz.bas.lang;

import sun.reflect.Reflection;

public class Caller {

    public static Class<?> getCallerClass(int caller) {
        return Reflection.getCallerClass(caller + 2);
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

    public static StackTraceElement getStack(int caller) {
        StackTraceElement[] stackTrace;
        try {
            throw new Exception(); // trace[0]
        } catch (Exception e) {
            stackTrace = e.getStackTrace();
        }
        assert stackTrace != null;
        return stackTrace[caller + 1];
    }

    // NOT IMPLEMENTED.
    public static native Object getCallerObject(int caller);

    static {
        // System.loadLibrary("native");
    }

}
