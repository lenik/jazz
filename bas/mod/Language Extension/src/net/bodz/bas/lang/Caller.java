package net.bodz.bas.lang;

import sun.reflect.Reflection;

public class Caller {

    public static Class<?> getCallerClass(int n) {
        return Reflection.getCallerClass(n + 2);
    }

    /**
     * equiv. to getCallerClass(0)
     */
    public static Class<?> getCallerClass() {
        return getCallerClass(1);
    }

    public static ClassLoader getCallerClassLoader(int n) {
        return getCallerClass(n + 2).getClassLoader();
    }

    /**
     * equiv. to getCallerClassLoader(0)
     */
    public static ClassLoader getCallerClassLoader() {
        return getCallerClassLoader(1);
    }

    public static StackTraceElement getStack(int n) {
        StackTraceElement[] stackTrace;
        try {
            throw new Exception(); // trace[0]
        } catch (Exception e) {
            stackTrace = e.getStackTrace();
        }
        assert stackTrace != null;
        return stackTrace[n + 1];
    }

    public static StackTraceElement getCallerStack() {
        return getStack(1);
    }

    // NOT IMPLEMENTED.
    public static native Object getCallerObject(int n);

    static {
        // System.loadLibrary("native");
    }

}
