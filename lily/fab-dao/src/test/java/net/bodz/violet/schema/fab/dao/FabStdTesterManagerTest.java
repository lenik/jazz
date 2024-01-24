package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabStdTester;
import net.bodz.violet.schema.fab.FabStdTesterSamples;

public class FabStdTesterManagerTest
        extends AbstractManagerTest<FabStdTester, FabStdTesterMapper, FabStdTesterManager> {

    @Override
    public FabStdTester buildSample()
            throws Exception {
        FabStdTesterSamples a = new FabStdTesterSamples();
        return a.buildWired(tables);
    }

}
