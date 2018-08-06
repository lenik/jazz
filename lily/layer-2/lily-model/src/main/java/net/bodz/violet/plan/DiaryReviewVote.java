package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.template.VoteRecord;

@Table(name = "diaryrev_vote")
public class DiaryReviewVote
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    DiaryReview review;

    public DiaryReviewVote() {
    }

    public DiaryReview getReview() {
        return review;
    }

    public void setReview(DiaryReview review) {
        this.review = review;
    }

}
