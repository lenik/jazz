package net.bodz.violet.schema.store.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _StoreOrderCriteriaBuilder_stuff<self_t extends _StoreOrderCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField year = integer("\"year\"");

    public final StringField subject = string("subject");

    public final IntegerField opId = integer("op");

    public final StringField rawText = string("text");

    public final IntegerField formId = integer("form");

    public final StringField formArguments = string("formargs");

    public final IntegerField categoryId = integer("cat");

    public final IntegerField phaseId = integer("phase");

    public final LongField prevId = _long("prev");

    public final LongField planId = _long("plan");

    public final IntegerField orgId = integer("org");

    public final IntegerField orgUnitId = integer("ou");

    public final IntegerField personId = integer("person");

    public final IntegerField length = integer("\"length\"");

    public final BigDecimalField totalQuantity = bigDecimal("sum_qty");

    public final BigDecimalField totalAmount = bigDecimal("sum_amount");

}
