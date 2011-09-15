package net.bodz.bas.unitperf.oats;

import net.bodz.bas.util.iter.Mitorx;

public interface ArrayTestingStrategy {

    Mitorx<int[], RuntimeException> getVectors(int... componentLevels);

}
