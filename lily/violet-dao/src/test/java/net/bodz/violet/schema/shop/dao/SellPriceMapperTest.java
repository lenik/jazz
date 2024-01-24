package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.shop.SellPrice;
import net.bodz.violet.schema.shop.SellPriceSamples;

public class SellPriceMapperTest
        extends AbstractTableTest<SellPrice, SellPriceMapper> {

    @Override
    public SellPrice buildSample()
            throws Exception {
        SellPriceSamples a = new SellPriceSamples();
        return a.buildWired(tables);
    }

}
