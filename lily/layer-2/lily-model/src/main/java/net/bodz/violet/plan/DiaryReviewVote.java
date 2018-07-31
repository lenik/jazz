package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;


@Table(name = "diaryrev_vote")
@IdType(Integer.class)
public class DiaryReviewVote
        extends CoMomentInterval<Integer> {

    private static final long serialVersionUID = 1L;

    public DiaryReviewVote() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryReviewVote: ...");
        return sb.toString();
    }

}
