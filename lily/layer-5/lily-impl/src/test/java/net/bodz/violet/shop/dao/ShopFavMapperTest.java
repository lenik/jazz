package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.ShopFav;
import net.bodz.violet.shop.ShopFavSamples;

public class ShopFavMapperTest
        extends AbstractTableTest<ShopFav, ShopFavMapper> {

    @Override
    public ShopFav buildSample()
            throws Exception {
        ShopFavSamples a = new ShopFavSamples();
        return a.buildWired(tables);
    }

}
