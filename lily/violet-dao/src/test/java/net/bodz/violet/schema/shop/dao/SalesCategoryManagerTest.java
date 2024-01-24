package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.shop.SalesCategory;
import net.bodz.violet.schema.shop.SalesCategorySamples;

public class SalesCategoryManagerTest
        extends AbstractManagerTest<SalesCategory, SalesCategoryMapper, SalesCategoryManager> {

    @Override
    public SalesCategory buildSample()
            throws Exception {
        SalesCategorySamples a = new SalesCategorySamples();
        return a.buildWired(tables);
    }

}
