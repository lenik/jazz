package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuStdTester;
import net.bodz.violet.manu.ManuStdTesterSamples;

public class ManuStdTesterMapperTest
        extends AbstractMapperTest<ManuStdTester, ManuStdTesterMask, ManuStdTesterMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuStdTester buildSample() {
        return ManuStdTesterSamples.build();
    }

}
