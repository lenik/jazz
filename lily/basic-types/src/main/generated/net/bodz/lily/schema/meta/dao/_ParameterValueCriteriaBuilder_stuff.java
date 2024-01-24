package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _ParameterValueCriteriaBuilder_stuff<self_t extends _ParameterValueCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final IntegerField parameterId = integer("parm");

    public final StringField val = string("val");

}
