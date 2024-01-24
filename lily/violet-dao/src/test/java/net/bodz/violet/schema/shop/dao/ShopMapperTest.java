package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.shop.Shop;
import net.bodz.violet.schema.shop.ShopSamples;

public class ShopMapperTest
        extends AbstractTableTest<Shop, ShopMapper> {

    @Override
    public Shop buildSample()
            throws Exception {
        ShopSamples a = new ShopSamples();
        return a.buildWired(tables);
    }

}
