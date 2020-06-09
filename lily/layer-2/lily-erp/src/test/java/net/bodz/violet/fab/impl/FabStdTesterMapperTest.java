package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.fab.FabStdTester;
import net.bodz.violet.fab.FabStdTesterSamples;

public class FabStdTesterMapperTest
        extends AbstractMapperTest<FabStdTester, FabStdTesterMask, FabStdTesterMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public FabStdTester buildSample() {
        return FabStdTesterSamples.build();
    }

}
