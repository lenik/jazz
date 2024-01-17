package net.bodz.violet.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.shop.CartItem;

public class CartItemManager
        extends AbstractEntityManager<CartItem, CartItemMapper> {

    public CartItemManager(DataContext dataContext) {
        super(dataContext, CartItemMapper.class);
    }

}
