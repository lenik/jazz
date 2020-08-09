package net.bodz.bas.http.thread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AsyncInvocationHandler
        implements InvocationHandler {

    Object obj;
    IAsyncCallback callback;

    public AsyncInvocationHandler(Object obj, IAsyncCallback callback) {
        this.obj = obj;
        this.callback = callback;
    }

    public static Object newInstance(Object obj, IAsyncCallback callback) {
        Class<?> objClass = obj.getClass();
        ClassLoader classLoader = objClass.getClassLoader();
        AsyncInvocationHandler handler = new AsyncInvocationHandler(obj, callback);
        return Proxy.newProxyInstance(classLoader, objClass.getInterfaces(), handler);
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args)
            throws Throwable {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Object retval = method.invoke(proxy, args);
                    callback.onReturn(retval);
                } catch (InvocationTargetException e) {
                    Throwable targetException = e.getTargetException();
                    if (targetException instanceof InterruptedException) {
                        callback.onInterrupt((InterruptedException) targetException);
                    } else {
                        callback.onException(e);
                    }
                } catch (ReflectiveOperationException e) {
                    callback.onException(e);
                }
            }
        };
        thread.start();
        return thread;
    }

}
