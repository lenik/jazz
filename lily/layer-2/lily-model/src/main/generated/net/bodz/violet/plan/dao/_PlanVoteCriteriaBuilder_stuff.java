package net.bodz.violet.plan.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _PlanVoteCriteriaBuilder_stuff<self_t extends _PlanVoteCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField parentId = _long("parent");

    public final IntegerField userId = integer("\"user\"");

    public final IntegerField voteScore = integer("votes");

}
