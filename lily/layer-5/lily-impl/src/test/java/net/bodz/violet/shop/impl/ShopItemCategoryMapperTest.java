package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.shop.ShopItemCategory;
import net.bodz.violet.shop.ShopItemCategorySamples;

public class ShopItemCategoryMapperTest
        extends AbstractMapperTest<ShopItemCategory, ShopItemCategoryMask, ShopItemCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ShopItemCategory buildSample() {
        return ShopItemCategorySamples.build();
    }

}
