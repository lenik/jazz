package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.SalesCategory;
import net.bodz.violet.shop.SalesCategorySamples;

public class SalesCategoryMapperTest
        extends AbstractTableTest<SalesCategory, SalesCategoryMapper> {

    @Override
    public SalesCategory buildSample()
            throws Exception {
        SalesCategorySamples a = new SalesCategorySamples();
        return a.buildWired(tables);
    }

}
