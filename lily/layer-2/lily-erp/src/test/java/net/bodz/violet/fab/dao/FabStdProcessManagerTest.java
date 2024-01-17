package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.fab.FabStdProcess;
import net.bodz.violet.fab.FabStdProcessSamples;

public class FabStdProcessManagerTest
        extends AbstractManagerTest<FabStdProcess, FabStdProcessMapper, FabStdProcessManager> {

    @Override
    public FabStdProcess buildSample()
            throws Exception {
        FabStdProcessSamples a = new FabStdProcessSamples();
        return a.buildWired(tables);
    }

}
