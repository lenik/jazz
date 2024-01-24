package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.shop.SalesOrderItem;
import net.bodz.violet.schema.shop.SalesOrderItemSamples;

public class SalesOrderItemManagerTest
        extends AbstractManagerTest<SalesOrderItem, SalesOrderItemMapper, SalesOrderItemManager> {

    @Override
    public SalesOrderItem buildSample()
            throws Exception {
        SalesOrderItemSamples a = new SalesOrderItemSamples();
        return a.buildWired(tables);
    }

}
