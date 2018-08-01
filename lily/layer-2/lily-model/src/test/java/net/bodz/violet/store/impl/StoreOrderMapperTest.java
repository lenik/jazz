package net.bodz.violet.store.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.store.StoreOrder;
import net.bodz.violet.store.StoreOrderSamples;

public class StoreOrderMapperTest
        extends AbstractMapperTest<StoreOrder, StoreOrderMask, StoreOrderMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public StoreOrder buildSample() {
        return StoreOrderSamples.build();
    }

}
