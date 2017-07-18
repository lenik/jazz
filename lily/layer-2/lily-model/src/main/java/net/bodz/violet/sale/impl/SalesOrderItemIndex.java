package net.bodz.violet.sale.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.sale.SalesOrderItem;

/**
 * 销售订单明细
 * 
 * 销售订单的具体明细项目
 */
@ObjectType(SalesOrderItem.class)
public class SalesOrderItemIndex
        extends CoIndex<SalesOrderItem, SalesOrderItemMask> {

}
