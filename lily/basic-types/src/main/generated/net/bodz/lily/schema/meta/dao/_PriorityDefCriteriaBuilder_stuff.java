package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.meta.PriorityDef;

@ForEntityType(PriorityDef.class)
public class _PriorityDefCriteriaBuilder_stuff<self_t extends _PriorityDefCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField schemaId = integer("\"schema\"");

}
