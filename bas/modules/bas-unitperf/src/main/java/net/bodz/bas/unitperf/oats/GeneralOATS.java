package net.bodz.bas.unitperf.oats;

import net.bodz.bas.collection.iterator.ImmediateIterator;

public class GeneralOATS
        implements ArrayTestingStrategy {

    private final int correlation;

    public GeneralOATS(int correlation) {
        if (correlation < 1)
            throw new IllegalArgumentException("correlation < 1: " + correlation);
        this.correlation = correlation;
    }

    @Override
    public ImmediateIterator<int[], RuntimeException> getVectors(int... columnLevels) {
        if (columnLevels == null)
            throw new NullPointerException("componentLevels");
        int cc = columnLevels.length;

        IntArrayList[] columns = new IntArrayList[cc];
        for (int i = 0; i < cc; i++)
            columns[i] = new IntArrayList();
        return null;
    }

}
