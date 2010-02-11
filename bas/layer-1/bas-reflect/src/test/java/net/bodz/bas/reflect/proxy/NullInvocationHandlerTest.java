package net.bodz.bas.reflect.proxy;

import java.lang.reflect.Proxy;

import net.bodz.bas.unitperf.duration.Benchmark;
import net.bodz.bas.unitperf.duration.IBenchmarkTarget;

import org.junit.Test;

public class NullInvocationHandlerTest {

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
                new Class<?>[] { IDog.class }, NullInvocationHandler.getInstance());

        Benchmark benchmark = new Benchmark();

        double directCost = benchmark.measureAverageDuration(new IBenchmarkTarget() {

            @Override
            public void run() {
                directRun.bark();
                // System.identityHashCode(mesg);
            }

        });

        double indirectCost = benchmark.measureAverageDuration(new IBenchmarkTarget() {

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
