package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.store.StoreOrderItem;
import net.bodz.violet.store.StoreOrderItemSamples;

public class StoreOrderItemManagerTest
        extends AbstractManagerTest<StoreOrderItem, StoreOrderItemMapper, StoreOrderItemManager> {

    @Override
    public StoreOrderItem buildSample()
            throws Exception {
        StoreOrderItemSamples a = new StoreOrderItemSamples();
        return a.buildWired(tables);
    }

}
