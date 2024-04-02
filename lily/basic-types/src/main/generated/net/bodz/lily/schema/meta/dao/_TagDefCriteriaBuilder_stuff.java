package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.meta.TagDef;

@ForEntityType(TagDef.class)
public class _TagDefCriteriaBuilder_stuff<self_t extends _TagDefCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField tagGroupId = integer("tagv");

}
