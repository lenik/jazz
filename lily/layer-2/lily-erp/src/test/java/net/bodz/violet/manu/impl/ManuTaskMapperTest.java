package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuTask;
import net.bodz.violet.manu.ManuTaskSamples;

public class ManuTaskMapperTest
        extends AbstractMapperTest<ManuTask, ManuTaskMask, ManuTaskMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuTask buildSample() {
        return ManuTaskSamples.build();
    }

}
