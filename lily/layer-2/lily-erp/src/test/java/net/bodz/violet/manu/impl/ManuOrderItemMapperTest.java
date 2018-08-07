package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuOrderItem;
import net.bodz.violet.manu.ManuOrderItemSamples;

public class ManuOrderItemMapperTest
        extends AbstractMapperTest<ManuOrderItem, ManuOrderItemMask, ManuOrderItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuOrderItem buildSample() {
        return ManuOrderItemSamples.build();
    }

}
