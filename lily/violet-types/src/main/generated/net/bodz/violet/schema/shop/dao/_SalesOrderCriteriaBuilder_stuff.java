package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;

public class _SalesOrderCriteriaBuilder_stuff<self_t extends _SalesOrderCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final LongField previousOrderId = _long("prev");

    public final LongField planId = _long("plan");

    public final IntegerField customerOrgId = integer("customer_org");

    public final IntegerField customerId = integer("customer");

    public final IntegerField length = integer("\"length\"");

    public final BigDecimalField totalQuantity = bigDecimal("sum_qty");

    public final BigDecimalField totalAmount = bigDecimal("sum_amount");

}
