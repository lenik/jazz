package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuStdTestParameter;
import net.bodz.violet.manu.ManuStdTestParameterSamples;

public class ManuStdTestParameterMapperTest
        extends AbstractMapperTest<ManuStdTestParameter, ManuStdTestParameterMask, ManuStdTestParameterMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuStdTestParameter buildSample() {
        return ManuStdTestParameterSamples.build();
    }

}
