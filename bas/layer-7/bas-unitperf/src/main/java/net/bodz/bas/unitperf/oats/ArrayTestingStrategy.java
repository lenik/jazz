package net.bodz.bas.unitperf.oats;

import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public interface ArrayTestingStrategy {

    ImmediateIteratorX<int[], RuntimeException> getVectors(int... componentLevels);

}
