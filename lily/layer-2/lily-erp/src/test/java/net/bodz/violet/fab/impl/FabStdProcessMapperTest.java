package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.fab.FabStdProcess;
import net.bodz.violet.fab.FabStdProcessSamples;

public class FabStdProcessMapperTest
        extends AbstractMapperTest<FabStdProcess, FabStdProcessMask, FabStdProcessMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public FabStdProcess buildSample() {
        return FabStdProcessSamples.build();
    }

}
