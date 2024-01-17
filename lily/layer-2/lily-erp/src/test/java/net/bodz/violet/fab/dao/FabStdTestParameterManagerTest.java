package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.fab.FabStdTestParameter;
import net.bodz.violet.fab.FabStdTestParameterSamples;

public class FabStdTestParameterManagerTest
        extends AbstractManagerTest<FabStdTestParameter, FabStdTestParameterMapper, FabStdTestParameterManager> {

    @Override
    public FabStdTestParameter buildSample()
            throws Exception {
        FabStdTestParameterSamples a = new FabStdTestParameterSamples();
        return a.buildWired(tables);
    }

}
