package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.shop.ShopItem;
import net.bodz.violet.schema.shop.ShopItemSamples;

public class ShopItemManagerTest
        extends AbstractManagerTest<ShopItem, ShopItemMapper, ShopItemManager> {

    @Override
    public ShopItem buildSample()
            throws Exception {
        ShopItemSamples a = new ShopItemSamples();
        return a.buildWired(tables);
    }

}
