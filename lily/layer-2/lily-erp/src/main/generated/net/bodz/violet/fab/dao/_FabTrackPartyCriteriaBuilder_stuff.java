package net.bodz.violet.fab.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _FabTrackPartyCriteriaBuilder_stuff<self_t extends _FabTrackPartyCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField trackId = _long("track");

    public final IntegerField personId = integer("person");

    public final StringField role = string("\"role\"");

}
