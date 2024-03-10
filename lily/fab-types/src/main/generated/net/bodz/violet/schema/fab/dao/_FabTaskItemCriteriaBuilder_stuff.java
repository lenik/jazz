package net.bodz.violet.schema.fab.dao;

import java.sql.Timestamp;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoEventCriteriaBuilder;

public class _FabTaskItemCriteriaBuilder_stuff<self_t extends _FabTaskItemCriteriaBuilder_stuff<self_t>>
        extends CoEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField taskId = _long("task");

    public final DateField<Timestamp> deadline = date("deadline", Timestamp.class);

    public final StringField status = string("status");

    public final IntegerField modelId = integer("model");

    public final BigDecimalField quantity = bigDecimal("qty");

    public final DiscreteField<JsonVariant> batch = discrete("batch", JsonVariant.class);

    public final IntegerField trackCount = integer("ntrack");

}
