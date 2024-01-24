package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.shop.ShopItemCategory;
import net.bodz.violet.schema.shop.ShopItemCategorySamples;

public class ShopItemCategoryMapperTest
        extends AbstractTableTest<ShopItemCategory, ShopItemCategoryMapper> {

    @Override
    public ShopItemCategory buildSample()
            throws Exception {
        ShopItemCategorySamples a = new ShopItemCategorySamples();
        return a.buildWired(tables);
    }

}
