package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.shop.CartItem;
import net.bodz.violet.shop.CartItemSamples;
import net.bodz.violet.shop.ShopItem;

public class CartItemMapperTest
        extends AbstractMapperTest<CartItem, CartItemMask, CartItemMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public CartItem buildSample() {
        ShopItem shopItem = tables.pickAny(ShopItemMapper.class, "shopitem");
        return CartItemSamples.build(shopItem);
    }

}
