package net.bodz.lily.pub.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _PostTagCriteriaBuilder_stuff<self_t extends _PostTagCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final LongField postId = _long("post");

    public final IntegerField tagId = integer("tag");

}
