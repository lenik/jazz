package net.bodz.lily.schema.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _ParameterDefCriteriaBuilder_stuff<self_t extends _ParameterDefCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final IntegerField schemaId = integer("\"schema\"");

}
