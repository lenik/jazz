package net.bodz.bas.jvm.stack;

public class Caller {

    public static StackTraceElement stackTrace(int caller) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return stackTrace[caller + 1];
    }

    public static Class<?> getCallerImplementationClass(int caller) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return getClass(stackTrace[caller + 1]);
    }

    /**
     * In the following, `<code>caller-method</code>' is the method which calls
     * {@link #getCallerClass(int)}, and `<code>self-method</code>' is the
     * {@link #getCallerClass(int)} method itself.
     *
     * @param caller
     *            Specify to 1 to get the <code>caller-method</code>, and 0 to get the
     *            <code>self-method</code>.
     */
    public static Class<?> getCallerClass(int caller) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return getClass(stackTrace[caller + 1]);
    }

    public static Class<?> getClass(StackTraceElement element) {
        String className = element.getClassName();
        try {
            Class<?> c = Class.forName(className);
            return c;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * In the following, `<code>caller-method</code>' is the method which calls
     * {@link #getCallerClassLoader(int)}, and `<code>self-method</code>' is the
     * {@link #getCallerClassLoader(int)} method itself.
     *
     * @param caller
     *            Specify to 1 to get the <code>caller-method</code>, and 0 to get the
     *            <code>self-method</code>.
     */
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
