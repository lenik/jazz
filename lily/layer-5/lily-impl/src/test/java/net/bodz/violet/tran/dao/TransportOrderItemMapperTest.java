package net.bodz.violet.tran.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.tran.TransportOrderItem;
import net.bodz.violet.tran.TransportOrderItemSamples;

public class TransportOrderItemMapperTest
        extends AbstractTableTest<TransportOrderItem, TransportOrderItemMapper> {

    @Override
    public TransportOrderItem buildSample()
            throws Exception {
        TransportOrderItemSamples a = new TransportOrderItemSamples();
        return a.buildWired(tables);
    }

}
