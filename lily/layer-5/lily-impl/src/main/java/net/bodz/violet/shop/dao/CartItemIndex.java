package net.bodz.violet.shop.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.shop.CartItem;

/**
* @label CartItem
*/
@ObjectType(CartItem.class)
public class CartItemIndex
        extends CoIndex<CartItem, CartItemCriteriaBuilder> {

    public CartItemIndex() {
    }

}
