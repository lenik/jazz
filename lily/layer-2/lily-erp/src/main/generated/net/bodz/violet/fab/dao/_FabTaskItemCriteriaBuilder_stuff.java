package net.bodz.violet.fab.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _FabTaskItemCriteriaBuilder_stuff<self_t extends _FabTaskItemCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField year = integer("\"year\"");

    public final LongField taskId = _long("task");

    public final StringField status = string("status");

    public final IntegerField modelId = integer("model");

    public final BigDecimalField quantity = bigDecimal("qty");

    public final IntegerField trackCount = integer("ntrack");

}
