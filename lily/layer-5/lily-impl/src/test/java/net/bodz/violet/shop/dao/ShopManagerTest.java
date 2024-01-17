package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.shop.Shop;
import net.bodz.violet.shop.ShopSamples;

public class ShopManagerTest
        extends AbstractManagerTest<Shop, ShopMapper, ShopManager> {

    @Override
    public Shop buildSample()
            throws Exception {
        ShopSamples a = new ShopSamples();
        return a.buildWired(tables);
    }

}
