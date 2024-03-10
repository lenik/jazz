package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;

public class _TestPaperItemCriteriaBuilder_stuff<self_t extends _TestPaperItemCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField paperId = integer("paper");

    public final LongField questionId = _long("q");

    public final BigDecimalField score = bigDecimal("score");

}
