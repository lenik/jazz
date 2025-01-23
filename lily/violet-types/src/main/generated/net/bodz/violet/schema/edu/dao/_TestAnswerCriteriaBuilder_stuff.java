package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoMessageCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.edu.TestAnswer;

@ForEntityType(TestAnswer.class)
public class _TestAnswerCriteriaBuilder_stuff<self_t extends _TestAnswerCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final LongField questionId = _long("q");

    public final BooleanField valid = bool("\"valid\"");

}
