package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.fab.FabTask;
import net.bodz.violet.fab.FabTaskSamples;

public class FabTaskMapperTest
        extends AbstractMapperTest<FabTask, FabTaskMask, FabTaskMapper> {

    @Override
    public FabTask buildSample() {
        return FabTaskSamples.build();
    }

}
