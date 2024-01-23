package net.bodz.violet.fab.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _FabProcessSerialCriteriaBuilder_stuff<self_t extends _FabProcessSerialCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField processId = _long("proc");

    public final StringField serial = string("serial");

}
