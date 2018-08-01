package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.SellPrice;
import net.bodz.violet.shop.SellPriceSamples;

public class SellPriceMapperTest
        extends AbstractMapperTest<SellPrice, SellPriceMask, SellPriceMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public SellPrice buildSample() {
        return SellPriceSamples.build();
    }

}
