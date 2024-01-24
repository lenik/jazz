package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.shop.ShopItem;
import net.bodz.violet.schema.shop.ShopItemSamples;

public class ShopItemMapperTest
        extends AbstractTableTest<ShopItem, ShopItemMapper> {

    @Override
    public ShopItem buildSample()
            throws Exception {
        ShopItemSamples a = new ShopItemSamples();
        return a.buildWired(tables);
    }

}
