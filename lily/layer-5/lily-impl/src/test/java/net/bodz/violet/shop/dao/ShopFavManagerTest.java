package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.shop.ShopFav;
import net.bodz.violet.shop.ShopFavSamples;

public class ShopFavManagerTest
        extends AbstractManagerTest<ShopFav, ShopFavMapper, ShopFavManager> {

    @Override
    public ShopFav buildSample()
            throws Exception {
        ShopFavSamples a = new ShopFavSamples();
        return a.buildWired(tables);
    }

}
