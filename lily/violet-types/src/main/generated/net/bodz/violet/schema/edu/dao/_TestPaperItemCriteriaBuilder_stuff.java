package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.edu.TestPaperItem;

@ForEntityType(TestPaperItem.class)
public class _TestPaperItemCriteriaBuilder_stuff<self_t extends _TestPaperItemCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField paperId = integer("paper");

    public final LongField questionId = _long("q");

    public final BigDecimalField score = bigDecimal("score");

}
