package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.store.StoreOrder;
import net.bodz.violet.schema.store.StoreOrderSamples;

public class StoreOrderMapperTest
        extends AbstractTableTest<StoreOrder, StoreOrderMapper> {

    @Override
    public StoreOrder buildSample()
            throws Exception {
        StoreOrderSamples a = new StoreOrderSamples();
        return a.buildWired(tables);
    }

}
