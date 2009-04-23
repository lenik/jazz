package net.bodz.bas.las;

import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.types.DirMap;

public class LasUnits {

    private static class Nil implements LasUnit {

        @Override
        public boolean enter(Object... args) {
            return true;
        }

        @Override
        public void leave() throws IllegalUsageError {
        }

        @Override
        public <T> T leave(T returnValue) throws IllegalUsageError {
            return returnValue;
        }

        @Override
        public <T extends Throwable> T leave(T t) throws IllegalUsageError, T {
            return t;
        }

    }

    public static final LasUnit nil = new Nil();

    static DirMap<LasUnit>      registry;
    static {
        registry = new DirMap<LasUnit>();
    }

    public static void register(Class<?> clazz, LasUnit unit) {
        String id = clazz.getName();
        registry.put(id, unit);
    }

    static LasUnit get(int n) {
        StackTraceElement stack = Caller.getStack(n + 1);
        String id = (stack.getClassName() + "/" + stack.getMethodName()); //$NON-NLS-1$
        LasUnit unit = registry.floor(id);
        if (unit != null)
            return unit;
        return nil;
    }

    public static LasUnit get() {
        return get(1);
    }

    public static boolean enter(Object... args) {
        return get(1).enter(args);
    }

    public static void leave() throws IllegalUsageError {
        get(1).leave();
    }

    public static <T> T leave(T returnValue) throws IllegalUsageError {
        return get(1).leave(returnValue);
    }

    public static <T extends Throwable> T leave(T t) throws IllegalUsageError, T {
        return get(1).leave(t);
    }

}
