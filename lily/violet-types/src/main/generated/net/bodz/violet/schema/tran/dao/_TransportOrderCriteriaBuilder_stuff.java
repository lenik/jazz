package net.bodz.violet.schema.tran.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoMessageCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.tran.TransportOrder;

@ForEntityType(TransportOrder.class)
public class _TransportOrderCriteriaBuilder_stuff<self_t extends _TransportOrderCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final LongField prevId = _long("prev");

    public final LongField salesOrderId = _long("saleodr");

    public final LongField storeodrId = _long("storeodr");

    public final IntegerField shipperId = integer("shipper");

    public final BigDecimalField shipcost = bigDecimal("shipcost");

    public final IntegerField length = integer("\"length\"");

    public final BigDecimalField totalQuantity = bigDecimal("sum_qty");

    public final BigDecimalField totalAmount = bigDecimal("sum_amount");

}
