package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.shop.SalesOrderItem;
import net.bodz.violet.shop.SalesOrderItemSamples;

public class SalesOrderItemManagerTest
        extends AbstractManagerTest<SalesOrderItem, SalesOrderItemMapper, SalesOrderItemManager> {

    @Override
    public SalesOrderItem buildSample()
            throws Exception {
        SalesOrderItemSamples a = new SalesOrderItemSamples();
        return a.buildWired(tables);
    }

}
