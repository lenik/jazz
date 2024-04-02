package net.bodz.violet.schema.edu.dao;

import java.time.OffsetDateTime;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.edu.TestQuestionTalk;

@ForEntityType(TestQuestionTalk.class)
public class _TestQuestionTalkCriteriaBuilder_stuff<self_t extends _TestQuestionTalkCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final DateField<OffsetDateTime> beginTime = date("t0", OffsetDateTime.class);

    public final DateField<OffsetDateTime> endTime = date("t1", OffsetDateTime.class);

    public final IntegerField year = integer("\"year\"");

    public final StringField subject = string("subject");

    public final IntegerField opId = integer("op");

    public final StringField rawText = string("text");

    public final IntegerField formId = integer("form");

    public final StringField formArguments = string("formargs");

    public final LongField questionId = _long("q");

    public final LongField parentId = _long("parent");

}
