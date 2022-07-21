package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.fab.FabTrackTest;
import net.bodz.violet.fab.FabTrackTestSamples;

public class FabTrackTestMapperTest
        extends AbstractMapperTest<FabTrackTest, FabTrackTestMask, FabTrackTestMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public FabTrackTest buildSample() {
        return FabTrackTestSamples.build();
    }

}
