package net.bodz.bas.unitperf.oats;

import net.bodz.bas.t.iterator.immed.Mitorx;

public interface ArrayTestingStrategy {

    Mitorx<int[], RuntimeException> getVectors(int... componentLevels);

}
