package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuFn;
import net.bodz.violet.manu.ManuFnSamples;

public class ManuFnMapperTest
        extends AbstractMapperTest<ManuFn, ManuFnMask, ManuFnMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuFn buildSample() {
        return ManuFnSamples.build();
    }

}
