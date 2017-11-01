package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.PlanVoteMask
 * @see net.bodz.violet.plan.impl.PlanVoteMapper
 * @see net.bodz.violet.plan.impl.PlanVote_htm
 * @see net.bodz.violet.plan.impl.PlanVoteIndex
 * @see net.bodz.violet.plan.impl.PlanVoteIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanVoteMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanVote_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanVote_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanVoteIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanVoteIndex_htm.js
*/
@IdType(Integer.class)
public class PlanVote
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public PlanVote() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planVote: ...");
        return sb.toString();
    }

}
