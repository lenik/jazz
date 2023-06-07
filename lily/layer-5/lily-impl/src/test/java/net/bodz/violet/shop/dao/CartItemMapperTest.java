package net.bodz.violet.shop.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.CartItem;
import net.bodz.violet.shop.CartItemSamples;

public class CartItemMapperTest
        extends AbstractTableTest<CartItem, CartItemMask, CartItemMapper> {

    @Override
    public CartItem buildSample()
            throws Exception {
        CartItemSamples a = new CartItemSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.shopItem = tables.pickAny(ShopItemMapper.class, "shopitem");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
