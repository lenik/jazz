package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.plan.PlanFav;

@ForEntityType(PlanFav.class)
public class _PlanFavCriteriaBuilder_stuff<self_t extends _PlanFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField planId = _long("plan");

    public final IntegerField userId = integer("\"user\"");

}
