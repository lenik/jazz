package net.bodz.swt.viz.core;

import java.lang.reflect.Method;

import net.bodz.bas.c.reflect.Reflects;

public class CallObject
        extends CallContext {

    private final Object object;
    private final Method method;

    public CallObject(Object object, Method method) {
        super(method.getParameterTypes());
        this.object = object;
        this.method = method;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }

    public Object invoke()
            throws ReflectiveOperationException {
        Object retval = Reflects.invoke(object, method, parameters);
        setRetval(retval);
        return retval;
    }

}
