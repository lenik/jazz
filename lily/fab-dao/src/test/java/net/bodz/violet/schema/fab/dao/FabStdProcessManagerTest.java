package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabStdProcess;
import net.bodz.violet.schema.fab.FabStdProcessSamples;

public class FabStdProcessManagerTest
        extends AbstractManagerTest<FabStdProcess, FabStdProcessMapper, FabStdProcessManager> {

    @Override
    public FabStdProcess buildSample()
            throws Exception {
        FabStdProcessSamples a = new FabStdProcessSamples();
        return a.buildWired(tables);
    }

}
