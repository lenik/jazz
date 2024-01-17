package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.shop.ShopItemFav;
import net.bodz.violet.shop.ShopItemFavSamples;

public class ShopItemFavManagerTest
        extends AbstractManagerTest<ShopItemFav, ShopItemFavMapper, ShopItemFavManager> {

    @Override
    public ShopItemFav buildSample()
            throws Exception {
        ShopItemFavSamples a = new ShopItemFavSamples();
        return a.buildWired(tables);
    }

}
