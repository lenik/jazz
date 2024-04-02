package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.fab.FabProcessSerial;

@ForEntityType(FabProcessSerial.class)
public class _FabProcessSerialCriteriaBuilder_stuff<self_t extends _FabProcessSerialCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField processId = _long("proc");

    public final StringField serial = string("serial");

}
