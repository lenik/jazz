package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.ShopCategory;
import net.bodz.violet.shop.ShopCategorySamples;

public class ShopCategoryMapperTest
        extends AbstractTableTest<ShopCategory, ShopCategoryMapper> {

    @Override
    public ShopCategory buildSample()
            throws Exception {
        ShopCategorySamples a = new ShopCategorySamples();
        return a.buildWired(tables);
    }

}
