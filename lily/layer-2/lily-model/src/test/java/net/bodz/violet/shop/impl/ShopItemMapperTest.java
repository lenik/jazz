package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.ShopItem;
import net.bodz.violet.shop.ShopItemSamples;

public class ShopItemMapperTest
        extends AbstractMapperTest<ShopItem, ShopItemMask, ShopItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ShopItem buildSample() {
        return ShopItemSamples.build();
    }

}
