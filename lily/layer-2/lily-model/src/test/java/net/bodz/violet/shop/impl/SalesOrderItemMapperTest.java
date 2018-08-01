package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.SalesOrderItem;
import net.bodz.violet.shop.SalesOrderItemSamples;

public class SalesOrderItemMapperTest
        extends AbstractMapperTest<SalesOrderItem, SalesOrderItemMask, SalesOrderItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public SalesOrderItem buildSample() {
        return SalesOrderItemSamples.build();
    }

}
