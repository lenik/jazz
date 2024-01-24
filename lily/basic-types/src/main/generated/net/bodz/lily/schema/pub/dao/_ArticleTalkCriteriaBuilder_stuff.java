package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;

public class _ArticleTalkCriteriaBuilder_stuff<self_t extends _ArticleTalkCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final LongField articleId = _long("article");

    public final LongField parentId = _long("parent");

}
