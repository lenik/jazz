package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.shop.CartItem;

public class CartItemManager
        extends AbstractEntityManager<CartItem, CartItemMapper> {

    public CartItemManager(DataContext dataContext) {
        super(dataContext, CartItemMapper.class);
    }

}
