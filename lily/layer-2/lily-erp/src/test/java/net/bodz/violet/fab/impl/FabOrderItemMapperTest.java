package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.fab.FabOrderItem;
import net.bodz.violet.fab.FabOrderItemSamples;

public class FabOrderItemMapperTest
        extends AbstractMapperTest<FabOrderItem, FabOrderItemMask, FabOrderItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public FabOrderItem buildSample() {
        return FabOrderItemSamples.build();
    }

}
