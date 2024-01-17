package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.ShopItem;
import net.bodz.violet.shop.ShopItemSamples;

public class ShopItemMapperTest
        extends AbstractTableTest<ShopItem, ShopItemMapper> {

    @Override
    public ShopItem buildSample()
            throws Exception {
        ShopItemSamples a = new ShopItemSamples();
        return a.buildWired(tables);
    }

}
