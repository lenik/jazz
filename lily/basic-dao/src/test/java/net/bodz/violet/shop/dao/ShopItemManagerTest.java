package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.shop.ShopItem;
import net.bodz.violet.shop.ShopItemSamples;

public class ShopItemManagerTest
        extends AbstractManagerTest<ShopItem, ShopItemMapper, ShopItemManager> {

    @Override
    public ShopItem buildSample()
            throws Exception {
        ShopItemSamples a = new ShopItemSamples();
        return a.buildWired(tables);
    }

}
