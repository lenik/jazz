package net.bodz.lily.schema.geo.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _ZoneCriteriaBuilder_stuff<self_t extends _ZoneCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField categoryId = integer("cat");

    public final StringField country = string("country");

    public final IntegerField parentId = integer("parent");

    public final IntegerField depth = integer("\"depth\"");

    public final StringField telCode = string("telcode");

    public final StringField postCode = string("postcode");


}
