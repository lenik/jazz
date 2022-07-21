package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.shop.ShopFav;
import net.bodz.violet.shop.ShopFavSamples;

public class ShopFavMapperTest
        extends AbstractMapperTest<ShopFav, ShopFavMask, ShopFavMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ShopFav buildSample() {
        return ShopFavSamples.build();
    }

}
