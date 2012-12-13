package net.bodz.bas.tool.test;

public class MultipassTimer {

    long nanoTimePrecision;

    private int passCount;

    private long skeletonCost = -1;

    public MultipassTimer() {
        this(1000000);
    }

    public MultipassTimer(int passCount) {
        if (passCount <= 0)
            throw new IllegalArgumentException("passCount must be positive: " + passCount);
        this.passCount = passCount;

        // 1ms by default.
        nanoTimePrecision = 1000;
    }

    public double measureAverageDuration(ITimingTarget target)
            throws TimingTargetException {
        if (skeletonCost == -1) {
            ITimingTarget nullTarget = new ITimingTarget() {
                @Override
                public void run()
                        throws TimingTargetException {
                }
            };
            long start = System.nanoTime();
            for (int i = 0; i < passCount; i++)
                nullTarget.run();
            skeletonCost = System.nanoTime() - start;
            // System.out.println("Skeleteon cost: " + skeletonCost);
        }
        long start = System.nanoTime();
        for (int i = 0; i < passCount; i++)
            target.run();
        long grossCost = System.nanoTime() - start;
        // System.out.println("Target cost: " + grossCost);
        long netCost = grossCost - skeletonCost;
        return ((double) netCost) / passCount;
    }

}
