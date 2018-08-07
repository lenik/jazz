package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuStdProcessInput;
import net.bodz.violet.manu.ManuStdProcessInputSamples;

public class ManuStdProcessInputMapperTest
        extends AbstractMapperTest<ManuStdProcessInput, ManuStdProcessInputMask, ManuStdProcessInputMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuStdProcessInput buildSample() {
        return ManuStdProcessInputSamples.build();
    }

}
