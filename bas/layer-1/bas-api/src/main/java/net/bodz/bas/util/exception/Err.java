package net.bodz.bas.util.exception;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import net.bodz.bas.err.RuntimizedException;
import net.bodz.bas.err.UnexpectedException;

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

    public static void setMessage(Throwable throwable, String message) {
        if (detailMessageField == null)
            return;
        try {
            detailMessageField.set(throwable, message);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void setMessagePrefix(Throwable throwable, String prefix) {
        if (detailMessageField == null)
            return;
        String message = throwable.getMessage();
        if (message == null || message.isEmpty())
            message = prefix;
        else
            message = prefix + ": " + message;
        try {
            detailMessageField.set(throwable, message);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <E extends RuntimeException> E throwOrWrap(Class<E> wrapperClass, Throwable e) {
        if (e instanceof Error)
            throw (Error) e;
        if (e instanceof RuntimeException)
            throw (RuntimeException) e;
        try {
            Constructor<E> ctor = wrapperClass.getConstructor(String.class, Throwable.class);
            E wrapped = ctor.newInstance(e.getMessage(), e);
            return wrapped;
        } catch (ReflectiveOperationException _roe) {
            throw new UnexpectedException(_roe.getMessage(), _roe);
        }
    }

}
