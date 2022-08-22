package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.SalesCategory;
import net.bodz.violet.shop.SalesCategorySamples;

public class SalesCategoryMapperTest
        extends AbstractTableTest<SalesCategory, SalesCategoryMask, SalesCategoryMapper> {

    @Override
    public SalesCategory buildSample() {
        return SalesCategorySamples.build();
    }

}
