package net.bodz.bas.lang.err;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import net.bodz.bas.lang.util.Reflects;

public class Err {

    public static void unwrap(InvocationTargetException e) {
        Throwable t = e.getCause();
        if (t instanceof RuntimeException)
            throw (RuntimeException) t;
        if (t instanceof Error)
            throw (RuntimeException) t;
        throw new RuntimeException(t.getMessage(), t);
    }

    private static final Field detailMessage;
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
            detailMessage = field;
        else
            detailMessage = null;
    }

    public static void setMessage(Throwable t, String mesg) {
        if (detailMessage == null)
            return;
        Reflects.set(t, detailMessage, mesg);
    }

    public static void setMessagePrefix(Throwable t, String prefix) {
        if (detailMessage == null)
            return;
        String mesg = t.getMessage();
        if (mesg == null || mesg.isEmpty())
            mesg = prefix;
        else
            mesg = prefix + ": " + mesg;
        Reflects.set(t, detailMessage, mesg);
    }

}
