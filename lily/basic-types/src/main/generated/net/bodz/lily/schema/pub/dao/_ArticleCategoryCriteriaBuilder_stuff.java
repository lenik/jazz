package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.CoCategoryCriteriaBuilder;

public class _ArticleCategoryCriteriaBuilder_stuff<self_t extends _ArticleCategoryCriteriaBuilder_stuff<self_t>>
        extends CoCategoryCriteriaBuilder<self_t> {

    public final StringField name = string("\"name\"");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField refCount = integer("nref");

}
