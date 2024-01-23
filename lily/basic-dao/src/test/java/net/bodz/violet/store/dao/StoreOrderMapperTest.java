package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.StoreOrder;
import net.bodz.violet.store.StoreOrderSamples;

public class StoreOrderMapperTest
        extends AbstractTableTest<StoreOrder, StoreOrderMapper> {

    @Override
    public StoreOrder buildSample()
            throws Exception {
        StoreOrderSamples a = new StoreOrderSamples();
        return a.buildWired(tables);
    }

}
