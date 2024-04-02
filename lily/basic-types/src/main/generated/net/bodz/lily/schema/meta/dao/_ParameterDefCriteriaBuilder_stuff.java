package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.meta.ParameterDef;

@ForEntityType(ParameterDef.class)
public class _ParameterDefCriteriaBuilder_stuff<self_t extends _ParameterDefCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField schemaId = integer("\"schema\"");

}
