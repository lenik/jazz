package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;

/**
 * @see net.bodz.violet.plan.impl.DiaryReplyVoteMask
 * @see net.bodz.violet.plan.impl.DiaryReplyVoteMapper
 * @see net.bodz.violet.plan.impl.DiaryReplyVote_htm
 * @see net.bodz.violet.plan.impl.DiaryReplyVoteIndex
 * @see net.bodz.violet.plan.impl.DiaryReplyVoteIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryReplyVoteMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryReplyVote_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryReplyVote_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryReplyVoteIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryReplyVoteIndex_htm.js
*/
@IdType(Integer.class)
public class DiaryReplyVote
        extends CoMomentInterval<Integer> {

    private static final long serialVersionUID = 1L;
    
    public DiaryReplyVote() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryReplyVote: ...");
        return sb.toString();
    }

}
