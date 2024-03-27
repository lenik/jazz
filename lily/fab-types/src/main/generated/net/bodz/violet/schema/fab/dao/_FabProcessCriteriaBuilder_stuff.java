package net.bodz.violet.schema.fab.dao;

import java.time.OffsetDateTime;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoEventCriteriaBuilder;

public class _FabProcessCriteriaBuilder_stuff<self_t extends _FabProcessCriteriaBuilder_stuff<self_t>>
        extends CoEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final LongField taskId = _long("task");

    public final LongField parentId = _long("parent");

    public final IntegerField outputId = integer("\"output\"");

    public final IntegerField standardId = integer("std");

    public final BigDecimalField quantity = bigDecimal("qty");

    public final DiscreteField<JsonVariant> batch = discrete("batch", JsonVariant.class);

    public final DateField<OffsetDateTime> since = date("since", OffsetDateTime.class);

    public final DateField<OffsetDateTime> deadline = date("deadline", OffsetDateTime.class);

    public final IntegerField trackCount = integer("ntrack");

}
