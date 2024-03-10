package net.bodz.violet.schema.fab.dao;

import java.sql.Timestamp;

import net.bodz.lily.concrete.CoEventCriteriaBuilder;

public class _FabTaskCriteriaBuilder_stuff<self_t extends _FabTaskCriteriaBuilder_stuff<self_t>>
        extends CoEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final LongField orderId = _long("odr");

    public final DateField<Timestamp> since = date("since", Timestamp.class);

    public final DateField<Timestamp> deadline = date("deadline", Timestamp.class);

    public final IntegerField processCount = integer("nproc");

    public final IntegerField trackCount = integer("ntrack");

}
