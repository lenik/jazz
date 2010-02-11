package net.bodz.bas.unitperf.duration;

public interface IBenchmarkTarget {

    void run()
            throws BenchmarkTargetException;

}
