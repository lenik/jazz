package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;

public class _DiaryReviewCriteriaBuilder_stuff<self_t extends _DiaryReviewCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final LongField diaryId = _long("diary");

    public final LongField parentId = _long("parent");

}
