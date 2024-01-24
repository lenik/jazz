package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _TestQuestionFavCriteriaBuilder_stuff<self_t extends _TestQuestionFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField testqId = _long("testq");

    public final IntegerField userId = integer("\"user\"");

}
