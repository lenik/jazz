package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.ShopFav;
import net.bodz.violet.shop.ShopFavSamples;

public class ShopFavMapperTest
        extends AbstractTableTest<ShopFav, ShopFavMask, ShopFavMapper> {

    @Override
    public ShopFav buildSample() {
        return ShopFavSamples.build();
    }

}
