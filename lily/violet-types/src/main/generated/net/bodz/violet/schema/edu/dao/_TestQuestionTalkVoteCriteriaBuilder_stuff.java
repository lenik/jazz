package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.concrete.VoteRecordCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.edu.TestQuestionTalkVote;

@ForEntityType(TestQuestionTalkVote.class)
public class _TestQuestionTalkVoteCriteriaBuilder_stuff<self_t extends _TestQuestionTalkVoteCriteriaBuilder_stuff<self_t>>
        extends VoteRecordCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField parentId = _long("parent");

    public final IntegerField voteScore = integer("votes");

}
