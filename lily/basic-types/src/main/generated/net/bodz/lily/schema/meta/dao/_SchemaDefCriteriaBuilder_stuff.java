package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.meta.SchemaDef;

@ForEntityType(SchemaDef.class)
public class _SchemaDefCriteriaBuilder_stuff<self_t extends _SchemaDefCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField dummy = integer("dummy");

}
