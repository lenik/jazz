package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.concrete.CoCategoryCriteriaBuilder;

public class _CategoryDefCriteriaBuilder_stuff<self_t extends _CategoryDefCriteriaBuilder_stuff<self_t>>
        extends CoCategoryCriteriaBuilder<self_t> {

    public final StringField code = string("code");

    public final IntegerField schemaId = integer("\"schema\"");

    public final IntegerField refCount = integer("nobj");

}
