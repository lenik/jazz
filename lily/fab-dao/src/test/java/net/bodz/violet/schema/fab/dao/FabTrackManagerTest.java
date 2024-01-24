package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabTrack;
import net.bodz.violet.schema.fab.FabTrackSamples;

public class FabTrackManagerTest
        extends AbstractManagerTest<FabTrack, FabTrackMapper, FabTrackManager> {

    @Override
    public FabTrack buildSample()
            throws Exception {
        FabTrackSamples a = new FabTrackSamples();
        return a.buildWired(tables);
    }

}
