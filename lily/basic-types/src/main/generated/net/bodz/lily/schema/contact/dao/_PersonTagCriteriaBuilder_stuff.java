package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.contact.PersonTag;

@ForEntityType(PersonTag.class)
public class _PersonTagCriteriaBuilder_stuff<self_t extends _PersonTagCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField personId = integer("person");

    public final IntegerField tagId = integer("tag");

}
