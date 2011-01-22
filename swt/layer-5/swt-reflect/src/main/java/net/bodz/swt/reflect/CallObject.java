package net.bodz.swt.reflect;

import java.lang.reflect.Method;

import net.bodz.bas.lang.err.ReflectException;
import net.bodz.bas.lang.util.Reflects;

public class CallObject extends CallContext {

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

    public Object invoke() throws ReflectException {
        Object retval = Reflects.invoke(object, method, parameters);
        setRetval(retval);
        return retval;
    }

}
