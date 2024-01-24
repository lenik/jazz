package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _FabTaskCriteriaBuilder_stuff<self_t extends _FabTaskCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField year = integer("\"year\"");

    public final LongField orderId = _long("odr");

    public final IntegerField processCount = integer("nproc");

    public final IntegerField trackCount = integer("ntrack");

}
