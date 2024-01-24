package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.shop.CartItem;
import net.bodz.violet.schema.shop.CartItemSamples;

public class CartItemMapperTest
        extends AbstractTableTest<CartItem, CartItemMapper> {

    @Override
    public CartItem buildSample()
            throws Exception {
        CartItemSamples a = new CartItemSamples();
        return a.buildWired(tables);
    }

}
