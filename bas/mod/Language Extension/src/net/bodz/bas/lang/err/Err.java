package net.bodz.bas.lang.err;

import java.lang.reflect.InvocationTargetException;

public class Err {

    public static void unwrap(InvocationTargetException e) {
        Throwable t = e.getCause();
        if (t instanceof RuntimeException)
            throw (RuntimeException) t;
        if (t instanceof Error)
            throw (RuntimeException) t;
        throw new RuntimeException(t.getMessage(), t);
    }

}
