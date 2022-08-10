package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.shop.ShopItemCategory;
import net.bodz.violet.shop.ShopItemCategorySamples;

public class ShopItemCategoryMapperTest
        extends AbstractMapperTest<ShopItemCategory, ShopItemCategoryMask, ShopItemCategoryMapper> {

    @Override
    public ShopItemCategory buildSample() {
        return ShopItemCategorySamples.build();
    }

}
