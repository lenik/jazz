package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.pub.ArticleTalk;

@ForEntityType(ArticleTalk.class)
public class _ArticleTalkCriteriaBuilder_stuff<self_t extends _ArticleTalkCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final LongField articleId = _long("article");

    public final LongField parentId = _long("parent");

}
