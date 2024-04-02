package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.meta.ParameterValue;

@ForEntityType(ParameterValue.class)
public class _ParameterValueCriteriaBuilder_stuff<self_t extends _ParameterValueCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField parameterId = integer("parm");

    public final StringField val = string("val");

}
