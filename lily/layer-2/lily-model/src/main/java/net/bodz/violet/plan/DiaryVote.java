package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.DiaryVoteMask
 * @see net.bodz.violet.plan.impl.DiaryVoteMapper
 * @see net.bodz.violet.plan.impl.DiaryVoteIndex
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryVoteMapper.xml
*/
@IdType(Integer.class)
public class DiaryVote
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public DiaryVote() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryVote: ...");
        return sb.toString();
    }

}
