package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.fab.FabTrack;
import net.bodz.violet.fab.FabTrackSamples;

public class FabTrackMapperTest
        extends AbstractMapperTest<FabTrack, FabTrackMask, FabTrackMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public FabTrack buildSample() {
        return FabTrackSamples.build();
    }

}
