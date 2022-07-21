package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.fab.FabStdTester;
import net.bodz.violet.fab.FabStdTesterSamples;

public class FabStdTesterMapperTest
        extends AbstractMapperTest<FabStdTester, FabStdTesterMask, FabStdTesterMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public FabStdTester buildSample() {
        return FabStdTesterSamples.build();
    }

}
