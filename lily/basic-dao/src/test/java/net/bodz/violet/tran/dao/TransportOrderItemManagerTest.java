package net.bodz.violet.tran.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.tran.TransportOrderItem;
import net.bodz.violet.tran.TransportOrderItemSamples;

public class TransportOrderItemManagerTest
        extends AbstractManagerTest<TransportOrderItem, TransportOrderItemMapper, TransportOrderItemManager> {

    @Override
    public TransportOrderItem buildSample()
            throws Exception {
        TransportOrderItemSamples a = new TransportOrderItemSamples();
        return a.buildWired(tables);
    }

}
