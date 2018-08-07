package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuProcess;
import net.bodz.violet.manu.ManuProcessSamples;

public class ManuProcessMapperTest
        extends AbstractMapperTest<ManuProcess, ManuProcessMask, ManuProcessMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuProcess buildSample() {
        return ManuProcessSamples.build();
    }

}
