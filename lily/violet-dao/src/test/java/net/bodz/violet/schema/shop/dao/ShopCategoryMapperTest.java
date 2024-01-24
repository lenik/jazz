package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.shop.ShopCategory;
import net.bodz.violet.schema.shop.ShopCategorySamples;

public class ShopCategoryMapperTest
        extends AbstractTableTest<ShopCategory, ShopCategoryMapper> {

    @Override
    public ShopCategory buildSample()
            throws Exception {
        ShopCategorySamples a = new ShopCategorySamples();
        return a.buildWired(tables);
    }

}
