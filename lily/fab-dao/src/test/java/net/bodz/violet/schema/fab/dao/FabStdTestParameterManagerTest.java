package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabStdTestParameter;
import net.bodz.violet.schema.fab.FabStdTestParameterSamples;

public class FabStdTestParameterManagerTest
        extends AbstractManagerTest<FabStdTestParameter, FabStdTestParameterMapper, FabStdTestParameterManager> {

    @Override
    public FabStdTestParameter buildSample()
            throws Exception {
        FabStdTestParameterSamples a = new FabStdTestParameterSamples();
        return a.buildWired(tables);
    }

}
