package net.bodz.lily.pub.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _ArticleCategoryCriteriaBuilder_stuff<self_t extends _ArticleCategoryCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField image = string("image");

    public final StringField imageAlt = string("imagealt");

    public final IntegerField parentId = integer("parent");

    public final IntegerField depth = integer("\"depth\"");

    public final IntegerField refCount = integer("nref");

}
