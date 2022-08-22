package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabTask;
import net.bodz.violet.fab.FabTaskSamples;

public class FabTaskMapperTest
        extends AbstractTableTest<FabTask, FabTaskMask, FabTaskMapper> {

    @Override
    public FabTask buildSample() {
        return FabTaskSamples.build();
    }

}
