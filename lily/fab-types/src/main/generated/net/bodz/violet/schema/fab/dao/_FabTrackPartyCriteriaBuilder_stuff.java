package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.fab.FabTrackParty;

@ForEntityType(FabTrackParty.class)
public class _FabTrackPartyCriteriaBuilder_stuff<self_t extends _FabTrackPartyCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField trackId = _long("track");

    public final IntegerField personId = integer("person");

    public final StringField role = string("\"role\"");

}
