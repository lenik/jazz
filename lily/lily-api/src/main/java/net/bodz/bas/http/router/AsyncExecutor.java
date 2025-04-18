package net.bodz.bas.http.router;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import net.bodz.mda.xjdoc.Xjdocs;

public class AsyncExecutor
        implements
            InvocationHandler {

    CallManager manager;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        method.getAnnotations();
        Xjdocs.getDefaultProvider().getClassDoc(proxy.getClass());

        CallRequest request = manager.newCall();
        // fill request...
        manager.push(request);

        CallResponse response = manager.waitForResponse(request);
        // process response

        Object returnValue = response.getReturnValue();
        return returnValue;
    }

}

interface IService {

    void demo(int arg);

}
