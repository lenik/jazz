package net.bodz.lily.pub.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _ArticleVoteCriteriaBuilder_stuff<self_t extends _ArticleVoteCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField parentId = _long("parent");

    public final IntegerField userId = integer("\"user\"");

    public final IntegerField voteScore = integer("votes");

}
