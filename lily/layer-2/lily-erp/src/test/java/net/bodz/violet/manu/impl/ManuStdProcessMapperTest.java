package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuStdProcess;
import net.bodz.violet.manu.ManuStdProcessSamples;

public class ManuStdProcessMapperTest
        extends AbstractMapperTest<ManuStdProcess, ManuStdProcessMask, ManuStdProcessMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuStdProcess buildSample() {
        return ManuStdProcessSamples.build();
    }

}
