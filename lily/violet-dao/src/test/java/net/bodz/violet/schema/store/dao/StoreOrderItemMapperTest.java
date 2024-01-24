package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.store.StoreOrderItem;
import net.bodz.violet.schema.store.StoreOrderItemSamples;

public class StoreOrderItemMapperTest
        extends AbstractTableTest<StoreOrderItem, StoreOrderItemMapper> {

    @Override
    public StoreOrderItem buildSample()
            throws Exception {
        StoreOrderItemSamples a = new StoreOrderItemSamples();
        return a.buildWired(tables);
    }

}
