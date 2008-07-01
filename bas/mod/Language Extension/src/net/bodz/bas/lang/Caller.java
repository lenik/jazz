package net.bodz.bas.lang;

import sun.reflect.Reflection;

public class Caller {

    public static Class<?> getCallerClass(int n) {
        return Reflection.getCallerClass(n + 1);
    }

    public static Class<?> getCallerClass() {
        return getCallerClass(2);
    }

    public static ClassLoader getCallerClassLoader(int n) {
        return getCallerClass(n + 1).getClassLoader();
    }

    public static ClassLoader getCallerClassLoader() {
        return getCallerClassLoader(2);
    }

    // NOT IMPLEMENTED.
    public static native Object getCallerObject(int n);

    static {
        // System.loadLibrary("native");
    }

}
