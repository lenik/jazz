package net.bodz.bas.util.exception;

import java.lang.reflect.Field;

import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.util.exception.RuntimizedException;

/**
 * Utility functions for {@link Throwable}s.
 */
public class Err {

    public static <T extends Throwable> void decompose(Class<T> targetExceptionType, RuntimeException runtimeException)
            throws T {
        if (runtimeException instanceof RuntimizedException) {
            T targetException = targetExceptionType.cast(runtimeException.getCause());
            throw targetException;
        }
        throw runtimeException;
    }

    public static <T extends Throwable> void decompose(Class<T> targetExceptionType, Runnable runnable)
            throws T {
        try {
            runnable.run();
        } catch (RuntimizedException runtimeException) {
            T targetException = targetExceptionType.cast(runtimeException.getCause());
            throw targetException;
        }
    }

    private static final Field detailMessageField;
    static {
        Field field = null;
        boolean ok = false;
        try {
            field = Throwable.class.getDeclaredField("detailMessage");
            field.setAccessible(true);
            ok = true;
        } catch (SecurityException e) {
        } catch (NoSuchFieldException e) {
        }
        if (ok)
            detailMessageField = field;
        else
            detailMessageField = null;
    }

    public static void setMessage(Throwable t, String mesg) {
        if (detailMessageField == null)
            return;
        try {
            Jdk7Reflect.set(detailMessageField, t, mesg);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void setMessagePrefix(Throwable t, String prefix) {
        if (detailMessageField == null)
            return;
        String mesg = t.getMessage();
        if (mesg == null || mesg.isEmpty())
            mesg = prefix;
        else
            mesg = prefix + ": " + mesg;
        try {
            Jdk7Reflect.set(detailMessageField, t, mesg);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
