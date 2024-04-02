package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.VoteRecordCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.pub.ArticleVote;

@ForEntityType(ArticleVote.class)
public class _ArticleVoteCriteriaBuilder_stuff<self_t extends _ArticleVoteCriteriaBuilder_stuff<self_t>>
        extends VoteRecordCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField parentId = _long("parent");

    public final IntegerField voteScore = integer("votes");

}
