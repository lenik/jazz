package net.bodz.bas.http.thread;

import org.junit.Assert;

public class AsyncInvocationHandlerTest
        extends Assert {

    public void test1() {
        SampleImpl impl = new SampleImpl();
        Object async = AsyncScheduler.toAsync(impl, new IAsyncCallback() {

            @Override
            public void onReturn(Object retval) {
                System.out.println("Returned: " + retval);
            }

            @Override
            public void onInterrupt(InterruptedException e) {
                System.out.println("Interrupted");
            }

            @Override
            public void onException(Throwable exception) {
                System.out.println("exception: " + exception);
            }
        });
        System.out.println("async ret: " + async);
    }

    public static void main(String[] args) {
        new AsyncInvocationHandlerTest().test1();
    }

}
