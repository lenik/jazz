package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabTask;
import net.bodz.violet.fab.FabTaskSamples;

public class FabTaskMapperTest
        extends AbstractTableTest<FabTask, FabTaskMapper> {

    @Override
    public FabTask buildSample()
            throws Exception {
        FabTaskSamples a = new FabTaskSamples();
        return a.buildWired(tables);
    }

}
