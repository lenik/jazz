package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.ShopFav;
import net.bodz.violet.shop.ShopFavSamples;

public class ShopFavMapperTest
        extends AbstractMapperTest<ShopFav, ShopFavMask, ShopFavMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ShopFav buildSample() {
        return ShopFavSamples.build();
    }

}
