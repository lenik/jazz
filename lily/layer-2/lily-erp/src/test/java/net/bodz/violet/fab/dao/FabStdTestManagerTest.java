package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.fab.FabStdTest;
import net.bodz.violet.fab.FabStdTestSamples;

public class FabStdTestManagerTest
        extends AbstractManagerTest<FabStdTest, FabStdTestMapper, FabStdTestManager> {

    @Override
    public FabStdTest buildSample()
            throws Exception {
        FabStdTestSamples a = new FabStdTestSamples();
        return a.buildWired(tables);
    }

}
