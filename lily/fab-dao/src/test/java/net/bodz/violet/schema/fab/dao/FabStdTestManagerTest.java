package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabStdTest;
import net.bodz.violet.schema.fab.FabStdTestSamples;

public class FabStdTestManagerTest
        extends AbstractManagerTest<FabStdTest, FabStdTestMapper, FabStdTestManager> {

    @Override
    public FabStdTest buildSample()
            throws Exception {
        FabStdTestSamples a = new FabStdTestSamples();
        return a.buildWired(tables);
    }

}
