package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.shop.ShopItemFav;
import net.bodz.violet.shop.ShopItemFavSamples;

public class ShopItemFavMapperTest
        extends AbstractMapperTest<ShopItemFav, ShopItemFavMask, ShopItemFavMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ShopItemFav buildSample() {
        return ShopItemFavSamples.build();
    }

}
