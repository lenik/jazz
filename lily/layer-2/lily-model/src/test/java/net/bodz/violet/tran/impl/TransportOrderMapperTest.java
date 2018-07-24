package net.bodz.violet.tran.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.tran.TransportOrder;
import net.bodz.violet.tran.TransportOrderSamples;

public class TransportOrderMapperTest
        extends AbstractMapperTest<TransportOrder, TransportOrderMask, TransportOrderMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TransportOrder buildSample() {
        return TransportOrderSamples.build();
    }

}
