package net.bodz.lily.schema.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _ParameterValueCriteriaBuilder_stuff<self_t extends _ParameterValueCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final IntegerField parameterId = integer("parm");

    public final StringField val = string("val");

}
