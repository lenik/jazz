package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.fab.FabTrack;
import net.bodz.violet.fab.FabTrackSamples;

public class FabTrackManagerTest
        extends AbstractManagerTest<FabTrack, FabTrackMapper, FabTrackManager> {

    @Override
    public FabTrack buildSample()
            throws Exception {
        FabTrackSamples a = new FabTrackSamples();
        return a.buildWired(tables);
    }

}
