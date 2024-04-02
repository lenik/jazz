package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.edu.TestApply;

@ForEntityType(TestApply.class)
public class _TestApplyCriteriaBuilder_stuff<self_t extends _TestApplyCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField paperId = integer("paper");

    public final IntegerField personId = integer("person");

    public final BigDecimalField score = bigDecimal("score");

}
