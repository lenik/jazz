package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _VAppCatCriteriaBuilder_stuff<self_t extends _VAppCatCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField name = string("\"name\"");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField parentId = integer("parent");

    public final IntegerField depth = integer("\"depth\"");

    public final IntegerField refCount = integer("nref");

}
