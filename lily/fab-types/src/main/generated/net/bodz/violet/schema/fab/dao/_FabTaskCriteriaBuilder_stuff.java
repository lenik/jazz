package net.bodz.violet.schema.fab.dao;

import java.time.OffsetDateTime;

import net.bodz.lily.concrete.CoEventCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.fab.FabTask;

@ForEntityType(FabTask.class)
public class _FabTaskCriteriaBuilder_stuff<self_t extends _FabTaskCriteriaBuilder_stuff<self_t>>
        extends CoEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final LongField orderId = _long("odr");

    public final DateField<OffsetDateTime> since = date("since", OffsetDateTime.class);

    public final DateField<OffsetDateTime> deadline = date("deadline", OffsetDateTime.class);

    public final IntegerField processCount = integer("nproc");

    public final IntegerField trackCount = integer("ntrack");

}
