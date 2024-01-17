package net.bodz.violet.fab.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _FabTrackTestCriteriaBuilder_stuff<self_t extends _FabTrackTestCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField trackId = _long("track");

    public final IntegerField standardId = integer("std");

    public final BooleanField valid = bool("\"valid\"");

}
