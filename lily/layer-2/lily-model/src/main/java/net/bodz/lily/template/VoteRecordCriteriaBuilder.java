package net.bodz.lily.template;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public abstract class VoteRecordCriteriaBuilder<self_t extends VoteRecordCriteriaBuilder<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField userId = integer("user");
    public final IntegerField vote = integer("n");

    void voteUp() {
        vote.greaterThan(0);
    }

    void voteDown() {
        vote.lessThan(0);
    }

}
