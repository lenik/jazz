package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.plan.Plan;

@ForEntityType(Plan.class)
public class _PlanCriteriaBuilder_stuff<self_t extends _PlanCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final IntegerField nlike = integer("nlike");

    public final DoubleField value = _double("\"value\"");

}
