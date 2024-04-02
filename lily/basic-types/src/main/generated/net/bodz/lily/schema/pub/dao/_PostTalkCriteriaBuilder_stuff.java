package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.pub.PostTalk;

@ForEntityType(PostTalk.class)
public class _PostTalkCriteriaBuilder_stuff<self_t extends _PostTalkCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final LongField postId = _long("post");

    public final LongField parentId = _long("parent");

}
