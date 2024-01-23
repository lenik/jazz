package net.bodz.violet.edu.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _TestPaperItemCriteriaBuilder_stuff<self_t extends _TestPaperItemCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField paperId = integer("paper");

    public final LongField questionId = _long("q");

    public final BigDecimalField score = bigDecimal("score");

}
