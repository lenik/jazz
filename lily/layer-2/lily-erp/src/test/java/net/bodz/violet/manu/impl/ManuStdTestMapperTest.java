package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuStdTest;
import net.bodz.violet.manu.ManuStdTestSamples;

public class ManuStdTestMapperTest
        extends AbstractMapperTest<ManuStdTest, ManuStdTestMask, ManuStdTestMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuStdTest buildSample() {
        return ManuStdTestSamples.build();
    }

}
