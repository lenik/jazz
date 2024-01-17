package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabTrack;
import net.bodz.violet.fab.FabTrackSamples;

public class FabTrackMapperTest
        extends AbstractTableTest<FabTrack, FabTrackMapper> {

    @Override
    public FabTrack buildSample()
            throws Exception {
        FabTrackSamples a = new FabTrackSamples();
        return a.buildWired(tables);
    }

}
