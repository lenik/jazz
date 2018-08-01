package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.shop.SalesOrderSamples;

public class SalesOrderMapperTest
        extends AbstractMapperTest<SalesOrder, SalesOrderMask, SalesOrderMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public SalesOrder buildSample() {
        return SalesOrderSamples.build();
    }

}
