package net.bodz.violet.tran.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.tran.TransportOrder;
import net.bodz.violet.tran.TransportOrderSamples;

public class TransportOrderMapperTest
        extends AbstractTableTest<TransportOrder, TransportOrderMapper> {

    @Override
    public TransportOrder buildSample()
            throws Exception {
        TransportOrderSamples a = new TransportOrderSamples();
        return a.buildWired(tables);
    }

}
