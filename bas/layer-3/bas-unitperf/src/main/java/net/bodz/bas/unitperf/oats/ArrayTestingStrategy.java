package net.bodz.bas.unitperf.oats;

import net.bodz.bas.collection.iterator.ImmediateIterator;

public interface ArrayTestingStrategy {

    ImmediateIterator<int[], RuntimeException> getVectors(int... componentLevels);

}
