package net.bodz.violet.sale.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.sale.ShippingOrderItem;

/**
 * 送货单明细
 * 
 * 送货单的具体明细项目
 */
@ObjectType(ShippingOrderItem.class)
public class ShippingOrderItemIndex
        extends CoIndex<ShippingOrderItem, ShippingOrderItemMask> {

}
