package net.bodz.bas.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class NullInvocationHandler
        implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        return null;
    }

    public static final NullInvocationHandler INSTANCE = new NullInvocationHandler();

}
