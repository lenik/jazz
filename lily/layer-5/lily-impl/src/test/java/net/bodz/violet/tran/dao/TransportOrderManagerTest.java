package net.bodz.violet.tran.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.tran.TransportOrder;
import net.bodz.violet.tran.TransportOrderSamples;

public class TransportOrderManagerTest
        extends AbstractManagerTest<TransportOrder, TransportOrderMapper, TransportOrderManager> {

    @Override
    public TransportOrder buildSample()
            throws Exception {
        TransportOrderSamples a = new TransportOrderSamples();
        return a.buildWired(tables);
    }

}
