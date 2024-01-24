package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _CategoryDefCriteriaBuilder_stuff<self_t extends _CategoryDefCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final IntegerField schemaId = integer("\"schema\"");

    public final IntegerField parentId = integer("parent");

    public final IntegerField depth = integer("\"depth\"");

    public final IntegerField refCount = integer("nobj");

}
