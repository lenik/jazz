package net.bodz.violet.store.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.store.StoreOrderItem;
import net.bodz.violet.store.StoreOrderItemSamples;

public class StoreOrderItemMapperTest
        extends AbstractMapperTest<StoreOrderItem, StoreOrderItemMask, StoreOrderItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public StoreOrderItem buildSample() {
        return StoreOrderItemSamples.build();
    }

}
