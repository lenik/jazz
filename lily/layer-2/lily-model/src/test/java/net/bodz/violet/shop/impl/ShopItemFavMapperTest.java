package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.ShopItemFav;
import net.bodz.violet.shop.ShopItemFavSamples;

public class ShopItemFavMapperTest
        extends AbstractMapperTest<ShopItemFav, ShopItemFavMask, ShopItemFavMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ShopItemFav buildSample() {
        return ShopItemFavSamples.build();
    }

}
