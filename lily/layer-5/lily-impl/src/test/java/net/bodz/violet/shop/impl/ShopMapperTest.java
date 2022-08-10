package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.shop.Shop;
import net.bodz.violet.shop.ShopSamples;

public class ShopMapperTest
        extends AbstractMapperTest<Shop, ShopMask, ShopMapper> {

    @Override
    public Shop buildSample() {
        return ShopSamples.build();
    }

}
