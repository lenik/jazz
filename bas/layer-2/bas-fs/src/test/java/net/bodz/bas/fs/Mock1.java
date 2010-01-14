package net.bodz.bas.fs;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

interface Subscriber {

    int receive(String mesg);

}

public class Mock1 {

    Mockery context = new Mockery();

    @Test
    public void test1() {

        final Subscriber subscriber = context.mock(Subscriber.class, "s1");

        context.checking(new Expectations() {
            {
                oneOf(subscriber).receive("hello");
                will(returnValue(100));

                oneOf(subscriber).receive("world");

                will(returnValue(200));
            }
        });

        int worldval = subscriber.receive("world");
        System.out.println("world: " + worldval);

        int helloval = subscriber.receive("hello");
        System.out.println("hello: " + helloval);

        context.assertIsSatisfied();
    }

}
