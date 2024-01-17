package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.SalesOrderItem;
import net.bodz.violet.shop.SalesOrderItemSamples;

public class SalesOrderItemMapperTest
        extends AbstractTableTest<SalesOrderItem, SalesOrderItemMapper> {

    @Override
    public SalesOrderItem buildSample()
            throws Exception {
        SalesOrderItemSamples a = new SalesOrderItemSamples();
        return a.buildWired(tables);
    }

}
