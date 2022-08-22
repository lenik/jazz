package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.Shop;
import net.bodz.violet.shop.ShopSamples;

public class ShopMapperTest
        extends AbstractTableTest<Shop, ShopMask, ShopMapper> {

    @Override
    public Shop buildSample() {
        return ShopSamples.build();
    }

}
