package net.bodz.bas.c.reflect.proxy;

import java.lang.reflect.Proxy;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.util.test.ITimingTarget;
import net.bodz.bas.util.test.MultipassTimer;

public class NullInvocationHandlerTest
        extends Assert {

    static interface IDog {
        String bark();
    }

    @Test
    public void testPerf()
            throws Exception {

        final IDog directRun = new IDog() {
            @Override
            public String bark() {
                return "hello";
            }
        };
        final IDog indirectRun = (IDog) Proxy.newProxyInstance(getClass().getClassLoader(), //
                new Class<?>[] { IDog.class }, NullInvocationHandler.INSTANCE);

        MultipassTimer timer = new MultipassTimer();

        double directCost = timer.measureAverageDuration(new ITimingTarget() {

            @Override
            public void run() {
                directRun.bark();
                // System.identityHashCode(mesg);
            }

        });

        double indirectCost = timer.measureAverageDuration(new ITimingTarget() {

            @Override
            public void run() {
                indirectRun.bark();
                // System.identityHashCode(mesg);
            }
        });

        System.out.println("Direct cost: " + directCost);
        System.out.println("Indirect cost: " + indirectCost);
    }
}
