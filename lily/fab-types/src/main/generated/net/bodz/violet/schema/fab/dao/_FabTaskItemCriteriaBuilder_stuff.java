package net.bodz.violet.schema.fab.dao;

import java.time.OffsetDateTime;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoEventCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.fab.FabTaskItem;

@ForEntityType(FabTaskItem.class)
public class _FabTaskItemCriteriaBuilder_stuff<self_t extends _FabTaskItemCriteriaBuilder_stuff<self_t>>
        extends CoEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField taskId = _long("task");

    public final DateField<OffsetDateTime> deadline = date("deadline", OffsetDateTime.class);

    public final StringField status = string("status");

    public final IntegerField modelId = integer("model");

    public final BigDecimalField quantity = bigDecimal("qty");

    public final DiscreteField<JsonVariant> batch = discrete("batch", JsonVariant.class);

    public final IntegerField trackCount = integer("ntrack");

}
