package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.shop.ShopCategory;
import net.bodz.violet.shop.ShopCategorySamples;

public class ShopCategoryMapperTest
        extends AbstractMapperTest<ShopCategory, ShopCategoryMask, ShopCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ShopCategory buildSample() {
        return ShopCategorySamples.build();
    }

}
