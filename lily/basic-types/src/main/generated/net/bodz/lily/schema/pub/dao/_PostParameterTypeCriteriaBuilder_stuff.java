package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.pub.PostParameterType;

@ForEntityType(PostParameterType.class)
public class _PostParameterTypeCriteriaBuilder_stuff<self_t extends _PostParameterTypeCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField name = string("\"name\"");

    public final IntegerField dummy = integer("dummy");

}
