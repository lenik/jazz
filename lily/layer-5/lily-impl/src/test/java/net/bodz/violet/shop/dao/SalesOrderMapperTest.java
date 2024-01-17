package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.shop.SalesOrderSamples;

public class SalesOrderMapperTest
        extends AbstractTableTest<SalesOrder, SalesOrderMapper> {

    @Override
    public SalesOrder buildSample()
            throws Exception {
        SalesOrderSamples a = new SalesOrderSamples();
        return a.buildWired(tables);
    }

}
