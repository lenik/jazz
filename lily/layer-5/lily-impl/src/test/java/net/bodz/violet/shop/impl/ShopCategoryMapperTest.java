package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.ShopCategory;
import net.bodz.violet.shop.ShopCategorySamples;

public class ShopCategoryMapperTest
        extends AbstractTableTest<ShopCategory, ShopCategoryMask, ShopCategoryMapper> {

    @Override
    public ShopCategory buildSample() {
        return ShopCategorySamples.build();
    }

}
