package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabTrackTestParameter;
import net.bodz.violet.schema.fab.FabTrackTestParameterSamples;

public class FabTrackTestParameterManagerTest
        extends AbstractManagerTest<FabTrackTestParameter, FabTrackTestParameterMapper, FabTrackTestParameterManager> {

    @Override
    public FabTrackTestParameter buildSample()
            throws Exception {
        FabTrackTestParameterSamples a = new FabTrackTestParameterSamples();
        return a.buildWired(tables);
    }

}
