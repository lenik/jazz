package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.fab.FabOrderItem;
import net.bodz.violet.fab.FabOrderItemSamples;

public class FabOrderItemManagerTest
        extends AbstractManagerTest<FabOrderItem, FabOrderItemMapper, FabOrderItemManager> {

    @Override
    public FabOrderItem buildSample()
            throws Exception {
        FabOrderItemSamples a = new FabOrderItemSamples();
        return a.buildWired(tables);
    }

}
