package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _TestAnswerCriteriaBuilder_stuff<self_t extends _TestAnswerCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField questionId = _long("q");

    public final BooleanField valid = bool("\"valid\"");

}
