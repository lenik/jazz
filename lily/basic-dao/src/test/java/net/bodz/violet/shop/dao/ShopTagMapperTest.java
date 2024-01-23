package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.ShopTag;
import net.bodz.violet.shop.ShopTagSamples;

public class ShopTagMapperTest
        extends AbstractTableTest<ShopTag, ShopTagMapper> {

    @Override
    public ShopTag buildSample()
            throws Exception {
        ShopTagSamples a = new ShopTagSamples();
        return a.buildWired(tables);
    }

}
