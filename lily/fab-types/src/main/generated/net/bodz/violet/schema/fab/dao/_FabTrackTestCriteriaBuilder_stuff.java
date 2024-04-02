package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.fab.FabTrackTest;

@ForEntityType(FabTrackTest.class)
public class _FabTrackTestCriteriaBuilder_stuff<self_t extends _FabTrackTestCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField trackId = _long("track");

    public final IntegerField standardId = integer("std");

    public final BooleanField valid = bool("\"valid\"");

}
