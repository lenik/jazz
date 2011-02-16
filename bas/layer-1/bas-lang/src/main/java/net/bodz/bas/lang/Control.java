package net.bodz.bas.lang;

import java.lang.reflect.Method;

import net.bodz.bas.jdk6compat.jdk7emul.IllegalAccessException;
import net.bodz.bas.jdk6compat.jdk7emul.InvocationTargetException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;

public class Control
        extends Error {

    private static final long serialVersionUID = 1L;

    public Control() {
        super();
    }

    public Control(String message, Throwable cause) {
        super(message, cause);
    }

    public Control(String message) {
        super(message);
    }

    public Control(Throwable cause) {
        super(cause);
    }

    public static Object invoke(Method method, Object obj, Object... parameters)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, Control {
        try {
            return Jdk7Reflect.invoke(method, obj, parameters);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof Control)
                throw (Control) cause;
            throw e;
        }
    }

}
