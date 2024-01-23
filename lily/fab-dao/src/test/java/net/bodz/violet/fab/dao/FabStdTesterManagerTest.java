package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.fab.FabStdTester;
import net.bodz.violet.fab.FabStdTesterSamples;

public class FabStdTesterManagerTest
        extends AbstractManagerTest<FabStdTester, FabStdTesterMapper, FabStdTesterManager> {

    @Override
    public FabStdTester buildSample()
            throws Exception {
        FabStdTesterSamples a = new FabStdTesterSamples();
        return a.buildWired(tables);
    }

}
