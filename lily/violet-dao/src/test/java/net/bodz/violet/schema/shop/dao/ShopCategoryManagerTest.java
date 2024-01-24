package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.shop.ShopCategory;
import net.bodz.violet.schema.shop.ShopCategorySamples;

public class ShopCategoryManagerTest
        extends AbstractManagerTest<ShopCategory, ShopCategoryMapper, ShopCategoryManager> {

    @Override
    public ShopCategory buildSample()
            throws Exception {
        ShopCategorySamples a = new ShopCategorySamples();
        return a.buildWired(tables);
    }

}
