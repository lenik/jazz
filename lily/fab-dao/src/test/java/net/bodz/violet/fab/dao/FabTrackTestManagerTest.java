package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.fab.FabTrackTest;
import net.bodz.violet.fab.FabTrackTestSamples;

public class FabTrackTestManagerTest
        extends AbstractManagerTest<FabTrackTest, FabTrackTestMapper, FabTrackTestManager> {

    @Override
    public FabTrackTest buildSample()
            throws Exception {
        FabTrackTestSamples a = new FabTrackTestSamples();
        return a.buildWired(tables);
    }

}
