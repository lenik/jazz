package net.bodz.lily.pub.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _PostFavCriteriaBuilder_stuff<self_t extends _PostFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField postId = _long("post");

    public final IntegerField userId = integer("\"user\"");

}
