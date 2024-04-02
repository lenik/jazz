package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.pub.ArticleTag;

@ForEntityType(ArticleTag.class)
public class _ArticleTagCriteriaBuilder_stuff<self_t extends _ArticleTagCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final LongField articleId = _long("article");

    public final IntegerField tagId = integer("tag");

}
