package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.plan.PlanParty;

@ForEntityType(PlanParty.class)
public class _PlanPartyCriteriaBuilder_stuff<self_t extends _PlanPartyCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField planId = _long("plan");

    public final IntegerField personId = integer("person");

    public final IntegerField orgId = integer("org");

}
