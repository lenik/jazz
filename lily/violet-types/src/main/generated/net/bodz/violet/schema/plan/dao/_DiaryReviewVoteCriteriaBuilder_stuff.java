package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.concrete.VoteRecordCriteriaBuilder;

public class _DiaryReviewVoteCriteriaBuilder_stuff<self_t extends _DiaryReviewVoteCriteriaBuilder_stuff<self_t>>
        extends VoteRecordCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField parentId = _long("parent");

    public final IntegerField voteScore = integer("votes");

}
