package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.art.StdParameter;
import net.bodz.violet.schema.art.StdParameterSamples;

public class StdParameterManagerTest
        extends AbstractManagerTest<StdParameter, StdParameterMapper, StdParameterManager> {

    @Override
    public StdParameter buildSample()
            throws Exception {
        StdParameterSamples a = new StdParameterSamples();
        return a.buildWired(tables);
    }

}
