package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.shop.CartItem;
import net.bodz.violet.shop.CartItemSamples;

public class CartItemManagerTest
        extends AbstractManagerTest<CartItem, CartItemMapper, CartItemManager> {

    @Override
    public CartItem buildSample()
            throws Exception {
        CartItemSamples a = new CartItemSamples();
        return a.buildWired(tables);
    }

}
