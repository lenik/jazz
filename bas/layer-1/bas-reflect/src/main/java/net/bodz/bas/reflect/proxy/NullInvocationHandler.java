package net.bodz.bas.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @test {@link NullInvocationHandlerTest}
 */
public class NullInvocationHandler
        implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        return null;
    }

    static NullInvocationHandler instance = new NullInvocationHandler();

    public static NullInvocationHandler getInstance() {
        return instance;
    }

}
