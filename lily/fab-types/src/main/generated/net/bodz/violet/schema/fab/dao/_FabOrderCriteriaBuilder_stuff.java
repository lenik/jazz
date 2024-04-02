package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.fab.FabOrder;

@ForEntityType(FabOrder.class)
public class _FabOrderCriteriaBuilder_stuff<self_t extends _FabOrderCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final LongField planId = _long("plan");

    public final IntegerField customOrgId = integer("custom_org");

    public final IntegerField customId = integer("custom");

    public final IntegerField clerkId = integer("clerk");

    public final IntegerField nitem = integer("nitem");

    public final BigDecimalField totalQuantity = bigDecimal("sum_qty");

    public final BigDecimalField totalAmount = bigDecimal("sum_amount");

    public final IntegerField taskCount = integer("ntask");

    public final IntegerField processCount = integer("nproc");

    public final IntegerField trackCount = integer("ntrack");

}
