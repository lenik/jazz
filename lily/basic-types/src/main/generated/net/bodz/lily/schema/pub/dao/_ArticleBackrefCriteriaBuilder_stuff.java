package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.pub.ArticleBackref;

@ForEntityType(ArticleBackref.class)
public class _ArticleBackrefCriteriaBuilder_stuff<self_t extends _ArticleBackrefCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final LongField articleId = _long("article");

    public final IntegerField siteId = integer("site");

    public final StringField key = string("\"key\"");

    public final IntegerField value = integer("\"value\"");

}
