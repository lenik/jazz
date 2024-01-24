package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.shop.ShopItemFav;
import net.bodz.violet.schema.shop.ShopItemFavSamples;

public class ShopItemFavManagerTest
        extends AbstractManagerTest<ShopItemFav, ShopItemFavMapper, ShopItemFavManager> {

    @Override
    public ShopItemFav buildSample()
            throws Exception {
        ShopItemFavSamples a = new ShopItemFavSamples();
        return a.buildWired(tables);
    }

}
