package net.bodz.lily.pub.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _ArticleTagCriteriaBuilder_stuff<self_t extends _ArticleTagCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final LongField articleId = _long("article");

    public final IntegerField tagId = integer("tag");

}
