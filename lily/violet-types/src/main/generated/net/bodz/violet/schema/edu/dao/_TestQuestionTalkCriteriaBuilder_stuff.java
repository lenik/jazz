package net.bodz.violet.schema.edu.dao;

import java.sql.Timestamp;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _TestQuestionTalkCriteriaBuilder_stuff<self_t extends _TestQuestionTalkCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final DateField<Timestamp> beginTime = date("t0", Timestamp.class);

    public final DateField<Timestamp> endTime = date("t1", Timestamp.class);

    public final IntegerField year = integer("\"year\"");

    public final StringField subject = string("subject");

    public final IntegerField opId = integer("op");

    public final StringField rawText = string("text");

    public final IntegerField formId = integer("form");

    public final StringField formArguments = string("formargs");

    public final LongField questionId = _long("q");

    public final LongField parentId = _long("parent");

}
