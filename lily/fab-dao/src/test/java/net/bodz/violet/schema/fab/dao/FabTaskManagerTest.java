package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabTask;
import net.bodz.violet.schema.fab.FabTaskSamples;

public class FabTaskManagerTest
        extends AbstractManagerTest<FabTask, FabTaskMapper, FabTaskManager> {

    @Override
    public FabTask buildSample()
            throws Exception {
        FabTaskSamples a = new FabTaskSamples();
        return a.buildWired(tables);
    }

}
