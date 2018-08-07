package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuTrack;
import net.bodz.violet.manu.ManuTrackSamples;

public class ManuTrackMapperTest
        extends AbstractMapperTest<ManuTrack, ManuTrackMask, ManuTrackMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuTrack buildSample() {
        return ManuTrackSamples.build();
    }

}
