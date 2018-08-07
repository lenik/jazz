package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuTrackTest;
import net.bodz.violet.manu.ManuTrackTestSamples;

public class ManuTrackTestMapperTest
        extends AbstractMapperTest<ManuTrackTest, ManuTrackTestMask, ManuTrackTestMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuTrackTest buildSample() {
        return ManuTrackTestSamples.build();
    }

}
