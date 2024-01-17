package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.store.StoreOrder;
import net.bodz.violet.store.StoreOrderSamples;

public class StoreOrderManagerTest
        extends AbstractManagerTest<StoreOrder, StoreOrderMapper, StoreOrderManager> {

    @Override
    public StoreOrder buildSample()
            throws Exception {
        StoreOrderSamples a = new StoreOrderSamples();
        return a.buildWired(tables);
    }

}
