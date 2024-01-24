package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _FabTrackTestParameterCriteriaBuilder_stuff<self_t extends _FabTrackTestParameterCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField testId = _long("test");

    public final IntegerField parameterId = integer("parm");

    public final IntegerField testerId = integer("tester");

    public final StringField actual = string("actual");

    public final BooleanField valid = bool("\"valid\"");

}
