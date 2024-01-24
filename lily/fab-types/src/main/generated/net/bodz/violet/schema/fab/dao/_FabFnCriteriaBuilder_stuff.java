package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _FabFnCriteriaBuilder_stuff<self_t extends _FabFnCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField image = string("image");

    public final StringField imageAlt = string("imagealt");

    public final IntegerField parentId = integer("parent");

    public final IntegerField depth = integer("\"depth\"");

    public final IntegerField refCount = integer("nref");

}
