package net.bodz.bas.unitperf.duration;

import org.junit.Test;

public class BenchmarkTest {

    @Test
    public void test1()
            throws Exception {
        Benchmark b = new Benchmark();
        double sin = b.measureAverageDuration(new IBenchmarkTarget() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    Math.sin(1.23);
            }
        });
        System.out.println(sin + " ns/op");
    }

}
