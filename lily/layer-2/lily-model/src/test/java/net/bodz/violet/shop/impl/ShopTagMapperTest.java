package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.ShopTag;
import net.bodz.violet.shop.ShopTagSamples;

public class ShopTagMapperTest
        extends AbstractMapperTest<ShopTag, ShopTagMask, ShopTagMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ShopTag buildSample() {
        return ShopTagSamples.build();
    }

}
