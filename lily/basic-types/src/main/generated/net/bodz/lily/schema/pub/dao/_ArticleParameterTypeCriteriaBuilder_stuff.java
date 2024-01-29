package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;

public class _ArticleParameterTypeCriteriaBuilder_stuff<self_t extends _ArticleParameterTypeCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField name = string("\"name\"");

    public final IntegerField dummy = integer("dummy");

}
