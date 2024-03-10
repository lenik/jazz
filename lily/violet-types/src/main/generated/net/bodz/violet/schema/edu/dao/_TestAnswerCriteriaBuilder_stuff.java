package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;

public class _TestAnswerCriteriaBuilder_stuff<self_t extends _TestAnswerCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final LongField questionId = _long("q");

    public final BooleanField valid = bool("\"valid\"");

}
