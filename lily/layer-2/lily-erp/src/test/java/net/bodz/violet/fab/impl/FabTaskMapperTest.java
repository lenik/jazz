package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.fab.FabTask;
import net.bodz.violet.fab.FabTaskSamples;

public class FabTaskMapperTest
        extends AbstractMapperTest<FabTask, FabTaskMask, FabTaskMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public FabTask buildSample() {
        return FabTaskSamples.build();
    }

}
