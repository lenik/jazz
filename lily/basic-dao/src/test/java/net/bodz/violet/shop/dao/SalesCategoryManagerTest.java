package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.shop.SalesCategory;
import net.bodz.violet.shop.SalesCategorySamples;

public class SalesCategoryManagerTest
        extends AbstractManagerTest<SalesCategory, SalesCategoryMapper, SalesCategoryManager> {

    @Override
    public SalesCategory buildSample()
            throws Exception {
        SalesCategorySamples a = new SalesCategorySamples();
        return a.buildWired(tables);
    }

}
