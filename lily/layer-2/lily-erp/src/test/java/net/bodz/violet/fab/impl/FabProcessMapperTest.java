package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.fab.FabProcess;
import net.bodz.violet.fab.FabProcessSamples;

public class FabProcessMapperTest
        extends AbstractMapperTest<FabProcess, FabProcessMask, FabProcessMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public FabProcess buildSample() {
        return FabProcessSamples.build();
    }

}
