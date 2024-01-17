package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.shop.ShopCategory;
import net.bodz.violet.shop.ShopCategorySamples;

public class ShopCategoryManagerTest
        extends AbstractManagerTest<ShopCategory, ShopCategoryMapper, ShopCategoryManager> {

    @Override
    public ShopCategory buildSample()
            throws Exception {
        ShopCategorySamples a = new ShopCategorySamples();
        return a.buildWired(tables);
    }

}
