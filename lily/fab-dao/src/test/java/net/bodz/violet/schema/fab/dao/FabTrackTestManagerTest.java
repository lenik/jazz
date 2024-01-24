package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabTrackTest;
import net.bodz.violet.schema.fab.FabTrackTestSamples;

public class FabTrackTestManagerTest
        extends AbstractManagerTest<FabTrackTest, FabTrackTestMapper, FabTrackTestManager> {

    @Override
    public FabTrackTest buildSample()
            throws Exception {
        FabTrackTestSamples a = new FabTrackTestSamples();
        return a.buildWired(tables);
    }

}
