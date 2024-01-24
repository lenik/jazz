package net.bodz.violet.schema.tran.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.tran.TransportOrder;
import net.bodz.violet.schema.tran.TransportOrderSamples;

public class TransportOrderManagerTest
        extends AbstractManagerTest<TransportOrder, TransportOrderMapper, TransportOrderManager> {

    @Override
    public TransportOrder buildSample()
            throws Exception {
        TransportOrderSamples a = new TransportOrderSamples();
        return a.buildWired(tables);
    }

}
