package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.shop.SellPrice;
import net.bodz.violet.schema.shop.SellPriceSamples;

public class SellPriceManagerTest
        extends AbstractManagerTest<SellPrice, SellPriceMapper, SellPriceManager> {

    @Override
    public SellPrice buildSample()
            throws Exception {
        SellPriceSamples a = new SellPriceSamples();
        return a.buildWired(tables);
    }

}
