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

    public final StringField sAlias = string("s_alias");

    public final StringField sAddress1 = string("s_address1");

    public final StringField sAddress2 = string("s_address2");

    public final IntegerField sZoneId = integer("s_zone");

    public final StringField sTel = string("s_tel");

    public final BooleanField sTelok = bool("s_telok");

    public final StringField sEmail = string("s_email");

    public final BooleanField sEmailok = bool("s_emailok");

    public final StringField dAlias = string("d_alias");

    public final StringField dAddress1 = string("d_address1");

    public final StringField dAddress2 = string("d_address2");

    public final IntegerField dZoneId = integer("d_zone");

    public final StringField dTel = string("d_tel");

    public final BooleanField dTelok = bool("d_telok");

    public final StringField dEmail = string("d_email");

    public final BooleanField dEmailok = bool("d_emailok");

    public final BigDecimalField shipcost = bigDecimal("shipcost");

    public final IntegerField length = integer("\"length\"");

    public final BigDecimalField totalQuantity = bigDecimal("sum_qty");

    public final BigDecimalField totalAmount = bigDecimal("sum_amount");

}
