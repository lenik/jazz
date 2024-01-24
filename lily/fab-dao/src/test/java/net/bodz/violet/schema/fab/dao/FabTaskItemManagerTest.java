package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabTaskItem;
import net.bodz.violet.schema.fab.FabTaskItemSamples;

public class FabTaskItemManagerTest
        extends AbstractManagerTest<FabTaskItem, FabTaskItemMapper, FabTaskItemManager> {

    @Override
    public FabTaskItem buildSample()
            throws Exception {
        FabTaskItemSamples a = new FabTaskItemSamples();
        return a.buildWired(tables);
    }

}
