package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.PlanLogVoteMask
 * @see net.bodz.violet.plan.impl.PlanLogVoteMapper
 * @see net.bodz.violet.plan.impl.PlanLogVote_htm
 * @see net.bodz.violet.plan.impl.PlanLogVoteIndex
 * @see net.bodz.violet.plan.impl.PlanLogVoteIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogVoteMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogVote_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogVote_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogVoteIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogVoteIndex_htm.js
*/
@IdType(Integer.class)
public class PlanLogVote
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public PlanLogVote() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planLogVote: ...");
        return sb.toString();
    }

}
