package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.fab.FabStdProcess;
import net.bodz.violet.fab.FabStdProcessSamples;

public class FabStdProcessMapperTest
        extends AbstractMapperTest<FabStdProcess, FabStdProcessMask, FabStdProcessMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public FabStdProcess buildSample() {
        return FabStdProcessSamples.build();
    }

}
