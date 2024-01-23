package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.fab.FabProcess;
import net.bodz.violet.fab.FabProcessSamples;

public class FabProcessManagerTest
        extends AbstractManagerTest<FabProcess, FabProcessMapper, FabProcessManager> {

    @Override
    public FabProcess buildSample()
            throws Exception {
        FabProcessSamples a = new FabProcessSamples();
        return a.buildWired(tables);
    }

}
