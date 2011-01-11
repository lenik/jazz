package net.bodz.bas.unitperf.duration;

/**
 * @test {@link BenchmarkTest}
 */
public class Benchmark {

    long nanoTimePrecision;

    private int repeatCount;

    private long skeletonCost = -1;

    public Benchmark() {
        this(1000000);
    }

    public Benchmark(int repeatCount) {
        if (repeatCount <= 0)
            throw new IllegalArgumentException("repeatCount must be positive: " + repeatCount);
        this.repeatCount = repeatCount;

        // 1ms by default.
        nanoTimePrecision = 1000;
    }

    public double measureAverageDuration(IBenchmarkTarget target)
            throws BenchmarkTargetException {
        if (skeletonCost == -1) {
            IBenchmarkTarget nullTarget = new IBenchmarkTarget() {
                @Override
                public void run()
                        throws BenchmarkTargetException {
                }
            };
            long start = System.nanoTime();
            for (int i = 0; i < repeatCount; i++)
                nullTarget.run();
            skeletonCost = System.nanoTime() - start;
            // System.out.println("Skeleteon cost: " + skeletonCost);
        }
        long start = System.nanoTime();
        for (int i = 0; i < repeatCount; i++)
            target.run();
        long grossCost = System.nanoTime() - start;
        // System.out.println("Target cost: " + grossCost);
        long netCost = grossCost - skeletonCost;
        return ((double) netCost) / repeatCount;
    }

}
