package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.shop.ShopItemCategory;
import net.bodz.violet.shop.ShopItemCategorySamples;

public class ShopItemCategoryManagerTest
        extends AbstractManagerTest<ShopItemCategory, ShopItemCategoryMapper, ShopItemCategoryManager> {

    @Override
    public ShopItemCategory buildSample()
            throws Exception {
        ShopItemCategorySamples a = new ShopItemCategorySamples();
        return a.buildWired(tables);
    }

}
