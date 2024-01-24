package net.bodz.violet.schema.tran.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.tran.TransportOrderItem;
import net.bodz.violet.schema.tran.TransportOrderItemSamples;

public class TransportOrderItemManagerTest
        extends AbstractManagerTest<TransportOrderItem, TransportOrderItemMapper, TransportOrderItemManager> {

    @Override
    public TransportOrderItem buildSample()
            throws Exception {
        TransportOrderItemSamples a = new TransportOrderItemSamples();
        return a.buildWired(tables);
    }

}
