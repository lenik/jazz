package net.bodz.violet.plan.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.plan.PlanVote;

/**
* @label PlanVote
*/
@ObjectType(PlanVote.class)
public class PlanVoteIndex
        extends CoIndex<PlanVote, PlanVoteCriteriaBuilder> {

    public PlanVoteIndex() {
    }

}
