package net.bodz.violet.schema.tran.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.tran.TransportOrderItem;
import net.bodz.violet.schema.tran.TransportOrderItemSamples;

public class TransportOrderItemMapperTest
        extends AbstractTableTest<TransportOrderItem, TransportOrderItemMapper> {

    @Override
    public TransportOrderItem buildSample()
            throws Exception {
        TransportOrderItemSamples a = new TransportOrderItemSamples();
        return a.buildWired(tables);
    }

}
