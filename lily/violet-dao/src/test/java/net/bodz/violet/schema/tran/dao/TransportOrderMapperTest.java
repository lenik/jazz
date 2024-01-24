package net.bodz.violet.schema.tran.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.tran.TransportOrder;
import net.bodz.violet.schema.tran.TransportOrderSamples;

public class TransportOrderMapperTest
        extends AbstractTableTest<TransportOrder, TransportOrderMapper> {

    @Override
    public TransportOrder buildSample()
            throws Exception {
        TransportOrderSamples a = new TransportOrderSamples();
        return a.buildWired(tables);
    }

}
