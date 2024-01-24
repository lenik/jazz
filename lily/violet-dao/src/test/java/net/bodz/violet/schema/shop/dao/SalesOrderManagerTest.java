package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.shop.SalesOrder;
import net.bodz.violet.schema.shop.SalesOrderSamples;

public class SalesOrderManagerTest
        extends AbstractManagerTest<SalesOrder, SalesOrderMapper, SalesOrderManager> {

    @Override
    public SalesOrder buildSample()
            throws Exception {
        SalesOrderSamples a = new SalesOrderSamples();
        return a.buildWired(tables);
    }

}
