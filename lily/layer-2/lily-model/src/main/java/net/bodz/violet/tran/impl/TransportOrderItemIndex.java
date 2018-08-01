package net.bodz.violet.tran.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.tran.TransportOrderItem;

/**
 * 送货单明细
 *
 * 送货单的具体明细项目
 */
@ObjectType(TransportOrderItem.class)
public class TransportOrderItemIndex
        extends CoIndex<TransportOrderItem, TransportOrderItemMask> {

}
