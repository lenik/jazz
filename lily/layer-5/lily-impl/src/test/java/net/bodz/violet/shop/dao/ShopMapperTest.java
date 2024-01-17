package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.Shop;
import net.bodz.violet.shop.ShopSamples;

public class ShopMapperTest
        extends AbstractTableTest<Shop, ShopMapper> {

    @Override
    public Shop buildSample()
            throws Exception {
        ShopSamples a = new ShopSamples();
        return a.buildWired(tables);
    }

}
