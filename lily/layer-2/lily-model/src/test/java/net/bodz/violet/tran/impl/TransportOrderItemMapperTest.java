package net.bodz.violet.tran.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.tran.TransportOrderItem;
import net.bodz.violet.tran.TransportOrderItemSamples;

public class TransportOrderItemMapperTest
        extends AbstractMapperTest<TransportOrderItem, TransportOrderItemMask, TransportOrderItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TransportOrderItem buildSample() {
        return TransportOrderItemSamples.build();
    }

}
