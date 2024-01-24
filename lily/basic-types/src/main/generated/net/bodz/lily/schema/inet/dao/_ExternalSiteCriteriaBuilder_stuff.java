package net.bodz.lily.schema.inet.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _ExternalSiteCriteriaBuilder_stuff<self_t extends _ExternalSiteCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField parentId = integer("parent");

    public final IntegerField depth = integer("\"depth\"");

    public final StringField urlfmt = string("urlfmt");

    public final IntegerField bonus = integer("bonus");

    public final IntegerField count = integer("\"count\"");

}
