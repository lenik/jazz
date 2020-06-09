package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.fab.FabStdTest;
import net.bodz.violet.fab.FabStdTestSamples;

public class FabStdTestMapperTest
        extends AbstractMapperTest<FabStdTest, FabStdTestMask, FabStdTestMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public FabStdTest buildSample() {
        return FabStdTestSamples.build();
    }

}
