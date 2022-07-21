package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.shop.ShopTag;
import net.bodz.violet.shop.ShopTagSamples;

public class ShopTagMapperTest
        extends AbstractMapperTest<ShopTag, ShopTagMask, ShopTagMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ShopTag buildSample() {
        return ShopTagSamples.build();
    }

}
