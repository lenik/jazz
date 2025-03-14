package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoMessageCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.edu.TestQuestion;

@ForEntityType(TestQuestion.class)
public class _TestQuestionCriteriaBuilder_stuff<self_t extends _TestQuestionCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final IntegerField courseId = integer("course");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField favCount = integer("nfav");

    public final IntegerField hateCount = integer("nhate");

    public final IntegerField pos = integer("pos");

    public final StringField answer = string("answer");

}
