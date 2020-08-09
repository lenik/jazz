package net.bodz.bas.http.thread;

public class AsyncScheduler {

    public static Object toAsync(Object obj, IAsyncCallback callback) {
        return AsyncInvocationHandler.newInstance(obj, callback);
    }

}
