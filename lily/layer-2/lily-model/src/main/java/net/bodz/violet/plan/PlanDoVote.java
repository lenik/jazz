package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.PlanDoVoteMask
 * @see net.bodz.violet.plan.impl.PlanDoVoteMapper
 * @see net.bodz.violet.plan.impl.PlanDoVote_htm
 * @see net.bodz.violet.plan.impl.PlanDoVoteIndex
 * @see net.bodz.violet.plan.impl.PlanDoVoteIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoVoteMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoVote_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoVote_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoVoteIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoVoteIndex_htm.js
*/
@IdType(Integer.class)
public class PlanDoVote
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public PlanDoVote() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planLogVote: ...");
        return sb.toString();
    }

}
