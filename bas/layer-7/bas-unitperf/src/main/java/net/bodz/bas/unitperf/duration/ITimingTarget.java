package net.bodz.bas.unitperf.duration;

import net.bodz.bas.c1.timing.TimingTargetException;

public interface ITimingTarget {

    void run()
            throws TimingTargetException;

}
