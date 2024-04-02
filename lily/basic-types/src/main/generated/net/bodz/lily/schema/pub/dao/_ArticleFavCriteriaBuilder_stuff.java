package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.pub.ArticleFav;

@ForEntityType(ArticleFav.class)
public class _ArticleFavCriteriaBuilder_stuff<self_t extends _ArticleFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField articleId = _long("article");

    public final IntegerField userId = integer("\"user\"");

}
