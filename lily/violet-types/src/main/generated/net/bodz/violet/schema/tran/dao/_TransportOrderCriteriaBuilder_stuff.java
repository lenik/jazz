package net.bodz.violet.schema.tran.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;

public class _TransportOrderCriteriaBuilder_stuff<self_t extends _TransportOrderCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final LongField prevId = _long("prev");

    public final LongField salesOrderId = _long("saleodr");

    public final LongField storeodrId = _long("storeodr");

    public final IntegerField shipperId = integer("shipper");

    public final BigDecimalField shipcost = bigDecimal("shipcost");

    public final IntegerField length = integer("\"length\"");

    public final BigDecimalField totalQuantity = bigDecimal("sum_qty");

    public final BigDecimalField totalAmount = bigDecimal("sum_amount");

}
