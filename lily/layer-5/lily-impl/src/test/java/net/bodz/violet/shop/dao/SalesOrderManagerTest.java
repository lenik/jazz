package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.shop.SalesOrderSamples;

public class SalesOrderManagerTest
        extends AbstractManagerTest<SalesOrder, SalesOrderMapper, SalesOrderManager> {

    @Override
    public SalesOrder buildSample()
            throws Exception {
        SalesOrderSamples a = new SalesOrderSamples();
        return a.buildWired(tables);
    }

}
