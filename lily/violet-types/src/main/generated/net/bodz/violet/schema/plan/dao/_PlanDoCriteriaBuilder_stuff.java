package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.plan.PlanDo;

@ForEntityType(PlanDo.class)
public class _PlanDoCriteriaBuilder_stuff<self_t extends _PlanDoCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final LongField planId = _long("plan");

    public final LongField parentId = _long("parent");

    public final DiscreteField<String[]> changes = discrete("changes", String[].class);

}
