package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabTrack;
import net.bodz.violet.fab.FabTrackSamples;

public class FabTrackMapperTest
        extends AbstractTableTest<FabTrack, FabTrackMask, FabTrackMapper> {

    @Override
    public FabTrack buildSample() {
        return FabTrackSamples.build();
    }

}
