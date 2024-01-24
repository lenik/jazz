package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.shop.SalesCategory;
import net.bodz.violet.schema.shop.SalesCategorySamples;

public class SalesCategoryMapperTest
        extends AbstractTableTest<SalesCategory, SalesCategoryMapper> {

    @Override
    public SalesCategory buildSample()
            throws Exception {
        SalesCategorySamples a = new SalesCategorySamples();
        return a.buildWired(tables);
    }

}
