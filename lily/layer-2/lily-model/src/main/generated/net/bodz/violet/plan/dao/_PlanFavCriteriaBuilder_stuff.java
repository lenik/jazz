package net.bodz.violet.plan.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _PlanFavCriteriaBuilder_stuff<self_t extends _PlanFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField planId = _long("plan");

    public final IntegerField userId = integer("\"user\"");

}
