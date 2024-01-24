package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _FabProcessCriteriaBuilder_stuff<self_t extends _FabProcessCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField year = integer("\"year\"");

    public final LongField taskId = _long("task");

    public final LongField parentId = _long("parent");

    public final IntegerField outputId = integer("\"output\"");

    public final IntegerField standardId = integer("std");

    public final BigDecimalField quantity = bigDecimal("qty");

    public final IntegerField trackCount = integer("ntrack");

}
