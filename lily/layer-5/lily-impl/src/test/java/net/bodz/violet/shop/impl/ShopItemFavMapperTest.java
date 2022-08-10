package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.shop.ShopItemFav;
import net.bodz.violet.shop.ShopItemFavSamples;

public class ShopItemFavMapperTest
        extends AbstractMapperTest<ShopItemFav, ShopItemFavMask, ShopItemFavMapper> {

    @Override
    public ShopItemFav buildSample() {
        return ShopItemFavSamples.build();
    }

}
