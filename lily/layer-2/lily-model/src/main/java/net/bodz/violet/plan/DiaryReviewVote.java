package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;

/**
 * @see net.bodz.violet.plan.impl.DiaryReviewVoteMask
 * @see net.bodz.violet.plan.impl.DiaryReviewVoteMapper
 * @see net.bodz.violet.plan.impl.DiaryReviewVote_htm
 * @see net.bodz.violet.plan.impl.DiaryReviewVoteIndex
 * @see net.bodz.violet.plan.impl.DiaryReviewVoteIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryReviewVoteMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryReviewVote_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryReviewVote_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryReviewVoteIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryReviewVoteIndex_htm.js
*/
@IdType(Integer.class)
public class DiaryReviewVote
        extends CoMomentInterval<Integer> {

    private static final long serialVersionUID = 1L;
    
    public DiaryReviewVote() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryReviewVote: ...");
        return sb.toString();
    }

}
