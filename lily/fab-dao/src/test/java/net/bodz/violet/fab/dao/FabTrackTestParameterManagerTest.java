package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.fab.FabTrackTestParameter;
import net.bodz.violet.fab.FabTrackTestParameterSamples;

public class FabTrackTestParameterManagerTest
        extends AbstractManagerTest<FabTrackTestParameter, FabTrackTestParameterMapper, FabTrackTestParameterManager> {

    @Override
    public FabTrackTestParameter buildSample()
            throws Exception {
        FabTrackTestParameterSamples a = new FabTrackTestParameterSamples();
        return a.buildWired(tables);
    }

}
