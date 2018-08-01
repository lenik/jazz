package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.CartItem;
import net.bodz.violet.shop.CartItemSamples;

public class CartItemMapperTest
        extends AbstractMapperTest<CartItem, CartItemMask, CartItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public CartItem buildSample() {
        return CartItemSamples.build();
    }

}
