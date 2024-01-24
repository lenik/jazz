package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _ArticleTagCriteriaBuilder_stuff<self_t extends _ArticleTagCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final LongField articleId = _long("article");

    public final IntegerField tagId = integer("tag");

}
