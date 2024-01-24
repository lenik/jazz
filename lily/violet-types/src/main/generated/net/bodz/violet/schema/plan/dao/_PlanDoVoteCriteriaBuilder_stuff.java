package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _PlanDoVoteCriteriaBuilder_stuff<self_t extends _PlanDoVoteCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField parentId = _long("parent");

    public final IntegerField userId = integer("\"user\"");

    public final IntegerField voteScore = integer("votes");

}
