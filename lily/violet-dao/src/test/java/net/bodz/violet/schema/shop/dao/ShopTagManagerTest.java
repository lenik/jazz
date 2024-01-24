package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.shop.ShopTag;
import net.bodz.violet.schema.shop.ShopTagSamples;

public class ShopTagManagerTest
        extends AbstractManagerTest<ShopTag, ShopTagMapper, ShopTagManager> {

    @Override
    public ShopTag buildSample()
            throws Exception {
        ShopTagSamples a = new ShopTagSamples();
        return a.buildWired(tables);
    }

}
