package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.ShopCategory;
import net.bodz.violet.shop.ShopCategorySamples;

public class ShopCategoryMapperTest
        extends AbstractMapperTest<ShopCategory, ShopCategoryMask, ShopCategoryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ShopCategory buildSample() {
        return ShopCategorySamples.build();
    }

}
