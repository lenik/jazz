package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.shop.SalesCategory;
import net.bodz.violet.shop.SalesCategorySamples;

public class SalesCategoryMapperTest
        extends AbstractMapperTest<SalesCategory, SalesCategoryMask, SalesCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public SalesCategory buildSample() {
        return SalesCategorySamples.build();
    }

}
