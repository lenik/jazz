package net.bodz.bas.lang;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Control extends RuntimeException {

    private static final long serialVersionUID = 1646492794599181112L;

    protected final Object    detail;

    public Control() {
        super();
        this.detail = null;
    }

    public Control(Object detail) {
        super();
        this.detail = detail;
    }

    public Control(Control cause) {
        super(cause);
        this.detail = null;
    }

    public Control(Control cause, Object detail) {
        super(cause);
        this.detail = detail;
    }

    public Object getDetail() {
        return detail;
    }

    @Override
    public String getMessage() {
        return "Detail: " + String.valueOf(detail);
    }

    public static <T> T newInstance(Class<T> clazz)
            throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        return clazz.newInstance();
    }

    public static <T> T newInstance(Constructor<T> ctor, Object... initargs)
            throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        try {
            return ctor.newInstance(initargs);
        } catch (InvocationTargetException et) {
            Throwable cause = et.getCause();
            if (cause instanceof Control)
                throw (Control) cause;
            throw et;
        }
    }

    public static Object invoke(Method method, Object obj, Object... args)
            throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        try {
            return method.invoke(obj, args);
        } catch (InvocationTargetException et) {
            Throwable cause = et.getCause();
            if (cause instanceof Control)
                throw (Control) cause;
            throw et;
        }
    }

}
